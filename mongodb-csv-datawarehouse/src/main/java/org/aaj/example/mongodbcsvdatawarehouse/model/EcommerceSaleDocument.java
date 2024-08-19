package org.aaj.example.mongodbcsvdatawarehouse.model;

import com.opencsv.bean.CsvBindByName;
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
    @CsvBindByName(column = "ProductID")
    private Integer productID;
    @CsvBindByName(column = "ProductName")
    private String productName;
    @CsvBindByName(column = "Category")
    private String category;
    @CsvBindByName(column = "Price")
    private Double price;
    @CsvBindByName(column = "Rating")
    private Double rating;
    @CsvBindByName(column = "NumReviews")
    private Integer numReviews;
    @CsvBindByName(column = "StockQuantity")
    private Integer stockQuantity;
    @CsvBindByName(column = "Discount")
    private Double discount;
    @CsvBindByName(column = "Sales")
    private Integer sales;
    @CsvBindByName(column = "DateAdded")
    private String dateAdded;
    @CsvBindByName(column = "City")
    private String city;
    @CsvBindByName(column = "Stock In Vast or Limited Quantity")
    private Boolean isStockVast;
}
