package web.diaryservice.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import web.diaryservice.domain.Diary;
import web.diaryservice.domain.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class JdbcDiaryRepository implements DiaryRepository{

    private final JdbcTemplate jdbcTemplate;
    private final MemberRepository memberRepository;

    @Autowired
    private DiaryMapper diaryMapper;

    public JdbcDiaryRepository(DataSource dataSource, MemberRepository memberRepository) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Diary> diaryList(HttpServletRequest request) {//위부터 아래로 오름차순으로 정렬해서 갖다줌
        String sql = "select id,title,writer,content,start,end,allDay,textColor,backgroundColor,borderColor from diary where mem_id=? order by start asc";
        HttpSession session = request.getSession();

        return jdbcTemplate.query(sql, diaryMapper, session.getAttribute("mem_id"));
    }

    @Override
    public Optional<Diary> findById(Long id) {
        String sql = "select * from diary where id = ?";
        List<Diary> result = jdbcTemplate.query(sql, diaryMapper, id);

        return result.stream().findAny();
    }

    @Override
    public void save(Diary diary, HttpServletRequest request) { //id는 db에서 자동으로 생성되도록 설계
        String sql = "insert into diary(title,writer,content,start,end,allDay,textColor,backgroundColor,borderColor, mem_id) values (?,?,?,?,?,?,?,?,?,?)";
        HttpSession session = request.getSession();
        String mem_id = String.valueOf(session.getAttribute("mem_id"));
        Optional<Member> member = memberRepository.findById(mem_id);

        int result = jdbcTemplate.update(sql, diary.getTitle(), member.get().getMem_name(),
                diary.getContent(), diary.getStart(), diary.getStart(), 1, //end에도 start를 넣어줘야함
                "#ffffff", "#90c45a", "#ffffff", session.getAttribute("mem_id"));
    }

    @Override
    public void update(Diary diary) { //title, content, id를 따로 변수로 받아와야하려나
        String sql = "update diary set start=?, title=?, content=? where id=?";

        int result = jdbcTemplate.update(sql, diary.getStart(), diary.getTitle(), diary.getContent(), diary.getId());
        log.info("Diary update success");
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from diary where id=?";

        int result = jdbcTemplate.update(sql, id);
        log.info("Diary delete success");
    }

    public int getPages(HttpServletRequest request){ //이거 필요없으려나
        return 0;
    }


}
