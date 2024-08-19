package org.aaj.example.postgresqletlscheduler.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ecommerce-sales")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EcommerceSaleDocument {

    @Id
    private Integer productID;
    private String productName;
    private String category;
    private Double price;
    private Double rating;
    private Integer numReviews;
    private Integer stockQuantity;
    private Double discount;
    private Integer sales;
    private String dateAdded;
    private String city;
    private Boolean isStockVast;
}
