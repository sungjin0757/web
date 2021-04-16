package firstproject.shop.service;

import firstproject.shop.domain.Member;
import firstproject.shop.repository.MemberRepository;
import firstproject.shop.service.MemberService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    public void 회원가입() throws Exception{
        //given
        Member member=new Member();
        member.updateNameAndPassword("abc","123");

        //when
        Long id=memberService.join(member);

        //then
        Assertions.assertEquals(member,memberRepository.findOne(id));
    }
}
