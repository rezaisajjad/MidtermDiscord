package ServerController;

import Model.Request.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class SocketHandler extends Thread{
    private Socket socket;
    private Request respons;
    private SocketHandler(Socket socket) {
        this.socket = socket;
        start();
        System.out.println("Connected: \n\t\t"+socket.toString());
    }
    public static SocketHandler New(Socket socket) {
        return new SocketHandler(socket);
    }
    @Override
    public void run() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Request request = (Request) inputStream.readObject();
            request.serverAct(Main.persons);
            request.selfAct(Main.persons);
            socket.close();
        }catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
            //throw new RuntimeException(e);
        }
    }
}
