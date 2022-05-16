package org.loose.fis.sre.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.model.Order;
import org.loose.fis.sre.model.Product;
import org.loose.fis.sre.services.*;

import java.io.IOException;
import java.util.ArrayList;

public class FarmerPendingOrdersController {
    @FXML
    private ListView<Order> pendingOrders;
    @FXML
    private Text errorMessage;
    @FXML
    private Button backButton;

    @FXML
    public void initialize() {
        String username = UserNameTransporterService.getUsername().getText();
        pendingOrders.getItems().clear();
        ArrayList<Order> orders = FarmerService.getFarmerByUsername(username).getPendingOrders();

        if (!orders.isEmpty()) {
            pendingOrders.getItems().addAll(orders);

            pendingOrders.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Order>() {
                @Override
                public void changed(ObservableValue<? extends Order> observable, Order oldValue, Order newValue) {
                    Order currentOrder = pendingOrders.getSelectionModel().getSelectedItem();
                    OrderIdTransporterService.setId(currentOrder.getId());

                    Main m = new Main();
                    try {
                        m.changeScene("farmerOrderDetails.fxml");
                    } catch (IOException e) {
                        errorMessage.setText(e.getMessage());
                    }
                }
            });
        }
    }

    @FXML
    public void handleBackAction() {
        try {
            Main m = new Main();
            m.changeScene("farmerOrders.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }
}
