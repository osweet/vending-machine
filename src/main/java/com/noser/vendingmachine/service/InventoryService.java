package com.noser.vendingmachine.service;

import com.noser.vendingmachine.model.InventoryItem;
import com.noser.vendingmachine.model.Product;
import com.noser.vendingmachine.model.VendingMachine;
import com.noser.vendingmachine.repository.InMemoryRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class InventoryService {

    private final InMemoryRepository inMemoryRepository = InMemoryRepository.getInstance();

    public List<InventoryItem> getInventoryForMachine(String machineId) {
        return inMemoryRepository.getVendingMachines().get(machineId).getInventory();
    }

    public void addProductToMachine(String machineId, InventoryItem inventoryItem) {
        inMemoryRepository.addInventoryToVendingMachine(machineId, Collections.singletonList(inventoryItem));
    }

    public void removeProductFromMachine(String machineId, InventoryItem inventoryItem) {
        inMemoryRepository.getVendingMachines().get(machineId).getInventory().remove(inventoryItem);
    }

    public VendingMachine createMachine(VendingMachine vendingMachine) {
        return inMemoryRepository.addVendingMachine(vendingMachine);
    }

    public Product createProduct(Product product) {
        return inMemoryRepository.addProduct(product);
    }
}
