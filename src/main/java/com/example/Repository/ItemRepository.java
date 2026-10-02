package com.example.lostfound.repository;

import com.example.lostfound.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    // Search by item name
    List<Item> findByItemNameContainingIgnoreCase(String itemName);

    // Filter by category
    List<Item> findByCategoryIgnoreCase(String category);
}