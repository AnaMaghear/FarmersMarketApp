package org.loose.fis.sre.model;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Farmer {

    @Id
    private String username;
    private String firstName;
    private String lastName;
    private String description;
    private String address;
    private String phone;
    private boolean availabilityStatus;
    private ArrayList<Product> products;

    private ArrayList<Order> pendingOrders;
    private ArrayList<Order> orderHistory;

    public ArrayList<Product> getProducts() {
        return products;
    }

    public Farmer(String username, String firstName, String lastName, String description, String address, String phone, boolean availabilityStatus) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.address = address;
        this.phone = phone;
        this.availabilityStatus = availabilityStatus;
        
        products = new ArrayList<>();
        pendingOrders = new ArrayList<>();
        orderHistory = new ArrayList<>();
    }

    public ArrayList<Order> getPendingOrders() {
        return pendingOrders;
    }

    public ArrayList<Order> getOrderHistory() {
        return orderHistory;
    }

    public Farmer() {
        products = new ArrayList<>();
        pendingOrders = new ArrayList<>();
        orderHistory = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(boolean availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Farmer farmer = (Farmer) o;
        return availabilityStatus == farmer.availabilityStatus && username.equals(farmer.username) && firstName.equals(farmer.firstName) && lastName.equals(farmer.lastName) && description.equals(farmer.description) && address.equals(farmer.address) && phone.equals(farmer.phone) && products.equals(farmer.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstName, lastName, description, address, phone, availabilityStatus, products);
    }

    public void addProduct(Product p) {
        products.add(p);
    }

    public void updateProduct(NitriteId id, Product editedProduct) {
        for (Product p : products)
            if (Objects.equals(id, p.getId()))
                products.set(products.indexOf(p), editedProduct);
    }

    public void removeProduct(NitriteId id) {
        products.removeIf(p -> Objects.equals(p.getId(), id));
    }

    public void addOrderToFarmer(Order o) { pendingOrders.add(o); }

    public void changeOrderStatus(NitriteId id, Order edited) {
        pendingOrders.removeIf(o -> Objects.equals(o.getId(), id));

        orderHistory.add(edited);
    }

    @Override
    public String toString() {
        return "" + firstName + " " +  lastName + "; " + address;
    }
}
