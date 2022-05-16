package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.exceptions.DeclinedOrderCanNotDeliverException;
import org.loose.fis.sre.model.Order;
import org.loose.fis.sre.model.OrderStatusEnum;
import org.loose.fis.sre.services.ConsumerService;
import org.loose.fis.sre.services.OrderIdTransporterService;
import org.loose.fis.sre.services.OrderService;

import java.io.IOException;

public class FarmerOrderDetailsController {
    @FXML
    private Text productName;
    @FXML
    private Text quantity;
    @FXML
    private Text deliveryMethod;
    @FXML
    private Text totalPrice;
    @FXML
    private Text consumerName;
    @FXML
    private Button acceptButton;
    @FXML
    private Button rejectButton;
    @FXML
    private Button backButton;
    @FXML
    private Text errorMessage;
    Order order;

    @FXML
    public void initialize() {
        order = OrderService.getOrderById(OrderIdTransporterService.getId());
        productName.setText(order.getProduct().getName());
        quantity.setText(String.valueOf(order.getProduct().getQuantity()));
        deliveryMethod.setText(order.getDeliveryMethod());
        totalPrice.setText(String.valueOf(order.getTotalprice()));
        consumerName.setText(ConsumerService.getConsumerByOrderId(order.getId()).getFirstName() + " " + ConsumerService.getConsumerByOrderId(order.getId()).getLastName());
    }

    @FXML
    public void handleAcceptAction() {
        try {
            OrderService.changeOrderStatus(order.getId(), OrderStatusEnum.Accepted);
            Main m = new Main();
            m.changeScene("farmerPendingOrders.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleRejectAction() {
        try {
            OrderService.changeOrderStatus(order.getId(), OrderStatusEnum.Declined);
            Main m = new Main();
            m.changeScene("farmerPendingOrders.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleBackAction() {
        try {
            Main m = new Main();
            m.changeScene("farmerPendingOrders.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }
}
