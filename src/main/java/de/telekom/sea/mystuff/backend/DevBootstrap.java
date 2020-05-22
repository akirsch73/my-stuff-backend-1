package de.telekom.sea.mystuff.backend; // Was macht diese Klasse - wie funktioniert sie?

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ItemRepository itemRepository;
    private Item item, item2, item3;

    public DevBootstrap(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    private void initData() {
        item = new Item((long) 1, "Hund", 3, "Keller", "Kuscheltier", Date.valueOf("2019-11-12"));
        item2 = new Item((long) 2, "Katze", 3, "Zwinger", "Kuscheltier", Date.valueOf("2017-11-12"));
        item3 = new Item((long) 3, "Maus", 3, "Dachboden", "Kuscheltier", Date.valueOf("2015-11-12"));
  

        List<Item> newItems = Arrays.asList(item, item2, item3);
        itemRepository.saveAll(newItems);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}