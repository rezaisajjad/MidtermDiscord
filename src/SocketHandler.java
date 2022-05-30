import Model.Request.FriendRequest;
import Model.Message;
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
            Request response = (Request) inputStream.readObject();
//            if (response.getRequestType() == RequestType.SendMessage) {
//                Message message=(Message) response.getObject();
//            }else if (response.getRequestType() == RequestType.AcceptFriendRequest) {
//                FriendRequest fRequest=(FriendRequest) response.getObject();
//            }else if (response.getRequestType() == RequestType.BlockPerson) {
//                 BlockRequest bRequest=(BlockRequest) response.getObject();
//            }else if (response.getRequestType() == RequestType.SendFriendRequest) {
//                 FriendRequest fRequest=(FriendRequest) response.getObject();
//            }else if (response.getRequestType() == RequestType.ReceiveNotifications) {
//                 fRequest=(FriendRequest) response.getObject();
//            }else if (response.getRequestType() == RequestType.SignUp) {
//                 =(FriendRequest) response.getObject();
//            }
            response.contactsAct();
            response.serverAct();
            socket.close();
        }catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
            //throw new RuntimeException(e);
        }
    }
}
