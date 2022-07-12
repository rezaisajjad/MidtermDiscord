package com.example.graphiscord;

import ClientController.InputValidator;
import ClientController.Server;
import code.Status;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class SettingViewController {
    public ComboBox statusComboBox;
    public Circle profilePic1;
    Server server = Server.getServer();

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
    private Label userName;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Label usernameValidity;
    // fields end here

    @FXML
    void changePicButton(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File" );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        var path = fileChooser.showOpenDialog(null).getAbsolutePath();
        profilePic.setFill(new ImagePattern(new Image(path)));
        try {
            var fileID = server.uploadFile(Files.readAllBytes(Path.of(path)),
                    path.substring(path.lastIndexOf('.') + 1));
            server.setPersonProfilePicture(HelloApplication.person.getUserName(), fileID);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HelloApplication.reFreshPerson();
    }

    @FXML
    void emailEditButton(ActionEvent event) {
        //check email validity and if it's not already in use then change it
        String email = emailTextField.getText();
        if (InputValidator.validateEmail(email)) {
            if (server.checkEmailAvailability(email)) {
                server.changePersonEmail(HelloApplication.person.getUserName(), email);
                emailValidity.setVisible(true);
                emailValidity.setText("Changed");
                emailValidity.setStyle("-fx-text-fill: green;");
                HelloApplication.reFreshPerson();
            }else
            {
                emailValidity.setVisible(true);
                emailValidity.setText("Already exist");
                emailValidity.setStyle("-fx-text-fill: red;");
            }
        } else {
            emailValidity.setVisible(true);
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
        String pass = passwordTextField.getText();
        if (InputValidator.validatePassword(pass)) {
            server.changePersonPassword(
                    HelloApplication.person.getUserName(),
                    HelloApplication.person.getPassWord(),
                    pass);
            passwordValidity.setVisible(true);
            passwordValidity.setText("Changed");
            passwordValidity.setStyle("-fx-text-fill: green;");
            HelloApplication.reFreshPerson();
        } else {
            passwordValidity.setVisible(true);
            passwordValidity.setText("Invalid");
            passwordValidity.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    void usernameEditButton(ActionEvent event) {
        if (server.checkUserNameAvailability(usernameTextField.getText())) {
            usernameValidity.setVisible(true);
            usernameValidity.setText("Changed");
            usernameValidity.setStyle("-fx-text-fill: green;");
            userName.setText(usernameTextField.getText());
        } else {
            usernameValidity.setVisible(true);
            usernameValidity.setText("Already exist");
            usernameValidity.setStyle("-fx-text-fill: red;");
        }

    }

    @FXML
    void initialize() {
        initializeComboBix();
        reFreshStatus();
        reFreshPic();
        reFreshInputs();

        assert emailTextField != null : "fx:id=\"emailTextField\" was not injected: check your FXML file 'setting-view.fxml'.";
        assert emailValidity != null : "fx:id=\"emailValidity\" was not injected: check your FXML file 'setting-view.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'setting-view.fxml'.";
        assert passwordValidity != null : "fx:id=\"passwordValidity\" was not injected: check your FXML file 'setting-view.fxml'.";
        assert profilePic != null : "fx:id=\"profilePic\" was not injected: check your FXML file 'setting-view.fxml'.";
        assert userName != null : "fx:id=\"userName\" was not injected: check your FXML file 'setting-view.fxml'.";
        assert usernameTextField != null : "fx:id=\"usernameTextField\" was not injected: check your FXML file 'setting-view.fxml'.";
        assert usernameValidity != null : "fx:id=\"usernameValidity\" was not injected: check your FXML file 'setting-view.fxml'.";
    }

    private void initializeComboBix() {
        statusComboBox.getItems().add("Online");
        statusComboBox.getItems().add("Idle");
        statusComboBox.getItems().add("Do Not Disturb");
        statusComboBox.getItems().add("Invisible");
    }

    private void reFreshInputs() {
        usernameTextField.setText(HelloApplication.person.getUserName());
        emailTextField.setText(HelloApplication.person.getEmail());
        phoneTextField.setText(HelloApplication.person.getPhoneNumber());
        userName.setText(HelloApplication.person.getUserName());
        passwordTextField.setText("*******");
    }

    private void reFreshPic() {
        if (HelloApplication.person.getImageID() == null || HelloApplication.person.getImageID() == 0)
            return;
        var file = this.server.downloadFile(HelloApplication.person.getImageID());
        ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());
        Image image = new Image(stream);
        profilePic.setFill(new ImagePattern(image));
    }

    private void reFreshStatus() {
        var status = server.getStatus(HelloApplication.person.getUserName());
        int i = 0;
        if (status.equals(Status.Idle.toString())) {
            profilePic1.setFill(Color.BLUE);
            i = 1;
        } else if (status.equals(Status.DoNotDisturb.toString())) {
            profilePic1.setFill(Color.RED);
            i = 2;
        } else if (status.equals(Status.Invisible.toString())) {
            profilePic1.setFill(Color.GRAY);
            i = 3;
        } else if (status.equals(Status.Online.toString())) {
            profilePic1.setFill(Color.GREEN);
        }
        statusComboBox.getSelectionModel().select(i);
    }


    public void phoneEditButton(ActionEvent actionEvent) {
        String phone = phoneTextField.getText();
        if (InputValidator.validatePhoneNumber(phone)) {
            server.changePhoneNumber(
                    HelloApplication.person.getUserName(),
                    phone);
            phoneValidity.setVisible(true);
            phoneValidity.setText("Changed");
            phoneValidity.setStyle("-fx-text-fill: green;");
            HelloApplication.reFreshPerson();
        } else {
            phoneValidity.setVisible(true);
            phoneValidity.setText("Invalid");
            phoneValidity.setStyle("-fx-text-fill: red;");
        }
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

    public void stateOnline(ActionEvent event) {
        server.setStatus(HelloApplication.person.getUserName(), Status.Online);
        reFreshStatus();
    }

    public void stateIdle(ActionEvent event) {
        server.setStatus(HelloApplication.person.getUserName(), Status.Idle);
        reFreshStatus();
    }

    public void stateDND(ActionEvent event) {
        server.setStatus(HelloApplication.person.getUserName(), Status.DoNotDisturb);
        reFreshStatus();
    }

    public void stateIV(ActionEvent event) {
        server.setStatus(HelloApplication.person.getUserName(), Status.Invisible);
        reFreshStatus();
    }

    public void changeStatus(ActionEvent event) {
        if (statusComboBox.getSelectionModel().getSelectedIndex() == 0) {
            stateOnline(event);
        } else if (statusComboBox.getSelectionModel().getSelectedIndex() == 1) {
            stateIdle(event);
        } else if (statusComboBox.getSelectionModel().getSelectedIndex() == 2) {
            stateDND(event);
        } else if (statusComboBox.getSelectionModel().getSelectedIndex() == 3) {
            stateIV(event);
        }

    }
}


