package com.noser.vendingmachine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@ToString
@Builder
@Entity(name = "Product")
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @JsonProperty(value = "id")
    private String id;

    // does not make sense to be null
    @JsonProperty(value = "name")
    @NotNull(message = "Product name is required")
    private String name;

    // Assumed there are no products with price per unit (liter / kg)
    // cannot be null, should be positive
    @JsonProperty(value = "unit_price")
    @NotNull(message = "Product unit price is required")
    @Positive(message = "Product unit price must be positive")
    private Float unitPrice;

}
