package com.noser.vendingmachine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class VendingMachine {

    @JsonProperty(value = "id")
    private String id;

    // Cannot be null
    @JsonProperty(value = "address")
    private String address;

    @JsonProperty(value = "inventory")
    private List<InventoryItem> inventory;

    @JsonProperty(value = "status")
    private Status status;
}
