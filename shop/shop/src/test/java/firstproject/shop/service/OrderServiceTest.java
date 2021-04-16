package firstproject.shop.service;

import firstproject.shop.domain.*;
import firstproject.shop.repository.ItemRepository;
import firstproject.shop.repository.MemberRepository;
import firstproject.shop.repository.OrderRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemService itemService;

    @Test
    public void 주문(){
        Member member=new Member("a","123","1","1","1");
        Item item=new Item();
        item.addName("b");
        item.updateStockAndPrice(4,10000);

        memberService.join(member);
        itemService.uploadItem(item);
        Long id = orderService.order(member.getId(), item.getId(), 3);
        Order order=orderRepository.findOne(id);
        Assertions.assertEquals(OrderStatus.ORDER,order.getOrderStatus());
        assertEquals(1,order.getOrderItems().size());
        assertEquals(10000*3,order.TotalPrice());
        assertEquals(1,item.getStock());
    }

    @Test
    public void 주문취소() throws  Exception{
        Member member=new Member("a","123","1","1","1");
        Item item=new Item();
        item.addName("b");
        item.updateStockAndPrice(4,10000);

        memberService.join(member);
        itemService.uploadItem(item);
        Long id = orderService.order(member.getId(), item.getId(), 3);
        orderService.orderCancel(id);

        Assertions.assertEquals(orderRepository.findOne(id).getOrderStatus(),OrderStatus.CANCEL);
        Assertions.assertEquals(4,item.getStock());
    }

    @Test
    public void 배송_완료() throws Exception{
        Member member=new Member("a","123","1","1","1");
        Item item=new Item();
        item.addName("b");
        item.updateStockAndPrice(4,10000);

        memberService.join(member);
        itemService.uploadItem(item);
        Long id = orderService.order(member.getId(), item.getId(), 3);
        orderService.orderComplete(id);
        Order order=orderRepository.findOne(id);

        Assertions.assertEquals(order.getDelivery().getDeliveryStatus(), DeliveryStatus.COMPLETE);
    }


}
