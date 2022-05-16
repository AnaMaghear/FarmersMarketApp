package org.loose.fis.sre.controllers;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.model.Order;
import org.loose.fis.sre.model.Product;
import org.loose.fis.sre.services.OrderIdTransporterService;
import org.loose.fis.sre.services.OrderService;
import org.loose.fis.sre.services.ProductIdTransporterService;
import org.loose.fis.sre.services.UserNameTransporterService;

import java.io.IOException;
import java.util.ArrayList;


public class ViewHistoryController {

    @FXML
    private Button backButton;
    @FXML
    private ListView<Order> listView;
    @FXML
    private Text errorMessage;

    @FXML
    public void handleBackButton(){
        try{
            Main m = new Main();
            m.changeScene("consumerProfile.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void initialize() {
        String username = UserNameTransporterService.getUsername().getText();
        ArrayList<Order> orders = OrderService.getAllOrdersByUsername(username);

        listView.getItems().clear();
        if(!orders.isEmpty()) {
            listView.getItems().addAll(orders);
            listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Order>() {
                @Override
                public void changed(ObservableValue<? extends Order> observable, Order oldValue, Order newValue) {
                    Order currentOrder = listView.getSelectionModel().getSelectedItem();
                    OrderIdTransporterService.setId(currentOrder.getId());

                    Main m = new Main();
                    try {
                        m.changeScene("orderDetails.fxml");
                    } catch (IOException e) {
                        errorMessage.setText(e.getMessage());
                    }
                }
            });
        }
    }
}



