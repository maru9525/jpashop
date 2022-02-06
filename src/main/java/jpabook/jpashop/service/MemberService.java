package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //기본적으로 트랜잭션 안에서 데이터 변경해라
@Transactional(readOnly = true) //스프링 트랜잭션 어노테이션을 사용해라 전체적으로 readOnly
@RequiredArgsConstructor //final로 된거만 생성자를 만들어준다.
public class MemberService {

    private final MemberRepository memberRepository;

    //회원가입
    @Transactional //따로 설정한 것.
    public Long join(Member member){
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }//멀티 스레드시에는 name을 db에서 유니크 설정을 하는 것이 좋음.
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }


}
