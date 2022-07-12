package com.example.graphiscord;

import ClientController.Server;
import code.ChannelType;
import code.Person;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class HelloApplication extends Application {
    public static Person person;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("setting-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        System.err.println(scene);
        stage.setScene(scene);
        stage.show();
    }
    public static void changeScene(ActionEvent event, String name) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(name));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) throws IOException {
//        String uName= "sajjadmahdi";
//        var person = new Person();
//        person.setEmail("a@b.com");
//        person.setUserName(uName);
//        person.setPassWord("Sajjadmahdi1");
//        Server.getServer().signUpPerson(person);
//        Server.getServer().createServer(uName,"Test1");
//        Server.getServer().createServer(uName,"Test2");
//        Server.getServer().createServer(uName,"Test3");
//        for (var item:Server.getServer().getPersonServerChats(uName).keySet()) {
//            Server.getServer().setServerImage(item,Server.getServer().uploadFile(
//                    Files.readAllBytes(Path.of("C:\\Users\\Sajjad\\IdeaProjects\\MidtermDiscordssssssssssssssssssssss\\Client\\src\\main\\resources\\com\\example\\graphiscord\\itl.cat_macbook-pro-wallpaper_51012.png")),"png"
//            ));
//        }
        launch();
    }
}
