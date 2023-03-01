package com.noser.vendingmachine.repository;

import com.noser.vendingmachine.model.InventoryItem;
import com.noser.vendingmachine.model.Product;
import com.noser.vendingmachine.model.VendingMachine;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;
import java.util.Map;
import java.util.UUID;


// Singleton
@Component("InMemoryRepository")
@ApplicationScope
public class InMemoryRepository {

    private static InMemoryRepository instance;

    public static InMemoryRepository getInstance() {
        if(instance == null) {
            instance = new InMemoryRepository();
        }
        return instance;
    }

    private InMemoryRepository() {}


    private Map<UUID, VendingMachine> vendingMachines;
    private Map<UUID, Product> products;

    public Map<UUID, VendingMachine> getVendingMachines() {
        return vendingMachines;
    }

    public VendingMachine addVendingMachine(VendingMachine vendingMachine) {
        if(vendingMachine.getId() != null) {
            this.vendingMachines.put(UUID.fromString(vendingMachine.getId()), vendingMachine);
            return vendingMachine;
        }
        else {
            UUID uuid = UUID.randomUUID();
            vendingMachine.setId(uuid.toString());
            this.vendingMachines.put(uuid, vendingMachine);
            return vendingMachine;
        }
    }

    public VendingMachine addInventoryToVendingMachine(String vendingMachineId, List<InventoryItem> inventoryItems) {
        VendingMachine vendingMachine = this.vendingMachines.get(UUID.fromString(vendingMachineId));
        if(vendingMachine != null) {
            vendingMachine.getInventory().addAll(inventoryItems);
        }
        return vendingMachine;
    }

    public void removeVendingMachine(String vendingMachineId) {
        this.vendingMachines.remove(UUID.fromString(vendingMachineId));
    }

    public Map<UUID, Product> getProducts() {
        return products;
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
