package org.aaj.example.mongodbcsvdatawarehouse.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collation = "ecommerce-sales")
@Getter
@Setter
@Builder
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
    private LocalDate dateAdded;
    private String city;
    private Boolean isStockVast;
}
