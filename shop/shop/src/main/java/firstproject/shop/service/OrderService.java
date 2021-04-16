package firstproject.shop.service;

import firstproject.shop.domain.*;
import firstproject.shop.repository.ItemRepository;
import firstproject.shop.repository.MemberRepository;
import firstproject.shop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId,Long itemId,int count){
        Member member = memberRepository.findOne(memberId);
        Delivery delivery=new Delivery();
        Item item = itemRepository.findOne(itemId);

        delivery.updateDelivery(member.getZipcode(),member.getCity(),member.getStreet(), DeliveryStatus.READY);

        OrderItem orderItem=OrderItem.createOrderItem(item,item.getPrice(),count);

        Order order=Order.createOrder(member,delivery,orderItem);

        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void orderCancel(Long orderId){
        Order order = orderRepository.findOne(orderId);

        order.cancel();
    }

    @Transactional
    public void orderComplete(Long orderId){
        Optional<Order> orderWithNull = orderRepository.findDelivery(orderId);

        Order order=orderWithNull.get();
        order.getDelivery().updateStatus(DeliveryStatus.COMPLETE);
    }
}
