package com.noser.vendingmachine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@Entity(name = "Inventory")
@Table(name = "inventory")
@IdClass(InventoryItem.InventoryItemId.class)
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItem {


    // Cannot be null
    @Id
    @JsonProperty(value = "product_id")
    @NotEmpty(message = "Product id is required")
    private String productId;

    @JsonProperty(value = "quantity")
    @NotNull
    @Min(value = 1, message = "Quantity must be greater than 1")
    @Max(value = 10, message = "Quantity must be less than")
    private Integer quantity;


    @Id
    @JsonProperty(value = "best_before")
    @Future(message = "Best before date must be in the future")
    private LocalDateTime bestBefore;

    @Id
    @JsonProperty(value = "machine_id")
    @NotEmpty(message = "Machine id is required")
    private String machineId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InventoryItem that = (InventoryItem) o;

        if (this.productId == null || this.machineId == null || this.bestBefore == null) {
            return false;
        }

        if (this.productId.equals(that.productId) && this.machineId.equals(that.machineId) && this.bestBefore.equals(that.bestBefore)) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int result = productId != null ? productId.hashCode() : 0;
        result = 31 * result + (machineId != null ? machineId.hashCode() : 0);
        result = 31 * result + (bestBefore != null ? bestBefore.hashCode() : 0);
        return result;
    }

    @Data
    public static class InventoryItemId implements Serializable {

        private String productId;
        private String machineId;
        private LocalDateTime expiryDate;

    }
}
