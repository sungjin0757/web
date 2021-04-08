package firstproject.shop.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name="order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    //상품 엔티티
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    //주문 상품 총 가격
    private int totalPrice;
    
    //주문 상품 개수
    private int count;
}
