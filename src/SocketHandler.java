import Model.Request.Request;
import Model.Response.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class SocketHandler extends Thread{
    Socket socket;
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
            Request response = (Request) inputStream.readObject();
            socket.close();
        }catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
            //throw new RuntimeException(e);
        }
    }
}
