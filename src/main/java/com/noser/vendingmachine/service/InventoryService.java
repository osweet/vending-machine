package com.noser.vendingmachine.service;

import com.noser.vendingmachine.model.InventoryItem;
import com.noser.vendingmachine.model.Product;
import com.noser.vendingmachine.model.VendingMachine;
import com.noser.vendingmachine.repository.InMemoryRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InventoryService {

    private final InMemoryRepository inMemoryRepository = InMemoryRepository.getInstance();

    public List<InventoryItem> getInventoryForMachine(String machineId) {
        return inMemoryRepository.getVendingMachines().get(UUID.fromString(machineId)) == null
                ? null
                : inMemoryRepository.getVendingMachines().get(UUID.fromString(machineId)).getInventory();
    }

    public void addProductToMachine(String machineId, InventoryItem inventoryItem) {
        inMemoryRepository.addInventoryToVendingMachine(machineId, Collections.singletonList(inventoryItem));
    }

    public List<Product> getProducts() {
        return inMemoryRepository.getProductList();
    }

    public void removeProductFromMachine(String machineId, InventoryItem inventoryItem) {
        inMemoryRepository.getVendingMachines().get(machineId).getInventory().remove(inventoryItem);
    }
    public void removeProductFromMachine(String machineId, String productId, final int quantity) {

        List<InventoryItem> inventoryItemsList = inMemoryRepository.getVendingMachines().get(machineId).getInventory();
        List<InventoryItem> inventoryItemsListNew = new ArrayList<>();
        inventoryItemsList.forEach(item -> {

        });


        inventoryItemsList.stream()
                .filter(item -> item.getProductId().equals(productId))
                .sorted(Comparator.comparing(InventoryItem::getBestBefore))
                        .forEach(item -> {
                            if(item.getQuantity() > quantity) {
                                item.setQuantity(item.getQuantity() - quantity);
                                inventoryItemsListNew.add(item);
                            }
                            else if (item.getQuantity() == quantity) {
                            }
                            else { // item.getQuantity() < quantity
                                item.setQuantity(0);
                                inventoryItemsList.remove(item);
                            }
                        });
        //Group


        // Remove items from inventoryItemList which match the productId



        inMemoryRepository.getVendingMachines().get(machineId).getInventory()
                .stream().filter(item -> item.getProductId().equals(productId))
                .sorted(Comparator.comparing(InventoryItem::getBestBefore))
                .
    }

    public VendingMachine createMachine(VendingMachine vendingMachine) {
        return inMemoryRepository.addVendingMachine(vendingMachine);
    }

    public Product createProduct(Product product) {
        return inMemoryRepository.addProduct(product);
    }
}
