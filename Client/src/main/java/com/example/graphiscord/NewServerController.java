package com.example.graphiscord;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

import ClientController.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;

public class NewServerController {


    @FXML // fx:id="nameTextField"
    private TextField nameTextField; // Value injected by FXMLLoader

    @FXML // fx:id="serverPic"
    private Circle serverPic; // Value injected by FXMLLoader


    private File serverImage;


    @FXML
    void backButton(ActionEvent event) throws IOException {
        HelloApplication.changeScene(event, "main-view.fxml");
    }

    @FXML
    void serverPicButton(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Server Picture");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        serverImage =fileChooser.showOpenDialog(null);
        Image image =new Image(serverImage.toURI().toString());
        serverPic.setFill(new ImagePattern(image));

    }

    @FXML
    void createButton(ActionEvent event) throws IOException {
        //creat File address of serverImage
        Server.getServer().createServer(HelloApplication.person.getUserName(),nameTextField.getText());
        try {
            HelloApplication.changeScene(event, "main-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (serverImage == null) {
            return;
        }else {
            Integer id=Server.getServer().uploadFile(Files.readAllBytes(serverImage.getParentFile().toPath()),"png");
            Server.getServer().setServerImage(id,id);
        }
    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert nameTextField != null : "fx:id=\"nameTextField\" was not injected: check your FXML file 'newServer-view.fxml'.";
        assert serverPic != null : "fx:id=\"serverPic\" was not injected: check your FXML file 'newServer-view.fxml'.";

    }

}

