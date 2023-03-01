package com.noser.vendingmachine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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
    private List<InventoryItem> inventory = new ArrayList<>();

    @JsonProperty(value = "status")
    private Status status;
}
