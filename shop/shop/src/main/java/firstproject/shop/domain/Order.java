package firstproject.shop.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name="orders")
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    //주문 시간
    private LocalDateTime orderDate;

    //주문 상태
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    //멤버 엔티티
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //주문 엔티티
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    //주문 상품 엔티티
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems=new ArrayList<>();
}
