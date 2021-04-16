package firstproject.shop.repository;

import firstproject.shop.domain.Delivery;
import firstproject.shop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class,id);
    }

    public Optional<Order> findDelivery(Long id){
         return Optional.ofNullable(em.createQuery("select o from Order o join fetch o.delivery where o.id= :id",Order.class)
                .setParameter("id", id)
                .getSingleResult());
    }
}
