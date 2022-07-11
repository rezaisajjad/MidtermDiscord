package com.example.graphiscord;

import code.ServerChat;
import code.ServerTextChannel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.File;
import java.util.ArrayList;

public class MainViewController {

    @FXML
    private ListView<HBox> chats = new ListView<>();
    @FXML
    private TextArea sendingMessageText;
    @FXML
    private ListView<HBox> serversListView = new ListView<>();
    @FXML
    private ListView<HBox> friendsListView = new ListView<>();
    @FXML
    private ListView<HBox> membersListView = new ListView<>();
    @FXML
    private ListView<HBox> serverTextChannelsListView = new ListView<>();
    @FXML
    private ListView<?> friendsList;
    @FXML
    private AnchorPane membersPane;
    @FXML
    private AnchorPane messagesPane;
    private final ArrayList<ServerChat> serversList = new ArrayList<>();
    private final ArrayList<String> currentServersTextChannels = new ArrayList<>();
    private ServerChat currentServer;

    @FXML
    void invitation(ActionEvent event) {

    }

    @FXML
    void newChannelButton(ActionEvent event) {

    }

    @FXML
    void newServerButton(ActionEvent event) {

    }
    @FXML
    void settingsButton(ActionEvent event) {

    }
    @FXML
    void allFriends(ActionEvent event) {

    }

    @FXML
    void onlineFriends(ActionEvent event) {

    }

    @FXML
    void pendingFriends(ActionEvent event) {
    }

    @FXML
    void aServerSelected() {
        currentServer = serversList.get(serversListView.getSelectionModel().getSelectedIndex());
        membersListView.getItems().clear();
        serverTextChannelsListView.getItems().clear();
        for (ServerTextChannel c : currentServer.getChannels().values()) {
            serverTextChannelsListView.getItems().add(new HBox(new Label(c.getName())));
            currentServersTextChannels.add(c.getName());
        }
        for (String u : currentServer.getMembers()) {
            //TODO: add member profile picture to the new HBox
            membersListView.getItems().add(new HBox(new Label(u)));
        }
        membersPane.setVisible(true);
    }

    @FXML
    void aServerTextChannelSelected() {
        membersListView.getItems().clear();
        for (String u : currentServer.getMembers()) {
            membersListView.getItems().add(new HBox(new Label(u)));
        }
        messagesPane.setVisible(true);
    }

    public HBox serverTitle(ServerChat server) {
        File file = new File("src/main/resources/images/" + server.getName() + ".png");
        return getHBox(server, file);
    }

    private HBox getHBox(ServerChat server, File file) {
        Image image = new Image(file.getAbsolutePath());
        Circle pic = new Circle(25, 25, 25);
        pic.setFill(new ImagePattern(image));
        HBox HServer = new HBox();
        HServer.getChildren().add(pic);
        javafx.scene.control.Label label = new javafx.scene.control.Label(Color.rgb(10, 50, 50) + server.getName());
        HServer.getChildren().add(label);
        return HServer;
    }

    private HBox getHBox(ServerChat server) {
        HBox HServer = new HBox();
        javafx.scene.control.Label label = new javafx.scene.control.Label(Color.rgb(10, 50, 50) + server.getName());
        HServer.getChildren().add(label);
        return HServer;
    }

    public void initialize() {
        chats = new ListView<>();
        serversListView = new ListView<>();
        //add servers title to serversListView
        for (ServerChat server : serversList) {
            serversListView.getItems().add(serverTitle(server));
        }
        messagesPane.setVisible(false);
        membersPane.setVisible(false);
    }
    public void sendMessageButton(ActionEvent actionEvent) {
        //String message = sendingMessageText.getText();
        //sendingMessageText.setText("");
        //currentServersTextChannels.sendMessage(message);
    }
}
