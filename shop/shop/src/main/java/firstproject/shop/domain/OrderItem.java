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

    //주문 상품 가격
    private int totalPrice;
    
    //주문 상품 개수
    private int count;

    public void addOrder(Order order){
        this.order=order;
    }

    public void updateOrderItem(Item item,int totalPrice,int count){
        this.item=item;
        this.totalPrice=totalPrice;
        this.count=count;
    }

    public static OrderItem createOrderItem(Item item,int totalPrice,int count){
        OrderItem orderItem=new OrderItem();
        orderItem.updateOrderItem(item,totalPrice,count);

        item.delStock(count);
        return orderItem;
    }
    
    public void cancel(){
        getItem().plusStock(count);
    }
    
    public int totalPrice(){
        return getCount()*getTotalPrice();
    }
    
}
