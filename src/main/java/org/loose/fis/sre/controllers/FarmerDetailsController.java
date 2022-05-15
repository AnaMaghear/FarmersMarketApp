package org.loose.fis.sre.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.model.Farmer;
import org.loose.fis.sre.model.Product;
import org.loose.fis.sre.services.FarmerService;
import org.loose.fis.sre.services.FarmerUsernameTransporterService;
import org.loose.fis.sre.services.ProductIdTransporterService;
import org.loose.fis.sre.services.UserNameTransporterService;

import java.io.IOException;
import java.util.ArrayList;


public class FarmerDetailsController {
    @FXML
    private Text lastname;
    @FXML
    private Text firstname;
    @FXML
    private Text description;
    @FXML
    private Text address;
    @FXML
    private Text phoneNumber;
    @FXML
    private ListView<Product> listView;
    @FXML
    private Button backButton;
    @FXML
    private Text errorMessage;
    private String username;
    @FXML
    public void initialize() {
        username = FarmerUsernameTransporterService.getUsername();
        Farmer f = FarmerService.getFarmerByUsername(username);
        lastname.setText(f.getLastName());
        firstname.setText(f.getFirstName());
        description.setText(f.getDescription());
        address.setText(f.getAddress());
        phoneNumber.setText(f.getPhone());

        listView.getItems().clear();
        ArrayList<Product> products = FarmerService.getAllProductsByUsername(username);

        if (!products.isEmpty()) {
            listView.getItems().addAll(products);

            listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Product>() {
                @Override
                public void changed(ObservableValue<? extends Product> observable, Product oldValue, Product newValue) {
                    Product currentProduct = listView.getSelectionModel().getSelectedItem();
                    ProductIdTransporterService.setProductId(currentProduct.getId());

                    Main m = new Main();
                    try {
                        m.changeScene("buyProduct.fxml");
                    } catch (IOException e) {
                        errorMessage.setText(e.getMessage());
                    }
                }
            });
        }
    }
    @FXML
    public void handleBackButton(){
        try{
            Main m = new Main();
            m.changeScene("consumerProfile.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }

}
