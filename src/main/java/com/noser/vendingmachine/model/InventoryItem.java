package com.noser.vendingmachine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class InventoryItem {

    // Cannot be null
    @JsonProperty(value = "product_id")
    private String productId;

    @JsonProperty(value = "quantity")
    @NotNull
    @Max(value = 10)
    private Integer quantity;

    // Cannot be null, should be later than current time
    @JsonProperty(value = "best_before")
    @NotNull
    private LocalDateTime bestBefore;

    // Cannot be null
    @JsonProperty(value = "machine_id")
    @NotEmpty
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
}
