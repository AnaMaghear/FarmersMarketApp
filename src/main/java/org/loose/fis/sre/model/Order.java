package org.loose.fis.sre.model;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

import java.util.Objects;

public class Order {
    @Id
    private NitriteId id;
    private Product product;
    private Consumer consumer;
    private double quantity;
    private double totalprice;
    private OrderStatusEnum status;
    private String deliveryMethod;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(order.quantity, quantity) == 0 && Double.compare(order.totalprice, totalprice) == 0 && Objects.equals(id, order.id) && Objects.equals(product, order.product) && Objects.equals(consumer, order.consumer) && status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, consumer, quantity, totalprice, status);
    }

    public NitriteId getId() {
        return id;
    }

    public void setId(NitriteId id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public Order() {
    }

    public Order(Product product, Consumer consumer, double quantity, double totalprice, OrderStatusEnum status, String deliveryMethod) {
        this.product = product;
        this.consumer = consumer;
        this.quantity = quantity;
        this.totalprice = totalprice;
        this.status = status;
        this.deliveryMethod = deliveryMethod;
    }
}
