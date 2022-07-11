package com.example.graphiscord;

import ClientController.Server;
import code.ChannelType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.HashSet;

public class MainViewController {
    Server server = Server.getServer();
    @FXML
    private final ListView<HBox> chats = new ListView<>();
    @FXML
    private TextArea sendingMessageText = new TextArea();
    @FXML
    private ListView<HBox> serversListView = new ListView<>();
    @FXML
    private ListView<HBox> friendsListView = new ListView<>();
    @FXML
    private ListView<HBox> membersListView = new ListView<>();
    @FXML
    private ListView<HBox> serverTextChannelsListView = new ListView<>();
    @FXML
    private ListView<?> friendsList = new ListView<>();
    @FXML
    private AnchorPane membersPane = new AnchorPane();
    @FXML
    private AnchorPane messagesPane = new AnchorPane();
    private HashMap<Integer, String> serversList;
    private HashSet<String> currentServersTextChannels;
    private Integer currentServer;

    public void refresh() {
        // add servers to list
        serversList = server.getPersonServerChats(HelloApplication.person.getUserName());
        serversListView.getItems().clear();
        for (var item : serversList.keySet()) {
            serversListView.getItems().add(serverTitle(item, serversList.get(item)));
        }
        serversListView.refresh();
    }

    public HBox serverTitle(Integer serverID,String name) {
        var file = this.server.downloadFile(server.getServerImageID(serverID));
        ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());
        Image image = new Image(stream);
        Circle pic = new Circle(25, 25, 25);
        pic.setFill(new ImagePattern(image));
        HBox HServer = new HBox();
        HServer.getChildren().add(pic);
        javafx.scene.control.Label label = new javafx.scene.control.Label(name);
        HServer.getChildren().add(label);
        return HServer;
    }

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
        currentServer = (Integer) serversList.keySet().toArray()[serversListView.getSelectionModel().getSelectedIndex()];
        serverTextChannelsListView.getItems().clear();
        currentServersTextChannels = server.getServerChannels(currentServer, HelloApplication.person.getUserName());
        for (var item : currentServersTextChannels) {
            serverTextChannelsListView.getItems().add(new HBox(new Label(item)));
        }
        var members = server.getServerMembers(currentServer);
        membersListView.getItems().clear();
        for (var item : members.keySet()) {
            membersListView.getItems().add(new HBox(new Label(item + "  " + members.get(item))));
        }
        membersPane.setVisible(true);
    }

    @FXML
    void aServerTextChannelSelected() {
//        membersListView.getItems().clear();
//        for (String u : currentServer.getMembers()) {
//            membersListView.getItems().add(new HBox(new Label(u)));
//        }
//        messagesPane.setVisible(true);
    }

    public void sendMessageButton(ActionEvent actionEvent) {
        //String message = sendingMessageText.getText();
        //sendingMessageText.setText("");
        //currentServersTextChannels.sendMessage(message);
    }

    public void initialize() {
        refresh();
    }
}
