package com.SpringBootQuiz.SpringBootQuiz.SalesOperations;

import com.SpringBootQuiz.SpringBootQuiz.Clients.Client;
import com.SpringBootQuiz.SpringBootQuiz.SaleTransaction.SaleTransaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class SaleOperation {
    private @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;

    @ManyToOne
    private Client client;
    private String seller;
    private int totalPrice;

    @OneToMany(mappedBy = "saleOperation", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SaleTransaction> saleTransactions ;

    @CreationTimestamp
    private LocalDateTime createDateTime;
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
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

    public List<SaleTransaction> getSaleTransactions() {
        return saleTransactions;
    }

    public void setSaleTransactions(List<SaleTransaction> saleTransactions) {
        this.saleTransactions = saleTransactions;
    }

    public int calculateTotalPrice(){
        return this.getSaleTransactions().stream().mapToInt(SaleTransaction::getTotalPrice).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleOperation that = (SaleOperation) o;
        return totalPrice == that.totalPrice &&
                id.equals(that.id) &&
                client.equals(that.client) &&
                seller.equals(that.seller) &&
                Objects.equals(saleTransactions, that.saleTransactions) &&
                Objects.equals(createDateTime, that.createDateTime) &&
                Objects.equals(updateDateTime, that.updateDateTime);
    }

    @Override
    public String toString() {
        return "SaleOperation{" +
                "id=" + id +
                ", client=" + client.toString() +
                ", seller='" + seller + '\'' +
                ", totalPrice=" + totalPrice +
                ", createDateTime=" + createDateTime +
                ", updateDateTime=" + updateDateTime +
                '}';
    }
}
