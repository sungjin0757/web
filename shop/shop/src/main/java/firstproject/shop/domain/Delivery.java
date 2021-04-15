package firstproject.shop.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    //주소
    private String zipcode;
    private String city;
    private String street;

    @OneToOne(fetch = FetchType.LAZY,mappedBy = "delivery")
    private Order order;
    //주문 상태
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    public void addOrder(Order order){
        this.order=order;
    }

}
