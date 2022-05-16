package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.loose.fis.sre.Main;

import java.io.IOException;

public class FarmerOrdersController {
    @FXML
    private Button pendingButton;
    @FXML
    private Button historyButton;
    @FXML
    private Button backButton;
    @FXML
    private Text errorMessage;
    @FXML
    public void handlePendingAction() {
        try {
            Main m = new Main();
            m.changeScene("farmerPendingOrders.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }

    public void handleHistoryAction() {
        try {
            Main m = new Main();
            m.changeScene("farmerHistoryOrders.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }

    public void handleBackAction() {
        try {
            Main m = new Main();
            m.changeScene("farmerProfile.fxml");
        } catch (IOException e) {
            errorMessage.setText(e.getMessage());
        }
    }
}
