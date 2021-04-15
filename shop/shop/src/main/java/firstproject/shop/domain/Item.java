package firstproject.shop.domain;

import firstproject.shop.Exception.NotEnoughStockQuantityException;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Item {

    @Id @GeneratedValue
    @Column(name="item_id")
    private Long id;

    //상품 이름
    private String name;
    //재고
    private int stock;

    //상품 가격
    private int price;
    
    //상품의 종류
    private String sort;

    //CategoryItem 엔티티
    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categoryItems=new ArrayList<>();

    public void plusStock(int quantity){
        this.sort+=quantity;
    }

    public void delStock(int quantity){
        int restStock=this.stock-quantity;
        if(restStock<0){
            throw new NotEnoughStockQuantityException("재고부족");
        }
        this.stock=restStock;
    }
}
