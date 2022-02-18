package web.diaryservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import web.diaryservice.repository.MemberRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceImpTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 로그인(){
        //given

        //when

        //then
    }

}