package com.example.market_api.service;

import com.example.market_api.data.Inventory.CreateInventory;
import com.example.market_api.data.Inventory.EditInventory;
import com.example.market_api.data.Inventory.InventoryView;
import com.example.market_api.data.Product.CreateProduct;
import com.example.market_api.data.Product.EditProduct;
import com.example.market_api.data.Product.ProductView;
import com.example.market_api.entity.Category;
import com.example.market_api.entity.Inventory;
import com.example.market_api.entity.Product;
import com.example.market_api.entity.Supplier;
import com.example.market_api.exception.ErrorCode;
import com.example.market_api.exception.NotFoundApiException;
import com.example.market_api.repository.InventoryRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service

public class InventoryService {

    private InventoryRepository inventoryRepository;

    public Inventory createInventory(CreateInventory createInventory) {
        String methodName = "createInventory";
        log.info("Execute method->" + methodName);

        Inventory inventory = new Inventory();
        try {
            inventory.setDateArrived(createInventory.getDatearrived());
            inventory.setQuantity(createInventory.getQuantity());
            inventory.setExpirationDate(createInventory.getExpirationDate());

        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage());

        }
        return inventoryRepository.save(inventory);
    }

    public Optional<Inventory> getInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    public Inventory updateInventory(Long id, EditInventory editInventory) {
        if (inventoryRepository.existsById(id)) {
            Inventory inventory = new Inventory();
            try {
                inventory.setId(id);
                inventory.setDateArrived(editInventory.getDatearrived());
                inventory.setQuantity(editInventory.getQuantity());
                inventory.setExpirationDate(editInventory.getExpirationDate());


                return inventoryRepository.save(inventory);

            } catch (Exception e) {
                log.info("not fount with id " + id);
                throw new RuntimeException(e.getLocalizedMessage());
            }

        }
        return null;
    }

    public boolean deleteInventory(Long id) {
        if (inventoryRepository.existsById(id)) {
            inventoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<InventoryView> getAllInventorys(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Inventory> inventoryPage = inventoryRepository.findAll(pageable);
        return inventoryPage.map(inventory -> new InventoryView(
                inventory.getQuantity(),
                inventory.getDateArrived(),
                inventory.getExpirationDate(),
                inventory.getLastUpdated()


        ));
    }
}
