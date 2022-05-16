package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.model.Order;
import org.loose.fis.sre.services.FarmerService;
import org.loose.fis.sre.services.UserNameTransporterService;

import java.io.IOException;
import java.util.ArrayList;

public class FarmerHistoryOrdersController {
    @FXML
    private ListView<Order> ordersHistory;
    @FXML
    private Button backButton;
    @FXML
    private Text errorMessage;

    @FXML
    void initialize() {
        String username = UserNameTransporterService.getUsername().getText();
        ArrayList<Order> orders = FarmerService.getFarmerByUsername(username).getOrderHistory();

        ordersHistory.getItems().clear();
        if (!orders.isEmpty()) {
            ordersHistory.getItems().addAll(orders);
        }
    }

    @FXML
    public void handleBackAction(){
        try{
            Main m = new Main();
            m.changeScene("farmerOrders.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }
}
