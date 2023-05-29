package com.raptor.ecommerceproject.models;

import javax.persistence.*;

@Entity
@Table(name = "details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameOrder;
    private double quantityOrder;
    private double priceOrder;
    private double totalOrder;


    @ManyToOne
    private Order order;



    @ManyToOne
    private Product product;


    public OrderDetail(Long id, String nameOrder, double quantityOrder, double priceOrder, double totalOrder) {
        this.id = id;
        this.nameOrder = nameOrder;
        this.quantityOrder = quantityOrder;
        this.priceOrder = priceOrder;
        this.totalOrder = totalOrder;
    }

    public OrderDetail() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOrder() {
        return nameOrder;
    }

    public void setNameOrder(String nameOrder) {
        this.nameOrder = nameOrder;
    }

    public double getQuantityOrder() {
        return quantityOrder;
    }

    public void setQuantityOrder(double quantityOrder) {
        this.quantityOrder = quantityOrder;
    }

    public double getPriceOrder() {
        return priceOrder;
    }

    public void setPriceOrder(double priceOrder) {
        this.priceOrder = priceOrder;
    }

    public double getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(double totalOrder) {
        this.totalOrder = totalOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", nameOrder='" + nameOrder + '\'' +
                ", quantityOrder=" + quantityOrder +
                ", priceOrder=" + priceOrder +
                ", totalOrder=" + totalOrder +
                '}';
    }
}
