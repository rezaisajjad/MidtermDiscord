package com.example.graphiscord;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SettingViewController {
    @FXML
    private MenuItem stateIV;
    @FXML
    private MenuItem stateDND;
    @FXML
    private MenuItem stateIdle;
    @FXML
    private MenuItem stateOnline;
    // fields start here
        @FXML
        private TextField phoneTextField;
        @FXML
        private Label phoneValidity;
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
    // fields end here

    @FXML
    void changePicButton(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        //check file chooser if it's null

        profilePic.setFill(new ImagePattern(new Image(fileChooser.showOpenDialog(null).toURI().toString())));
    }

    @FXML
    void emailEditButton(ActionEvent event) {
        //check email validity and if it's not already in use then change it
        if (emailTextField.getText().matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")) {
            emailValidity.setVisible(true);
            emailValidity.setText("Valid");
            emailValidity.setStyle("-fx-text-fill: green;");
            //ToDO: check if email is already in use
            //ToDO: change email in server

        } else {
            emailValidity.setText("Invalid");
            emailValidity.setStyle("-fx-text-fill: red;");
        }


    }



    @FXML
    void exitSettings(ActionEvent event) throws IOException {
        changeView(event,"main-view.fxml");
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


    public void phoneEditButton(ActionEvent actionEvent) {

    }

    public void logoutButton(ActionEvent actionEvent) throws IOException {
        changeView(actionEvent,"login-view.fxml");
        HelloApplication.person = null;
    }
    private void changeView(ActionEvent event, String name) {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(name)));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


