package firstproject.shop.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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

    public void addMemberAndOrder(Member member){
        this.member=member;
        member.getOrders().add(this);
    }

    public void addOrderItemAndOrder(OrderItem orderItem){
        this.orderItems.add(orderItem);
        orderItem.addOrder(this);
    }

    public void addDeliveryAndOrder(Delivery delivery){
        this.delivery=delivery;
        delivery.addOrder(this);
    }

    public void addStatusAndDate(OrderStatus orderStatus,LocalDateTime dateTime){
        this.orderStatus=orderStatus;
        this.orderDate=orderDate;
    }

    public void updateStatus(OrderStatus orderStatus){
        this.orderStatus=orderStatus;
    }
    //생성 메소드
    public static Order createOrder(Member member,Delivery delivery,OrderItem... orderItems){
        Order order=new Order();
        order.addMemberAndOrder(member);
        order.addDeliveryAndOrder(delivery);
        for(OrderItem orderItem:orderItems){
            order.addOrderItemAndOrder(orderItem);
        }

        order.addStatusAndDate(OrderStatus.ORDER,LocalDateTime.now());

        return order;
    }

    //비즈니스 로직
    /**
     * 주문 취소
     */
    public void cancel(){
        if(delivery.getDeliveryStatus()==DeliveryStatus.COMPLETE){
            throw new IllegalStateException("주문이 완료된 상태 환불 불가");
        }
        this.updateStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem:this.orderItems){
            orderItem.cancel();
        }
    }

    /**
     * 주문 가격 조회
     */
    public int TotalPrice(){
        int totalPrice=0;
        for(OrderItem orderItem:this.orderItems){
            totalPrice+=orderItem.totalPrice();
        }
        return totalPrice;
    }
}
