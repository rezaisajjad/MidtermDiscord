package com.example.graphiscord;

import code.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import java.io.File;
import java.util.ArrayList;

public class MainViewController {

    @FXML
    private ListView<HBox> chats=new ListView<>();
    @FXML
    private ListView<HBox> serversListView =new ListView<>();
    @FXML
    private ListView<HBox> friendsListView =new ListView<>();

    ArrayList<ServerChat> serversList=new ArrayList<>();
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
        //TODO: open settings window

    }
    @FXML
    private ListView<?> friendsList;


    @FXML
    void allFriends(ActionEvent event) {

    }

    @FXML
    void onlineFriends(ActionEvent event) {

    }

    @FXML
    void pendingFriends(ActionEvent event) {


    }




    public void changeCurrentServer(ServerChat server) {
        currentServer=server;
    }
    public void changeCurrentServer(String serverName) {
        for (ServerChat server:serversList) {
            if (server.getName().equals(serverName)) {
                currentServer=server;
            }
        }
    }
    public void  serverChannelsTitleList (ServerChat server) {
        for (ServerTextChannel chat: currentServer.getChannels().values()) {
            File file = new File("src/main/resources/images/"+server.getName()+"_"+chat.getName()+".png");
            Image image = new Image(file.getAbsolutePath());
            Circle pic = new Circle(25, 25, 25);
            pic.setFill(new ImagePattern(image));
            HBox HChat=new HBox();
            HChat.getChildren().add(pic);
            javafx.scene.control.Label label = new javafx.scene.control.Label(Color.rgb(10,50,50)+chat.getName());
            HChat.getChildren().add(label);
//            a new button adding to aChat using image source
            chats.getItems().add(HChat);
        }
    }
    public HBox serverTitle(ServerChat server){
        File file = new File("src/main/resources/images/"+server.getName()+".png");
        Image image = new Image(file.getAbsolutePath());
        Circle pic = new Circle(25, 25, 25);
        pic.setFill(new ImagePattern(image));
        HBox HServer=new HBox();
        HServer.getChildren().add(pic);
        javafx.scene.control.Label label = new javafx.scene.control.Label(Color.rgb(10,50,50)+server.getName());
        HServer.getChildren().add(label);
        return HServer;
    }



    public void initialize() {
        chats=new ListView<>();
        serversListView =new ListView<>();
        //add servers title to serversListView
        for (ServerChat server:serversList) {
            serversListView.getItems().add(serverTitle(server));
        }

    }

}
