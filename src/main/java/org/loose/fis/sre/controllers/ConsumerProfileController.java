package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.model.Farmer;
import org.loose.fis.sre.model.Product;
import org.loose.fis.sre.services.ProductService;

import java.io.IOException;
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
    private ListView<Farmer> listView;
    @FXML
    private Text errorMessage;

    @FXML
    public void initialize(){
        selectChoiceBox.getItems().addAll("product", "county");
    }

    @FXML
    public void handleSearchAction(){
        errorMessage.setText("");
        try{
            if(selectChoiceBox.getSelectionModel().isEmpty())
                throw new Exception("No filter selected");
            ArrayList<Farmer> farmers = new ArrayList<Farmer>();
            farmers = ProductService.filter(searchBar.getText(), (String)selectChoiceBox.getSelectionModel().getSelectedItem());
            listView.getItems().clear();
            listView.getItems().addAll(farmers);
        } catch (Exception e) {
            errorMessage.setText(e.getMessage());
        }

    }

    @FXML
    public void handleViewHistory(){
        try{
            Main m = new Main();
            m.changeScene("viewHistory.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void handleEditProfile(){
        try{
            Main m = new Main();
            m.changeScene("editConsumer.fxml");
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
