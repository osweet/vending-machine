package com.noser.vendingmachine.repository;

import com.noser.vendingmachine.model.VendingMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendingMachineRepository extends JpaRepository<VendingMachine, Integer> {
}
