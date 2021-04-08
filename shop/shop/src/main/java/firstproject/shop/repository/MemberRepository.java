package firstproject.shop.repository;

import firstproject.shop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public List<Member> findByName(String name){
        List<Member> members= em.createQuery("select m from Member m where m.name= :name", Member.class).setParameter("name", name).getResultList();

        return members;
    }

    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m",Member.class).getResultList();
    }
}
