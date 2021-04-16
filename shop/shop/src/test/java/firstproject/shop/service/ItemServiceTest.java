package firstproject.shop.service;

import firstproject.shop.Exception.NotEnoughStockQuantityException;
import firstproject.shop.domain.Item;
import firstproject.shop.repository.ItemRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemService itemService;

    @Test
    public void 아이템저장() throws Exception{
        Item item=new Item();

        item.addName("a");
        Long itemId = itemService.uploadItem(item);

        Assertions.assertEquals(item,itemRepository.findOne(itemId));
    }

    @Test(expected = NotEnoughStockQuantityException.class)
    public void 재고_부족() throws Exception{
        Item item=new Item();

        item.addName("a");
        item.updateStockAndPrice(10,100);
        item.delStock(11);

        Assertions.fail("재고 부족");
    }
}
