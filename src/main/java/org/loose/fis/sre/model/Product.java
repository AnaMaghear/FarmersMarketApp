package org.loose.fis.sre.model;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

import java.text.MessageFormat;
import java.util.Objects;

public class Product {

    @Id
    private NitriteId id;
    private String name;
    private String description;
    private double quantity;
    private double pricePerUnit;

    public Product(String name, String description, double quantity, double pricePerUnit) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    public Product() {
    }

    public NitriteId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Double.compare(product.quantity, quantity) == 0 && Double.compare(product.pricePerUnit, pricePerUnit) == 0 && name.equals(product.name) && description.equals(product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, quantity, pricePerUnit);
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}; {1}; {2} RON/unitate", name, description, pricePerUnit);
    }
}
