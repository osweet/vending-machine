package com.noser.vendingmachine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
@Builder
@Entity(name = "VendingMachine")
@Table(name = "vendor_machine")
@AllArgsConstructor
@NoArgsConstructor
public class VendingMachine {

    @Id
    @JsonProperty(value = "id")
    private String id;

    // Cannot be null
    @JsonProperty(value = "address")
    private String address;

    @JsonProperty(value = "inventory")
    @Builder.Default
    private List<InventoryItem> inventory = new ArrayList<>();

    @JsonProperty(value = "status")
    private Status status;
}
