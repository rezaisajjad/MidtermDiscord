package com.example.graphiscord;

import ClientController.InputValidator;
import ClientController.Server;
import code.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloController {
    public Label loginResult;
    public Label signUpResult;
    @FXML
    private Label eORValidity = new Label();
    @FXML
    private Label pValidity=new Label();
    @FXML
    private TextField emailOrUsername;
    @FXML
    private TextField password;
    @FXML
    private TextField email;
    @FXML
    private TextField userName;
    private final Server server = Server.getServer();

    public HelloController() {
    }

    /* login request here with param1:emailOrUsername and param2:password
        if login success, go to main page
        if login failed, show error message
        if login success, save the user info to the server*/
    @FXML
    public void LoginButtonPressed(ActionEvent event) {
        emailOrUsername.setText(emailOrUsername.getText().toLowerCase());
        if (!InputValidator.validateUserName(emailOrUsername.getText())) {
            eORValidity.setVisible(true);
            return;
        }else{
            eORValidity.setVisible(false);
        }
        if(!InputValidator.validatePassword(password.getText())) {
            pValidity.setVisible(true);
            return;
        }else {
            pValidity.setVisible(false);
        }
        HelloApplication.person = server.loginPerson(emailOrUsername.getText(), password.getText());
        if (HelloApplication.person != null) {
            System.out.println("login success");
            changeView(event, "main-view.fxml");
        } else {
            loginResult.setVisible(true);
            password.setText("");
        }
    }
    @FXML
    void registrationButtonPressed(ActionEvent event){
        //close current window and open signUp window
        changeView(event, "signup-view.fxml");
    }

    @FXML
    void loginPageButtonPressed(ActionEvent event){
        //close current window and open login window
        changeView(event, "login-view.fxml");
    }
    @FXML
    void signUpButtonPressed(ActionEvent event) {
        userName.setText(userName.getText().toLowerCase());
        if (!server.checkUserNameAvailability(userName.getText())) {
            signUpResult.setText("Username already exist");
            return;
        }
        if (!validateFields().equals("")) {
            signUpResult.setText("invalid " + validateFields());
            return;
        }
        //////
        Person p = new Person();
        p.setUserName(userName.getText());
        p.setPassWord(password.getText());
        p.setEmail(email.getText());
        //p.setPhoneNumber(phoneNumber.getText());
        HelloApplication.person = server.signUpPerson(p);
        System.out.println("login success");
        password.setText("");

        changeView(event, "main-view.fxml");

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

    private String validateFields() {
        if (!InputValidator.validateUserName(userName.getText()))
            return "user name";
        if (!InputValidator.validatePassword(password.getText()))
            return "passWord";
        if (!InputValidator.validateEmail(email.getText()))
            return "email";
        return "";
    }

    @FXML
    void initialize() {
        assert emailOrUsername != null : "fx:id=\"emailOrUsername\" was not injected: check your FXML file 'login-view.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'login-view.fxml'.";
    }

    public void changePass(KeyEvent event) {
        if (!InputValidator.validatePassword(password.getText())) {
            signUpResult.setText("invalid passWord");
        } else {
            signUpResult.setText("");
        }
    }

    public void changeEmail(KeyEvent event) {
        if (!InputValidator.validateEmail(email.getText())) {
            signUpResult.setText("invalid email");
        } else {
            signUpResult.setText("");
        }
    }

    public void changeUName(KeyEvent event) {
        if (!server.checkUserNameAvailability(userName.getText())) {
            signUpResult.setText("Username already exist");
        } else if (!InputValidator.validateUserName(userName.getText())) {
            signUpResult.setText("invalid username");
        } else {
            signUpResult.setText("");
        }
    }
}