package firstproject.shop.domain;

import firstproject.shop.service.MemberRole;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    //회원 이름
    private String name;
    
    //회원 비밀번호
    private String password;

    //회원 주소
    private String zipcode;
    private String city;
    private String street;

    //주문 엔티티
    @OneToMany(mappedBy = "member")
    private List<Order> orders=new ArrayList<>();

    public void EncodePassword(String password){
        this.password=password;
    }
    public void updateNameAndPassword(String name,String password){
        this.name=name;
        this.password=password;
    }
}
