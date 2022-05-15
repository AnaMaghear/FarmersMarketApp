package org.loose.fis.sre.controllers;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.model.Product;
import org.loose.fis.sre.services.FarmerService;
import org.loose.fis.sre.services.UserNameTransporterService;

import java.io.IOException;
import java.util.*;

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
        ArrayList<Product> products = FarmerService.getAllProductsByUsername(username.getText());

        if (!products.isEmpty())
            productsList.getItems().addAll(products);
    }

    @FXML
    public void handleAddProductAction() {
        try {
            Main m = new Main();
            m.changeScene("addProductForm.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
