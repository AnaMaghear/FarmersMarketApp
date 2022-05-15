package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.exceptions.EmptyFieldsException;
import org.loose.fis.sre.exceptions.NotANumberException;
import org.loose.fis.sre.model.Farmer;
import org.loose.fis.sre.model.Product;
import org.loose.fis.sre.services.FarmerService;
import org.loose.fis.sre.services.UserNameTransporterService;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class AddProductFormController {
    @FXML
    private TextField username;
    @FXML
    private TextField name;
    @FXML
    private TextArea description;
    @FXML
    private TextField quantity;
    @FXML
    private TextField price;
    @FXML
    private Button addProductButton;
    @FXML
    private Button backButton;
    @FXML
    private Text errorMessage;

    public void initialize() {
        username = UserNameTransporterService.getUsername();
    }

    public void handleAddAction() {
        try {
            FarmerService.addProductToFarmer(username.getText(), name.getText(), description.getText(), quantity.getText(), price.getText());

            Main m = new Main();
            m.changeScene("farmerProductsList.fxml");
        } catch (EmptyFieldsException | IOException | NotANumberException ex) {
            errorMessage.setText(ex.getMessage());
        }
    }

    public void handleBackAction() {
        try {
            Main m = new Main();
            m.changeScene("farmerProductsList.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }
}
