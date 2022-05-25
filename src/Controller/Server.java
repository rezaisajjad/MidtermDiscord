package Controller;

import Model.Request.Request;
import Model.Response.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Server implements IServer {
    @Override
    public Response sendRequest(Request request) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 80);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(request);
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Response response = (Response) inputStream.readObject();
            socket.close();
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
