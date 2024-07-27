package com.app.ecommerce.Product_Service.Model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
@Document(value = "product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
