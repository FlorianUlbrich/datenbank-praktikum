package de.uni_leipzig.dbprak.entity;

import javax.persistence.*;

@Entity
public class Order {

    @Id
    @JoinColumn(name = "customer.id")
    private Long customerId;

    @JoinColumn(name = "product_id")
    private Long productId;

    private int amount;

    public Order() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
