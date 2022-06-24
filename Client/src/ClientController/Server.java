package ClientController;

import Model.Request.IRequest;
import Model.Requestable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Server {
    public static IRequest sendRequest(IRequest request) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 1567);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(request);
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            return (IRequest) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
