package org.loose.fis.sre.services;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class FarmerUsernameTransporterService {
    @FXML
    private static String username;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        FarmerUsernameTransporterService.username = username;
    }
}
