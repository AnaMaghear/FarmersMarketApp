package org.loose.fis.sre.services;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.EmptyFieldsException;
import org.loose.fis.sre.exceptions.NotANumberException;
import org.loose.fis.sre.exceptions.QuantityNotAvailableException;
import org.loose.fis.sre.model.*;

import java.util.ArrayList;
import java.util.Objects;

public class OrderService {
    private static ObjectRepository<Order> orderRepository = UserService.getOrderRepository();
    private static ObjectRepository<Farmer> farmerRepository = UserService.getFarmerRepository();

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

    public static Order getOrderById(NitriteId id) {
        for(Order o : orderRepository.find())
            if (Objects.equals(o.getId(), id))
                return o;

        return new Order();
    }

    public static void changeOrderStatus(NitriteId id, OrderStatusEnum status) {
        Order o = OrderService.getOrderById(id);
        o.setStatus(status);
        orderRepository.update(o);

        for (Farmer f : farmerRepository.find()) {
            if (status == OrderStatusEnum.Delivered) {
                for (Order order : f.getOrderHistory())
                    if (Objects.equals(order.getId(), id)) {
                        f.changeOrderStatus(id, o);
                        farmerRepository.update(f);
                    }
            } else {
                for (Order order : f.getPendingOrders())
                    if (Objects.equals(order.getId(), id)) {
                        f.changeOrderStatus(id, o);
                        farmerRepository.update(f);
                    }
            }
        }
    }
}
