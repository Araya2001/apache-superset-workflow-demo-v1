package org.aaj.example.postgresqletlscheduler.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ecommerce_sale")
@SuperBuilder
public class EcommerceSaleEntity extends BaseEntity {
    @Column(name = "product_name", length = 512)
    private String productName;
    @Column(name = "category", length = 512)
    private String category;
    @Column(name = "price")
    private Double price;
    @Column(name = "rating")
    private Double rating;
    @Column(name = "num_reviews")
    private Integer numReviews;
    @Column(name = "stock_quantity")
    private Integer stockQuantity;
    @Column(name = "discount")
    private Double discount;
    @Column(name = "sales")
    private Integer sales;
    @Column(name = "date_added")
    private LocalDate dateAdded;
    @Column(name = "city", length = 512)
    private String city;
    @Column(name = "is_stock_vast")
    private Boolean isStockVast;


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        EcommerceSaleEntity that = (EcommerceSaleEntity) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + getId() + ", " +
                "productName = " + getProductName() + ", " +
                "category = " + getCategory() + ", " +
                "price = " + getPrice() + ", " +
                "rating = " + getRating() + ", " +
                "numReviews = " + getNumReviews() + ", " +
                "stockQuantity = " + getStockQuantity() + ", " +
                "discount = " + getDiscount() + ", " +
                "sales = " + getSales() + ", " +
                "dateAdded = " + getDateAdded() + ", " +
                "city = " + getCity() + ", " +
                "isStockVast = " + getIsStockVast() + ", " +
                "dateCreate = " + getDateCreate() + ", " +
                "dateUpdate = " + getDateUpdate() + ")";
    }
}