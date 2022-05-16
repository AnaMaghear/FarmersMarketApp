package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import org.dizitart.no2.NitriteId;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.model.Order;
import org.loose.fis.sre.model.Product;
import org.loose.fis.sre.services.OrderIdTransporterService;

import java.io.IOException;

public class OrderDetailsController {
    private NitriteId orderId;
    @FXML
    private Text productName;
    @FXML
    private Text quantity;
    @FXML
    private Text totalPrice;
    @FXML
    private Text deliveryMethod;
    @FXML
    private ChoiceBox orderStatus;
    @FXML
    private Text errorMessage;
    @FXML
    private Button backButton;
    @FXML
    public void initialize(){

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



}
