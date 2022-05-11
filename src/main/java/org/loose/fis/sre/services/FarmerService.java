package org.loose.fis.sre.services;

import javafx.scene.control.Toggle;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.EmptyFieldsException;
import org.loose.fis.sre.model.Farmer;

public class FarmerService {
    private static ObjectRepository<Farmer> farmerRepository = UserService.getFarmerRepository();

    public static void addFarmer(String username, String firstName, String lastName, String description, String address, String phone, boolean availabilityStatus) throws EmptyFieldsException {
        checkIfFieldsEmpty(username, firstName, lastName, description, address, phone);
        farmerRepository.insert(new Farmer(username, firstName, lastName, description, address, phone, availabilityStatus));
    }

    private static void checkIfFieldsEmpty(String username, String firstName, String lastName, String description, String address, String phone) throws EmptyFieldsException {
        if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || description.isEmpty() || address.isEmpty() || phone.isEmpty())
            throw new EmptyFieldsException();
    }
}
