package com.example.graphiscord;

import ClientController.Server;
import code.Friend.AddFriendRequest;
import code.Person;
import code.PrivateChat;
import code.Status;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

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

    ArrayList<String> friends;
    @FXML
    private AnchorPane messagesPane = new AnchorPane();
    private HashMap<Integer, String> serversList;
    private HashSet<String> currentServersTextChannels;
    private Integer currentServer;

    public void refresh() {
        // add servers to list
        serversList = server.getPersonServerChats(HelloApplication.person.getUserName());
        serversListView.getItems().clear();
        serversListView.getItems().add(serverViewChats());
        for (var item : serversList.keySet()) {
            serversListView.getItems().add(serverTitle(item, serversList.get(item)));
        }
        serversListView.refresh();
    }

    public HBox serverViewChats() {
        FileInputStream stream = null;
        try {
            stream = new FileInputStream("Client/src/main/resources/com/example/graphiscord/chat-2389223_640.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image(stream);
        Circle pic = new Circle(25, 25, 25);
        pic.setFill(new ImagePattern(image));
        HBox HServer = new HBox();
        HServer.getChildren().add(pic);
        javafx.scene.control.Label label = new javafx.scene.control.Label("Chats");
        HServer.getChildren().add(label);
        return HServer;
    }

    public HBox serverTitle(Integer serverID, String name) {
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

    @FXML
    void settingsButton(ActionEvent event) {
        changeView(event, "setting-view.fxml");
    }

    HBox getFriendHBox(String name, String status ,String lastMessage,Image picture) {
        HBox hBox = new HBox();
        VBox vBox = new VBox();
        Circle circle = new Circle(20,20,20);
        Label label = new Label();
        HBox innerHBox = new HBox();
        Label innerLabel = new Label();
        Circle innerCircle = new Circle(5,5,5);

        hBox.getChildren().add(circle);
        hBox.getChildren().add(vBox);
        vBox.getChildren().add(label);
        vBox.getChildren().add(innerHBox);
        innerHBox.getChildren().add(innerCircle);
        innerHBox.getChildren().add(innerLabel);

        label.setText(name);
        label.setAlignment(Pos.CENTER);
        label.setContentDisplay(ContentDisplay.CENTER);
        hBox.prefWidthProperty().bind(friendsListView.widthProperty());
        vBox.prefWidthProperty().bind(hBox.widthProperty().subtract(5));
        label.prefWidthProperty().bind(vBox.widthProperty());
        innerLabel.prefWidthProperty().bind(vBox.widthProperty());
        circle.setFill(Color.RED);
        innerCircle.setFill(Color.GREEN);
        innerLabel.setText(lastMessage);
        innerHBox.setPadding(new Insets(2));
        hBox.setPadding(new Insets(2));
        vBox.setPadding(new Insets(2));
        return hBox;
    }

    @FXML
    void allFriends(ActionEvent event) {
        friendsList.getItems().clear();
        friends = server.getPersonFriends(HelloApplication.person.getUserName());
         for (var item : server.getPersonFriends(HelloApplication.person.getUserName())
        ) {
        friendsListView.getItems().add(getFriendHBox(item,server.getStatus(item),"none",null));
        }
    }

    @FXML
    void onlineFriends(ActionEvent event) {

    }

    @FXML
    void pendingFriends(ActionEvent event) {
    }

    String chatContact(PrivateChat privateChat) {
        return privateChat.getP1().trim().equals(HelloApplication.person.getUserName()) ? privateChat.getP2() : privateChat.getP1();
    }

    @FXML
    void aServerSelected() {
        int index = serversListView.getSelectionModel().getSelectedIndex();
        if (index == 0) {
            serverTextChannelsListView.getItems().clear();
            for (var item : server.getPersonPrivateChats(HelloApplication.person.getUserName())) {
                serverTextChannelsListView.getItems().add(new HBox(new Label(chatContact(item))));
            }
            return;
        }
        index--;
        currentServer = (Integer) serversList.keySet().toArray()[index];
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

    public void newFriendButton(ActionEvent event) {
    }
}
