package com.example.graphiscord;

import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {
    private Stage stage;
    private Scene scene;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField emailOrUsername;
    @FXML
    private TextField password;
    @FXML
    private TextField email;
    @FXML
    private TextField userName;


    @FXML
    void LoginButtonPressed(ActionEvent event) {
        //login request here with param1:emailOrUsername and param2:password
        System.out.println("Login button pressed");
    }
    @FXML
    void registrationButtonPressed(ActionEvent event) throws IOException {
        //close current window and open signUp window
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signup-view.fxml")));
        stage= (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void loginPageButtonPressed(ActionEvent event) throws IOException {
        //close current window and open login window
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("login-view.fxml")));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene= new Scene(root);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void signUpButtonPressed(ActionEvent event) {
        //sign up request here with param1:email and param2:userName and param3:password
        System.out.println("Sign up button pressed");
    }

    @FXML
    void initialize() {
        assert emailOrUsername != null : "fx:id=\"emailOrUsername\" was not injected: check your FXML file 'login-view.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'login-view.fxml'.";

    }

}