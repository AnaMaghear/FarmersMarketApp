package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

import java.util.Objects;

public class Consumer {
    public Consumer() {
    }

    @Id
    private String username;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;

    public Consumer(String username, String firstName, String lastName, String address, String phone) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return username.equals(consumer.username) && firstName.equals(consumer.firstName) && lastName.equals(consumer.lastName) && address.equals(consumer.address) && phone.equals(consumer.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstName, lastName, address, phone);
    }
}
