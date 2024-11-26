package com.daturism.taller3.Service;

import com.daturism.taller3.Model.Cliente;
import com.daturism.taller3.Model.Item;
import com.daturism.taller3.Repository.IItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private IItemRepository itemRepository;

    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public Item findItem(Long id_item) {
        return itemRepository.findById(id_item).orElse(null);
    }

    public void deleteItem(Long id_item) {
        itemRepository.deleteById(id_item);
    }

    public void editItem(Item item) {
        this.saveItem(item);
    }
}
