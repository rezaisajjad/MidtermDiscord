package com.example.graphiscord;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class ServerSetteingController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Circle channelPic;

    @FXML
    private ListView<?> channelsListView;

    @FXML
    private ListView<?> rolesListView;

    @FXML
    private TextField serverNameTextField;

    @FXML
    private Label srverNameLabel;

    @FXML
    private ListView<?> usersListView;

    @FXML
    void changePicButton(ActionEvent event) {

    }

    @FXML
    void channelsClicked(MouseEvent event) {

    }

    @FXML
    void editServerNameButton(ActionEvent event) {

    }

    @FXML
    void newChannelButton(ActionEvent event) {

    }

    @FXML
    void newRoleButton(ActionEvent event) {

    }

    @FXML
    void rolesClicked(MouseEvent event) {

    }

    @FXML
    void usersClicked(MouseEvent event) {

    }

    @FXML
    void initialize() {
        assert channelPic != null : "fx:id=\"channelPic\" was not injected: check your FXML file 'serverSetteing-view.fxml'.";
        assert channelsListView != null : "fx:id=\"channelsListView\" was not injected: check your FXML file 'serverSetteing-view.fxml'.";
        assert rolesListView != null : "fx:id=\"rolesListView\" was not injected: check your FXML file 'serverSetteing-view.fxml'.";
        assert serverNameTextField != null : "fx:id=\"serverNameTextField\" was not injected: check your FXML file 'serverSetteing-view.fxml'.";
        assert srverNameLabel != null : "fx:id=\"srverNameLabel\" was not injected: check your FXML file 'serverSetteing-view.fxml'.";
        assert usersListView != null : "fx:id=\"usersListView\" was not injected: check your FXML file 'serverSetteing-view.fxml'.";

    }

}
