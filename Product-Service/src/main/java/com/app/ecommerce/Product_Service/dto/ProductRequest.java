package com.app.ecommerce.Product_Service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name must have at least 2 characters")
    private String name;
    @NotNull(message = "Description cannot be null")
    @Size(min = 5, message = "Description must have at least 5 characters")
    private String description;

    @NotNull(message = "Price cannot be null")
    @Min(value = 10000, message = "Min price should be 10000 Rupees")
    @Max(value = 200000, message = "Max price can't be above 2 lakh")
    private BigDecimal price;
}
