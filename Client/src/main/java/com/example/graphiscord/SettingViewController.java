package com.example.graphiscord;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class SettingViewController {

    @FXML
    private TextField emailTextField;

    @FXML
    private Label emailValidity;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label passwordValidity;

    @FXML
    private Circle profilePic;

    @FXML
    private Rectangle state;

    @FXML
    private Label userName;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Label usernameValidity;

    @FXML
    void changePicButton(ActionEvent event) {

    }

    @FXML
    void emailEditButton(ActionEvent event) {

    }

    @FXML
    void exitSettings(ActionEvent event) {

    }

    @FXML
    void passwordEditButton(ActionEvent event) {

    }

    @FXML
    void usernameEditButton(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert emailTextField != null : "fx:id=\"emailTextField\" was not injected: check your FXML file 'setting-view.fxml'.";
        assert emailValidity != null : "fx:id=\"emailValidity\" was not injected: check your FXML file 'setting-view.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'setting-view.fxml'.";
        assert passwordValidity != null : "fx:id=\"passwordValidity\" was not injected: check your FXML file 'setting-view.fxml'.";
        assert profilePic != null : "fx:id=\"profilePic\" was not injected: check your FXML file 'setting-view.fxml'.";
        assert state != null : "fx:id=\"state\" was not injected: check your FXML file 'setting-view.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'setting-view.fxml'.";
        assert usernameTextField != null : "fx:id=\"usernameTextField\" was not injected: check your FXML file 'setting-view.fxml'.";
        assert usernameValidity != null : "fx:id=\"usernameValidity\" was not injected: check your FXML file 'setting-view.fxml'.";

    }
}


