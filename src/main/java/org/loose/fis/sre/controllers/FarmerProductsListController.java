package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.model.Product;
import org.loose.fis.sre.services.UserNameTransporterService;
import org.loose.fis.sre.services.UserService;

import java.util.ArrayList;

public class FarmerProductsListController {
    @FXML
    private TextField username;
    @FXML
    private ListView<Product> productsList;
    @FXML
    private Button addProductButton;
    @FXML
    private Button seeProductsButton;
    @FXML
    private Text errorMessage;
    @FXML
    public void initialize() {
        username = UserNameTransporterService.getUsername();
        productsList.getItems().clear();
        ArrayList<Product> products = UserService.getAllProductsByUsername(username.getText());

        if (products != null)
            productsList.getItems().addAll(products);
    }

    @FXML
    public void handleAddProductAction() {
    }
}
