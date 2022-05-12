package org.loose.fis.sre.services;

import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.EmptyFieldsException;
import org.loose.fis.sre.model.Consumer;
import org.loose.fis.sre.model.Farmer;

public class ConsumerService {
    private static ObjectRepository<Consumer> consumerRepository = UserService.getConsumerRepository();

    public static void addConsumer(String username,String firstName, String lastName, String address, String phone) throws EmptyFieldsException {
        checkIfFieldsEmpty(username, firstName, lastName, address, phone);
        consumerRepository.insert(new Consumer(username, firstName, lastName, address, phone));
    }

    private static void checkIfFieldsEmpty(String username, String firstName, String lastName, String address, String phone) throws EmptyFieldsException {
        if (username.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || phone.isEmpty())
            throw new EmptyFieldsException();
    }

}
