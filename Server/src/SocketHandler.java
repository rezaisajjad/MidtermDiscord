import Model.Request.Account.CheckUserNameAvailabilityRequest;
import Model.Request.Chats.GetPersonPrivateChatsRequest;
import Model.Request.Account.LoginRequest;
import Model.Request.Account.SignUpRequest;
import Model.Request.Chats.SendMessagePrivateChatRequest;
import Model.Request.Friend.*;
import Model.Request.IRequest;
import Repository.PeopleRepository;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketHandler extends Thread {
    public static PeopleRepository people = new PeopleRepository();

    private Socket socket;

    private SocketHandler(Socket socket) {
        this.socket = socket;
        start();
        System.out.println("Connected: \n\t\t" + socket.toString());
    }

    public static SocketHandler New(Socket socket) {
        return new SocketHandler(socket);
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Object request = (Object) inputStream.readObject();
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(processRequest(request));
            socket.close();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private IRequest processRequest(Object request) {
        //region Account
        if (request instanceof CheckUserNameAvailabilityRequest res) {
            res.setAvailability(people.isUserNameAvailable(res.getUserName()));
            return res;
        } else if (request instanceof LoginRequest res) {
            res.setP(people.loginPerson(res.getUserName(), res.getPassWord()).cloneWithoutList());
            return res;
        } else if (request instanceof SignUpRequest res) {
            if (!people.addPerson(res.getP())) {
                res.setP(null);
            }
            if (res.getP() != null)
                res.setP(res.getP().cloneWithoutList());
            return res;
        }
        //endregion
        //region Chats
        else if (request instanceof GetPersonPrivateChatsRequest res) {
            res.setPrivateChats(people.getPersonPrivateChats(res.getUserName()));
            return res;
        } else if (request instanceof SendMessagePrivateChatRequest res) {
            people.sendPrivateChatMessage(res.getPrivateChat(), res.getMessage());
            return res;
        }
        //endregion
        //region Friends
        else if (request instanceof RemoveFriendRequest res) {
            people.removeFriend(res.getSenderUserName(), res.getReceiverUserName());
            return res;
        } else if (request instanceof GetPersonRequestsRequest res) {
            res.setRequests(people.getPersonFriendRequests(res.getUserName()));
            return res;
        } else if (request instanceof AddFriendRequest res) {
            people.addFriend(res.getSenderUserName(), res.getReceiverUserName());
            return res;
        } else if (request instanceof AcceptFriendRequest res) {
            people.acceptFriendRequest(res.getFriendRequest().getReceiverUserName(), res.getFriendRequest().getSenderUserName());
            return res;
        } else if (request instanceof GetAcceptedFriendsRequest res) {
            res.setFriends(people.getPersonFriends(res.getUserName()));
            return res;
        } else if (request instanceof GetBlockListRequest res) {
            res.setPersons(people.getPersonBlockedList(res.getUserName()));
            return res;
        } else if (request instanceof BlockPersonRequest res) {
            people.blockAPerson(res.getBlocked(),res.getBlocked());
            return res;
        } else if (request instanceof UnBlockPersonRequest res) {
            people.unBlockAPerson(res.getBlocked(),res.getBlocked());
            return res;
        } else {
            return null;
        }
        //endregion
    }
}
