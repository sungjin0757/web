package firstproject.shop.service;

import firstproject.shop.domain.Member;
import firstproject.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member){
        //이미 가입한 회원
        validateDuplicate(member);
        //비밀번호 암호
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        member.EncodePassword(passwordEncoder.encode(member.getPassword()));
        memberRepository.save(member);
        return member.getId();
    }

    //중복 회원
    private void validateDuplicate(Member member){
        List<Member> members=memberRepository.findByName(member.getName());
        if(!members.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다!");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        System.out.println("name = " + name);
        Member member=new Member();
        List<Member> members=memberRepository.findByName(name);
        for(Member member1:members){
            member=member1;
        }

        List<GrantedAuthority> authorities=new ArrayList<>();

        if(("admin").equals(name)){
            authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        }else{
            authorities.add(new SimpleGrantedAuthority(MemberRole.MEMBER.getValue()));
        }

        return new User(member.getName(),member.getPassword(),authorities);
    }

    //회원 전체 조회
    public List<Member> allMembers(){
        return memberRepository.findAll();
    }

    //회원 한명 조회
    public Member oneMember(Long memberId){
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void modification(Long id){
        Member findMember = memberRepository.findOne(id);


    }
}
