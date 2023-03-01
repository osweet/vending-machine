package com.noser.vendingmachine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {

    @JsonProperty(value = "id")
    private String id;

    // does not make sense to be null
    @JsonProperty(value = "name")
    private String name;

    // Assumed there are no products with price per unit (liter / kg)
    // cannot be null, should be positive
    @JsonProperty(value = "unit_price")
    private Float unitPrice;

}
