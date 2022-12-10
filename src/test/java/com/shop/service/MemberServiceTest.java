package com.shop.service;

import com.shop.dto.MemberDto;
import com.shop.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(){
        MemberDto memberDto = new MemberDto();
        memberDto.setEmail("test@email.com");
        memberDto.setName("tester");
        memberDto.setAddress("부산시");
        memberDto.setPassword("1234");
        return Member.createMember(memberDto,passwordEncoder);

    }

    @DisplayName("saveMemberTest")
    @Test
    public void saveMemberTest(){
        // given
        Member member = createMember();
        // when
        Member savedMember = memberService.saveMember(member);

        // then
        assertEquals(member.getName(),savedMember.getName());
        assertEquals(member.getAddress(),savedMember.getAddress());
        assertEquals(member.getEmail(),savedMember.getEmail());
        assertEquals(member.getPassword(),savedMember.getPassword());
        assertEquals(member.getRole(),savedMember.getRole());
    }

    @DisplayName("saveDuplicateMemberTest")
    @Test
    public void saveDuplicateMemberTest(){
        // given
        Member member = createMember();
        Member member1 = createMember();
        Member savedMember = memberService.saveMember(member1);
        // when
        Throwable e = assertThrows(IllegalStateException.class,()->{
            memberService.saveMember(member1);
        });


        // then
        assertEquals("이미 가입된 회원입니다.",e.getMessage());

    }

}
