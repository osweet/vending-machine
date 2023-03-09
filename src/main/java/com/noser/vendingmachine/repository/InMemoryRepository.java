package com.noser.vendingmachine.repository;

import com.noser.vendingmachine.model.InventoryItem;
import com.noser.vendingmachine.model.Product;
import com.noser.vendingmachine.model.VendingMachine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;


// Singleton
@Slf4j
@Component("InMemoryRepository")
public class InMemoryRepository {

    private static InMemoryRepository instance;

    public static InMemoryRepository getInstance() {
        if(instance == null) {
            instance = new InMemoryRepository();
        }
        return instance;
    }

    private InMemoryRepository() {}


    private Map<UUID, VendingMachine> vendingMachines = new HashMap<>();
    private Map<UUID, Product> products = new HashMap<>();

    public Map<UUID, VendingMachine> getVendingMachines() {
        return vendingMachines;
    }

    public VendingMachine addVendingMachine(VendingMachine vendingMachine) {
        if(vendingMachine.getId() != null) {
            this.vendingMachines.put(UUID.fromString(vendingMachine.getId()), vendingMachine);
            log.info("Added vending machine with Id: " + vendingMachine.getId());
            return vendingMachine;
        }
        else {
            UUID uuid = UUID.randomUUID();
            vendingMachine.setId(uuid.toString());
            this.vendingMachines.put(uuid, vendingMachine);
            log.info("Added vending machine with Id: " + vendingMachine.getId());
            return vendingMachine;
        }
    }

    public void addInventoryToVendingMachine(String vendingMachineId, List<InventoryItem> inventoryItems) {
        VendingMachine vendingMachine = this.vendingMachines.get(UUID.fromString(vendingMachineId));
        if(vendingMachine == null) {
            log.error("Vending machine with Id: " + vendingMachineId + " not found");
            return;
        }
        if(vendingMachine.getInventory() != null) {
            Random random = new Random();
            inventoryItems.forEach(item -> item.setId(random.nextInt(10000 - 1) + 1));
            vendingMachine.getInventory().addAll(inventoryItems);
            log.info("Added inventory to vending machine with Id: " + vendingMachine.getId());
        }
        else {
            vendingMachine.setInventory(inventoryItems);
            log.info("Added inventory to vending machine with Id: " + vendingMachine.getId());
        }
    }

    public void removeVendingMachine(String vendingMachineId) {
        this.vendingMachines.remove(UUID.fromString(vendingMachineId));
        log.info("Removed vending machine with Id: " + vendingMachineId);
    }

    public Map<UUID, Product> getProducts() {
        log.info("Retrieved all products");
        return products;
    }

    public List<Product> getProductList() {
        log.info("Retrieved all products");
        return products.values().stream().toList();
    }

    public Product addProduct(Product product) {
        if(product.getId() != null) {
            this.products.put(UUID.fromString(product.getId()), product);
            return product;
        }
        else {
            UUID uuid = UUID.randomUUID();
            product.setId(uuid.toString());
            this.products.put(uuid, product);
            return product;
        }
    }

}
