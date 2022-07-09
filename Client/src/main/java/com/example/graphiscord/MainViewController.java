package com.example.graphiscord;

import javafx.fxml.FXML;
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
    private ListView<HBox> servers=new ListView<>();
//    @FXML
//    private final HBox aChat =new HBox();
    ArrayList<Chat> contacts=new ArrayList<>();
    ArrayList<Server> serversList=new ArrayList<>();
    private Server currentServer=new Server();


    public void changeCurrentServer(Server server) {
        currentServer=server;
    }
    public void changeCurrentServer(String serverName) {
        for (Server server:serversList) {
            if (server.getName().equals(serverName)) {
                currentServer=server;
            }
        }
    }
    public void  showableServer(Server server) {
        for (Chat chat: currentServer.getChats()) {
            File file = new File("src/main/resources/images/user.png");
            Image image = new Image(file.getAbsolutePath());
            Circle pic = new Circle(25, 25, 25);
            pic.setFill(new ImagePattern(image));
            HBox HChat=new HBox();
            HChat.getChildren().add(pic);
            javafx.scene.control.Label label = new javafx.scene.control.Label(Color.rgb(10,50,50)+chat.getName());
            HChat.getChildren().add(label);
//            a new button adding to aChat using image source
            chats.getItems().add(HChat);
        };
    }


    public void initialize() {
        chats=new ListView<>();
        servers=new ListView<>();
        servers.getItems().add()
    }

}
