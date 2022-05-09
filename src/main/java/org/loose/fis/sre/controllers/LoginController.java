package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.exceptions.EmptyUsernameOrPasswordException;
import org.loose.fis.sre.exceptions.UsernameAndPasswordDoNotMatchException;
import org.loose.fis.sre.services.UserService;

public class LoginController {

    public LoginController() {
    }

    @FXML
    private Text wrongLogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;

    public void handleLogInAction() {
        try {
            UserService.logInUser(username.getText(), password.getText());
            wrongLogin.setText("Logged In successfully");
        } catch (EmptyUsernameOrPasswordException | UsernameAndPasswordDoNotMatchException ex) {
            wrongLogin.setText(ex.getMessage());
        }
    }
}
