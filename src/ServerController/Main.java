package ServerController;

import Model.*;

import java.io.*;
import java.net.*;
import java.util.*;

public class Main {
    public static ArrayList<Requestable> requestables;
    public static HashMap<String,Person> persons;
    public static Person findPerson(String userName) {
        return persons.get(userName);
    }
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(80);
            while (true) {
                SocketHandler.New(server.accept());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}