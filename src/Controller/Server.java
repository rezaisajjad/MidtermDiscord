package Controller;

import Model.Request.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Server implements IServer {
    @Override
    public Request sendRequest(Request request) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 80);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(request);
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Request response = (Request) inputStream.readObject();
            socket.close();
            return response;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
