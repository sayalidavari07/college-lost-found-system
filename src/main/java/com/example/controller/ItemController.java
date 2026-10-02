package com.example.lostfound.controller;

import com.example.lostfound.dto.ItemDTO;
import com.example.lostfound.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // 1. Add Item
    @PostMapping
    public ItemDTO addItem(@Valid @RequestBody ItemDTO itemDTO) {
        return itemService.addItem(itemDTO);
    }

    // 2. Get All Items
    @GetMapping
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();
    }

    // 3. Get Item By ID
    @GetMapping("/{id}")
    public ItemDTO getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    // 4. Update Item
    @PutMapping("/{id}")
    public ItemDTO updateItem(@PathVariable Long id,
                              @Valid @RequestBody ItemDTO itemDTO) {
        return itemService.updateItem(id, itemDTO);
    }

    // 5. Delete Item
    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return "Item deleted successfully";
    }

    // 6. Search Item By Name
    @GetMapping("/searchByName")
    public List<ItemDTO> searchByName(@RequestParam String name) {
        return itemService.searchByName(name);
    }

    // 7. Filter Item By Category
    @GetMapping("/filterByCategory")
    public List<ItemDTO> filterByCategory(@RequestParam String category) {
        return itemService.filterByCategory(category);
    }
}