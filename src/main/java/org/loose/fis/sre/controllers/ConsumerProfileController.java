package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.loose.fis.sre.model.Product;
import org.loose.fis.sre.services.ProductService;

import java.util.ArrayList;

public class ConsumerProfileController {
    @FXML
    private ChoiceBox selectChoiceBox;
    @FXML
    private TextField searchBar;
    @FXML
    private Button searchButton;
    @FXML
    private Button viewHistoryButton;
    @FXML
    private ListView<Product> listView;

    @FXML
    public void initialize(){
        selectChoiceBox.getItems().addAll("product", "county");
    }

    @FXML
    public void handleSearchAction(){
        ArrayList<Product> products;
        products = ProductService.filter(searchBar.getText(), (String)selectChoiceBox.getValue());
        listView.getItems().clear();
        listView.getItems().addAll(products);
    }


}
