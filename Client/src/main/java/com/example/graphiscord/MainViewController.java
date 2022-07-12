package com.example.graphiscord;

import ClientController.Server;
import code.PrivateChat;
import code.SC.ChatFile;
import code.TextChannelMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class MainViewController {
    public ComboBox friedComboBox;
    @FXML
    private TextArea friendTextArea = new TextArea();
    @FXML
    private final ListView serverMessagesListView = new ListView();
    @FXML
    private TextArea serverTextArea = new TextArea();
    @FXML
    private final ListView friendMessageListView=new ListView();
    private ListView<HBox> currentListView=new ListView<>();
    Server server = Server.getServer();
    @FXML
    private final ListView<HBox> chats = new ListView<>();
    @FXML
    private ListView<HBox> serversListView = new ListView<>();
    @FXML
    private ListView<HBox> friendsListView = new ListView<>();
    @FXML
    private ListView<HBox> membersListView = new ListView<>();
    @FXML
    private ListView<HBox> serverTextChannelsListView = new ListView<>();
    @FXML
    private final ListView<?> friendsList = new ListView<>();
    @FXML
    private AnchorPane membersPane = new AnchorPane();

    ArrayList<String> friends;
    @FXML
    private AnchorPane messagesPane = new AnchorPane();
    private HashMap<Integer, String> serversList;
    private HashSet<String> currentServersTextChannels=new HashSet<>();
    private Integer currentServer;
    private String currentServerTextChannel;

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
        currentServersTextChannels.clear();
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
        currentListView= serverTextChannelsListView;
        int index = serverTextChannelsListView.getSelectionModel().getSelectedIndex();
        for ( TextChannelMessage item : server.getChannelMessages((String) currentServersTextChannels.toArray()[index],currentServer,HelloApplication.person.getUserName())) {
            //todo
            //todo
            //todo
            //todo
            serverMessagesListView.getItems().add(getMessageHBox(null,null,0,0,0));
            //todo
            //todo
            //todo
            //todo

        }
    }

    public void sendMessageButton(ActionEvent actionEvent) {
        //String message = sendingMessageText.getText();
        //sendingMessageText.setText("");
        //currentServersTextChannels.sendMessage(message);
    }

    @FXML
    public void initialize() {
        refresh();
        friedComboBox.getItems().add("All Friends");
        friedComboBox.getItems().add("Online");
        friedComboBox.getItems().add("Pending");
        friedComboBox.getItems().add("Requests");
        friedComboBox.getSelectionModel().select(0);
    }

    @FXML
    public void newFriendButton(ActionEvent event) {
    }


    public HBox getMessageHBox(Image image,String message,int likes,int dislikes,int laugh_) {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(5));
        // circle
        Circle circle = new Circle(25, 25, 25);
        circle.setFill(Color.YELLOW);
        hBox.getChildren().add(circle);
        // icon vbox
        VBox vBoxIcon = new VBox();
        ImageView like = new ImageView();
        like.setFitHeight(25);
        like.setFitHeight(25);
        ImageView disLike = new ImageView();
        disLike.setFitHeight(25);
        disLike.setFitHeight(25);
        ImageView laugh = new ImageView();
        laugh.setFitHeight(25);
        laugh.setFitHeight(25);
        vBoxIcon.getChildren().add(like);
        vBoxIcon.getChildren().add(disLike);
        vBoxIcon.getChildren().add(laugh);
        hBox.getChildren().add(vBoxIcon);
        like.setImage(new Image("@like.png"));
        // result vbox
        VBox vBoxResult = new VBox();
        Label labelLike = new Label();
        labelLike.setAlignment(Pos.CENTER);
        labelLike.prefWidth(33);
        labelLike.prefWidth(33);
        labelLike.setText(String.valueOf(likes));
        labelLike.setTextAlignment(TextAlignment.CENTER);
        labelLike.setPadding(new Insets(2));
        Label labelDisLike = new Label();
        labelDisLike.setAlignment(Pos.CENTER);
        labelDisLike.prefWidth(33);
        labelDisLike.prefWidth(33);
        labelDisLike.setText(String.valueOf(dislikes));
        labelDisLike.setTextAlignment(TextAlignment.CENTER);
        labelDisLike.setPadding(new Insets(2));
        Label labelLaugh = new Label();
        labelLaugh.setAlignment(Pos.CENTER);
        labelLaugh.prefWidth(33);
        labelLaugh.prefWidth(33);
        labelLaugh.setText(String.valueOf(laugh_));
        labelLaugh.setTextAlignment(TextAlignment.CENTER);
        labelLaugh.setPadding(new Insets(2));
        vBoxResult.getChildren().add(labelLike);
        vBoxResult.getChildren().add(labelDisLike);
        vBoxResult.getChildren().add(laugh);
        hBox.getChildren().add(vBoxResult);
        // label
        Label labelMessage = new Label();
        labelMessage.setAlignment(Pos.CENTER);
        labelMessage.prefWidth(33);
        labelMessage.prefWidth(33);
        labelMessage.setText(message);
        labelMessage.setTextAlignment(TextAlignment.CENTER);
        labelMessage.setPadding(new Insets(2));
        hBox.getChildren().add(labelMessage);
        return hBox;
    }
    @FXML
    private void sendVoiceButton(ActionEvent actionEvent) {
    }
    @FXML
    public void sendFileButton(ActionEvent actionEvent) throws IOException {
        //open a file chooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        Integer fileMessage=server.uploadFile(Files.readAllBytes(selectedFile.getAbsoluteFile().toPath()),selectedFile.getAbsolutePath().substring(selectedFile.getAbsolutePath().lastIndexOf('.') + 1));
        var file = server.downloadFile(HelloApplication.person.getImageID());
        Button button = new Button();
        button.setText("Download");
        button.setOnAction(event -> {
            ChatFile file1 = server.downloadFile(fileMessage);
            FileChooser fileChooser1 = new FileChooser();
            fileChooser1.setTitle("Save File");
            fileChooser1.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All Files", "*.*"),
                    new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                    new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
                    new FileChooser.ExtensionFilter("All Files", "*.*")
            );
            File selectedFile1 = fileChooser1.showSaveDialog(null);
            try {
                Files.write(Path.of(selectedFile1.getAbsolutePath()),file1.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());
        Image image = new Image(stream);
        HBox messageHBox = getMessageHBox(image,selectedFile.getAbsolutePath(),0,0,0);
        messageHBox.getChildren().add(button);
        currentListView.getItems().add(messageHBox);
    }

    public void pinnedMessageButton(ActionEvent actionEvent) {
        currentListView= serverTextChannelsListView;
        int index = serverTextChannelsListView.getSelectionModel().getSelectedIndex();
        for ( TextChannelMessage item : server.getPinMessages((String) currentServersTextChannels.toArray()[index],currentServer)) {
            //todo
            //todo
            //todo
            //todo
            serverMessagesListView.getItems().add(getMessageHBox(null,null,0,0,0));
            //todo
            //todo
            //todo
            //todo

        }
    }

}
