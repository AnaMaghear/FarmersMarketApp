package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.loose.fis.sre.model.Product;

public class FarmerProductsListController {
    @FXML
    private TextField username;
    @FXML
    private ListView<Product> productsList;
    @FXML
    private Button addProduct;
}
