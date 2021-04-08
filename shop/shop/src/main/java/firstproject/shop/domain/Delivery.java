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

    //주문 상태
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

}
