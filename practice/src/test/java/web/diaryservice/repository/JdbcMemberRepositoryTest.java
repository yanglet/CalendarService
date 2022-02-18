package web.diaryservice.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import web.diaryservice.domain.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class JdbcMemberRepositoryTest{

    @Autowired MemberRepository memberRepository;
//    private String mem_id;
//    private String mem_pw;
//    private String mem_name;
//    private int mem_age;
//    private String mem_gender;
//    private int mem_usertype;
    @Test
    public void 회원가입() throws Exception{ //테스트를 어떻게 해야되는지 모르겠네요
        //given
        Member member = new Member("user1","pw1","name1",1,"male",1);

        //when
        memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById("user1").get();

        assertThat(member).isEqualTo(findMember);
//        org.junit.jupiter.api.Assertions.assertEquals(member, findMember);
    }

    @Test
    public void findAll() throws  Exception{
        //given
        Member member1 = new Member("user1","pw1","name1",1,"male",1);
        Member member2 = new Member("user2","pw2","name2",2,"male",1);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> members = memberRepository.findAll();
        for(Member member: members){
            System.out.println(member.getMem_id());
        }
        System.out.println(members);
        System.out.println(member1);

        //then
        assertThat(members.size()).isEqualTo(2); //이건 안되는거 인정 원래 디비를 쓰는거니까
        assertThat(members).contains(member1,member2); //아니 왜안돼 말도안돼 진짜 ㅡㅡ
    }

}