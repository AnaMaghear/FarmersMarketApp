package org.loose.fis.sre.services;

import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.EmptyFieldsException;
import org.loose.fis.sre.exceptions.NotANumberException;
import org.loose.fis.sre.exceptions.QuantityNotAvailableException;
import org.loose.fis.sre.model.*;

public class OrderService {
    private static ObjectRepository<Order> orderRepository = UserService.getOrderRepository();

    public static void addOrder(Product p, Consumer c, String quantity) throws NotANumberException, QuantityNotAvailableException, EmptyFieldsException {
        checkQuantity(p, quantity);
        double q = Double.parseDouble(quantity);
        orderRepository.insert(new Order(p, c, Double.parseDouble(quantity), p.getPricePerUnit() * Double.parseDouble(quantity), OrderStatusEnum.Pending));
        ProductService.updateProductById(p.getId(), p.getName(), p.getDescription(), String.valueOf(p.getQuantity() - q), String.valueOf(p.getPricePerUnit()));

        if (p.getQuantity() == 0)
            ProductService.removeProduct(p.getId());
    }

    private static void checkQuantity(Product p, String quantity) throws NotANumberException, QuantityNotAvailableException {
        double q;
        try {
            q = Double.parseDouble(quantity);
        } catch (NumberFormatException ex) {
            throw new NotANumberException();
        }

        if (p.getQuantity() < q)
            throw new QuantityNotAvailableException();
    }
}
