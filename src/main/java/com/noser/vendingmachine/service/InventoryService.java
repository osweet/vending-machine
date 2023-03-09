package com.noser.vendingmachine.service;

import com.noser.vendingmachine.model.InventoryItem;
import com.noser.vendingmachine.model.Product;
import com.noser.vendingmachine.model.VendingMachine;
import com.noser.vendingmachine.repository.InventoryItemRepository;
import com.noser.vendingmachine.repository.ProductRepository;
import com.noser.vendingmachine.repository.VendingMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VendingMachineRepository vendingMachineRepository;



    public List<InventoryItem> getInventoryForMachine(String machineId) {
        return inventoryItemRepository.getInventoryItemsByVendingMachineId(machineId);
    }

    @Transactional
    public void addProductToMachine(String machineId, InventoryItem inventoryItem) {
        inventoryItemRepository.save(inventoryItem);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void removeProductFromMachine(String machineId, InventoryItem inventoryItem) {
        inventoryItemRepository.delete(inventoryItem);
    }
    public void removeProductFromMachine(String machineId, String productId, final int quantity) {


        /**
         * 1. Get all inventory items for the machine
         * 2. Filter by productId
         * 3. Sort by bestBefore
         * 4. Remove quantity from the first item
         * 5. If quantity is 0, remove the item
         * 6. If quantity is > 0, add the item to a new list
         * 7. If quantity is < 0, remove the item and add the remaining quantity to the next item
         */


        List<InventoryItem> inventoryItemsList = inventoryItemRepository.getInventoryItemsByVendingMachineId(machineId);
        List<InventoryItem> inventoryItemsListNew = new ArrayList<>();
        List<InventoryItem> inventoryItemsListSorted = new ArrayList<>();

        inventoryItemsListSorted = inventoryItemsList.stream()
                .filter(item -> item.getProductId().equals(productId))
                .sorted(Comparator.comparing(InventoryItem::getBestBefore))
                .collect(Collectors.toList());


//                .forEach(item -> {
//                    if (item.getQuantity() > quantity) {
//                        item.setQuantity(item.getQuantity() - quantity);
//                        inventoryItemsListNew.add(item);
//                    } else if (item.getQuantity() == quantity) {
//                    } else { // item.getQuantity() < quantity
//                        item.setQuantity(0);
//                        inventoryItemsList.remove(item);
//                    }
//                });
        //Group


        // Remove items from inventoryItemList which match the productId

    }

    public VendingMachine createMachine(VendingMachine vendingMachine) {
        return vendingMachineRepository.save(vendingMachine);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}
