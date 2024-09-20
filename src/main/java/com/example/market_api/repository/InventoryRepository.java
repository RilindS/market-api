package com.example.market_api.repository;

import com.example.market_api.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
