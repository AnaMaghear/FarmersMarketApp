package org.loose.fis.sre.services;

import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.EmptyFieldsException;
import org.loose.fis.sre.exceptions.NotANumberException;
import org.loose.fis.sre.exceptions.QuantityNotAvailableException;
import org.loose.fis.sre.model.*;

import java.util.ArrayList;
import java.util.Objects;

public class OrderService {
    private static ObjectRepository<Order> orderRepository = UserService.getOrderRepository();
    public static Order addOrder(Product p, String c, String quantity, String deliveryMethod) throws NotANumberException, QuantityNotAvailableException, EmptyFieldsException {
        checkQuantity(p, quantity);
        double q = Double.parseDouble(quantity);
        Order o = new Order(p, c, Double.parseDouble(quantity), p.getPricePerUnit() * Double.parseDouble(quantity), OrderStatusEnum.Pending, deliveryMethod);
        orderRepository.insert(o);
        ProductService.updateProductById(p.getId(), p.getName(), p.getDescription(), String.valueOf(p.getQuantity() - q), String.valueOf(p.getPricePerUnit()));

        return o;
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

    public static ArrayList<Order> getAllOrdersByUsername(String username){
        ArrayList<Order> shownOrders = new ArrayList<Order>();
        for(Order o : orderRepository.find()) {
            if (Objects.equals(o.getConsumer(), username))
                shownOrders.add(o);
        }
        return shownOrders;
    }
}
