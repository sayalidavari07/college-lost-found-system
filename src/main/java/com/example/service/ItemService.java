package com.example.lostfound.service;

import com.example.lostfound.dto.ItemDTO;
import com.example.lostfound.entity.Item;
import com.example.lostfound.exception.ItemNotFoundException;
import com.example.lostfound.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // Convert Entity to DTO
    private ItemDTO convertToDTO(Item item) {
        ItemDTO dto = new ItemDTO();

        dto.setItemName(item.getItemName());
        dto.setCategory(item.getCategory());
        dto.setDescription(item.getDescription());
        dto.setLocation(item.getLocation());
        dto.setStatus(item.getStatus());
        dto.setContact(item.getContact());

        return dto;
    }

    // Convert DTO to Entity
    private Item convertToEntity(ItemDTO dto) {
        Item item = new Item();

        item.setItemName(dto.getItemName());
        item.setCategory(dto.getCategory());
        item.setDescription(dto.getDescription());
        item.setLocation(dto.getLocation());
        item.setStatus(dto.getStatus());
        item.setContact(dto.getContact());

        return item;
    }

    // Add Item
    public ItemDTO addItem(ItemDTO dto) {
        Item item = convertToEntity(dto);
        Item savedItem = itemRepository.save(item);

        return convertToDTO(savedItem);
    }

    // Get All Items
    public List<ItemDTO> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get Item By ID
    public ItemDTO getItemById(Long id) {
        return itemRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    // Update Item
    public ItemDTO updateItem(Long id, ItemDTO dto) {

        Item existingItem = itemRepository.findById(id).orElse(null);

        if (existingItem != null) {

            existingItem.setItemName(dto.getItemName());
            existingItem.setCategory(dto.getCategory());
            existingItem.setDescription(dto.getDescription());
            existingItem.setLocation(dto.getLocation());
            existingItem.setStatus(dto.getStatus());
            existingItem.setContact(dto.getContact());

            Item updatedItem = itemRepository.save(existingItem);

            return convertToDTO(updatedItem);
        }

        return null;
    }

    // Delete Item
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    // Search Item By Name
    public List<ItemDTO> searchByName(String name) {
        return itemRepository.findByItemNameContainingIgnoreCase(name)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Filter Item By Category
    public List<ItemDTO> filterByCategory(String category) {
        return itemRepository.findByCategoryIgnoreCase(category)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
}