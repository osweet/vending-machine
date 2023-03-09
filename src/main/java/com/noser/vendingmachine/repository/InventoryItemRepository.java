package com.noser.vendingmachine.repository;

import com.noser.vendingmachine.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, InventoryItem.InventoryItemId> {

    public List<InventoryItem> getInventoryItemsByVendingMachineId(String vendingMachineId);
}
