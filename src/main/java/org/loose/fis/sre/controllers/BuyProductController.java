package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.model.Product;
import org.loose.fis.sre.services.ProductIdTransporterService;
import org.loose.fis.sre.services.ProductService;

import java.io.IOException;

public class BuyProductController {
    @FXML
    private Text productName;
    @FXML
    private Text description;
    @FXML
    private Text quantityAvailable;
    @FXML
    private TextField desiredAmount;
    @FXML
    private Button confirmButton;
    @FXML
    private ChoiceBox pickUp;
    @FXML
    private Button back;
    @FXML
    private  Text errorMessage;
    @FXML
    private Text price;
    @FXML
    public void initialize(){
        pickUp.getItems().addAll("pick-up", "delivery");
        try {
            Product p = ProductService.getProductById(ProductIdTransporterService.getProductId());
            description.setText(p.getDescription());
            quantityAvailable.setText(String.valueOf(p.getQuantity()));
            price.setText(String.valueOf(p.getPricePerUnit()));
        } catch (Exception e) {
            errorMessage.setText(e.getMessage());
        }
    }
    @FXML
    public void handleBackButton(){
        try{
            Main m = new Main();
            m.changeScene("farmerDetails.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }

}
