package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import org.dizitart.no2.NitriteId;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.exceptions.DeclinedOrderCanNotDeliverException;
import org.loose.fis.sre.model.Order;
import org.loose.fis.sre.model.OrderStatusEnum;
import org.loose.fis.sre.model.Product;
import org.loose.fis.sre.services.OrderIdTransporterService;
import org.loose.fis.sre.services.OrderService;

import java.io.IOException;

public class OrderDetailsController {
    private NitriteId orderId;
    private Order order;
    @FXML
    private Text productName;
    @FXML
    private Text quantity;
    @FXML
    private Text totalPrice;
    @FXML
    private Text deliveryMethod;
    @FXML
    private Button orderStatus;
    @FXML
    private Text errorMessage;
    @FXML
    private Button backButton;
    @FXML
    public void initialize(){
        orderId = OrderIdTransporterService.getId();
        order = OrderService.getOrderById(orderId);
        productName.setText(order.getProduct().getName());
        quantity.setText(String.valueOf(order.getQuantity()));
        totalPrice.setText(String.valueOf(order.getTotalprice()));
        deliveryMethod.setText(order.getDeliveryMethod());
    }

    @FXML
    public void handleBackButton(){
        try{
            Main m = new Main();
            m.changeScene("viewHistory.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleOrderStatus(){
        try{
            if(OrderService.getOrderById(orderId).getStatus() != OrderStatusEnum.Accepted)
                throw new DeclinedOrderCanNotDeliverException();
            OrderService.changeOrderStatus(orderId, OrderStatusEnum.Delivered);
            Main m = new Main();
            m.changeScene("viewHistory.fxml");
        } catch (DeclinedOrderCanNotDeliverException | IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }


}
