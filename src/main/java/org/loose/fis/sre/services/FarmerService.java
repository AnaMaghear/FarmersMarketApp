package org.loose.fis.sre.services;

import javafx.scene.control.Toggle;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.EmptyFieldsException;
import org.loose.fis.sre.exceptions.NotANumberException;
import org.loose.fis.sre.exceptions.QuantityNotAvailableException;
import org.loose.fis.sre.model.Consumer;
import org.loose.fis.sre.model.Farmer;
import org.loose.fis.sre.model.Order;
import org.loose.fis.sre.model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class FarmerService {
    private static ObjectRepository<Farmer> farmerRepository = UserService.getFarmerRepository();
    private static ObjectRepository<Consumer> consumerRepository = UserService.getConsumerRepository();
    public static void addFarmer(String username, String firstName, String lastName, String description, String address, String phone, boolean availabilityStatus) throws EmptyFieldsException {
        checkIfFieldsEmpty(username, firstName, lastName, description, address, phone);
        farmerRepository.insert(new Farmer(username, firstName, lastName, description, address, phone, availabilityStatus));
    }

    private static void checkIfFieldsEmpty(String username, String firstName, String lastName, String description, String address, String phone) throws EmptyFieldsException {
        if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || description.isEmpty() || address.isEmpty() || phone.isEmpty())
            throw new EmptyFieldsException();
    }

    public static ArrayList<Product> getAllProductsByUsername(String username) {
        for (Farmer farmer : farmerRepository.find()) {
            if (Objects.equals(username, farmer.getUsername())) {
                return farmer.getProducts();
            }
        }

        return new ArrayList<>();
    }

    public static void addProductToFarmer(String username, String name, String description, String quantity, String price) throws EmptyFieldsException, NotANumberException {
        Product p = ProductService.addProduct(name, description, quantity, price);

        for (Farmer farmer : farmerRepository.find()) {
            if (Objects.equals(username, farmer.getUsername())) {
                farmer.addProduct(p);

                farmerRepository.update(farmer);
            }
        }
    }

    public static void addOrderToFarmer(Product p, Consumer c, String quantity, String deliveryMethod) throws NotANumberException, QuantityNotAvailableException, EmptyFieldsException {
        Order o = OrderService.addOrder(p, c.getUsername(), quantity, deliveryMethod);

        Farmer f = getFarmerByProductId(p.getId());
        f.addOrderToFarmer(o);
        c.addOrderToConsumer(o);
        farmerRepository.update(f);
        consumerRepository.update(c);

    }

    public static Farmer getFarmerByUsername(String username){
        for(Farmer f : farmerRepository.find())
            if(Objects.equals(f.getUsername(), username))
                return f;
        return new Farmer();
    }

    public static Farmer getFarmerByProductId(NitriteId id) {
        for (Farmer f : farmerRepository.find()) {
            if (f.getProducts() != null) {
                for (Product p : f.getProducts())
                    if (Objects.equals(id, p.getId())) {
                        return f;
                    }
            }
        }

        return new Farmer();
    }

    public static void updateFarmerByUsername(String username, String firstName, String lastName, String description, String address, String phone, boolean status) throws EmptyFieldsException {
            checkIfFieldsEmpty(username, firstName, lastName, description, address, phone);
            for (Farmer f : farmerRepository.find()) {
                if (Objects.equals(username, f.getUsername())) {
                    f.setFirstName(firstName);
                    f.setLastName(lastName);
                    f.setDescription(description);
                    f.setAddress(address);
                    f.setPhone(phone);
                    f.setAvailabilityStatus(status);

                    farmerRepository.update(f);
                }
        }
    }
  
    public static ArrayList<Farmer> filter(String search, String filterBy) {
        ArrayList<Farmer> shownFarmers = new ArrayList<Farmer>();
        if (filterBy.equals("product")) {
            for(Farmer f : farmerRepository.find()) {
                if (f.getProducts() != null && f.isAvailabilityStatus())
                    for (Product p : f.getProducts())
                        if (p.getName().contains(search) && p.getQuantity() > 0) {
                            shownFarmers.add(f);
                            break;
                        }
            }
        } else if (filterBy.equals("county")) {
            for(Farmer f : farmerRepository.find()) {
                if (f.getProducts() != null && f.isAvailabilityStatus())
                    if (f.getAddress().contains(search)) {
                        shownFarmers.add(f);
                    }
            }
        }
        return shownFarmers;
    }

}
