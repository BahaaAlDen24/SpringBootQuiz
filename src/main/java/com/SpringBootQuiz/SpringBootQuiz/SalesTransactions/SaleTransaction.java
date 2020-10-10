package com.SpringBootQuiz.SpringBootQuiz.SalesTransactions;

import com.SpringBootQuiz.SpringBootQuiz.Products.Product;
import com.SpringBootQuiz.SpringBootQuiz.SalesOperations.SaleOperation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class SaleTransaction {
    private @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    @JsonBackReference
    private SaleOperation saleOperation;

    @Audited
    private int quantity;
    @Audited
    private int unitPrice;
    @Audited
    private int totalPrice;

    @CreationTimestamp
    private LocalDateTime createDateTime;
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public SaleOperation getSaleOperation() {
        return saleOperation;
    }

    public void setSaleOperation(SaleOperation saleOperation) {
        this.saleOperation = saleOperation;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleTransaction that = (SaleTransaction) o;
        return quantity == that.quantity &&
                unitPrice == that.unitPrice &&
                totalPrice == that.totalPrice &&
                id.equals(that.id) &&
                product.equals(that.product) &&
                saleOperation.equals(that.saleOperation) &&
                Objects.equals(createDateTime, that.createDateTime) &&
                Objects.equals(updateDateTime, that.updateDateTime);
    }


    @Override
    public String toString() {
        return "SaleTransaction{" +
                "id=" + id +
                ", product=" + product.toString() +
                ", saleOperation=" + saleOperation.toString() +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", totalPrice=" + totalPrice +
                ", createDateTime=" + createDateTime +
                ", updateDateTime=" + updateDateTime +
                '}';
    }
}
