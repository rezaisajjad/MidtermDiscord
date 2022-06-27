import Model.Request.Account.*;
import Model.Request.Chats.GetPersonPrivateChatsRequest;
import Model.Request.Chats.SendMessagePrivateChatRequest;
import Model.Request.Friend.*;
import Model.Request.IRequest;
import Model.Request.SC.*;
import Repository.PeopleRepository;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketHandler extends Thread {
    public static PeopleRepository people = PeopleRepository.getInstance();

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
        else if (request instanceof GetStatusRequest res) {
            res.setStatus(people.getStatus(res.getUserName()));
            return res;
        }
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
            people.blockAPerson(res.getBlocker(),res.getBlocked());
            return res;
        } else if (request instanceof UnBlockPersonRequest res) {
            people.unBlockAPerson(res.getBlocker(),res.getBlocked());
            return res;
        }else if (request instanceof ChangeStatusRequest res) {
            people.setStatus(res.getUserName(),res.getStatus());
            return res;
        }else if (request instanceof ChangeProfilePictureRequest res) {
            people.setPersonProfilePicture(res.getUserName(), res.getImage(), res.getSuffix());
            res = new ChangeProfilePictureRequest("", null, "");
            return res;
        } else if (request instanceof CreateServerRequest res) {
            people.createServer(res.getCreatorUserName(), res.getName());
            return res;
        } else if (request instanceof GetPersonServersRequest res) {
            res.setList(people.getPersonServerChats(res.getUserName()));
            return res;
        } else if (request instanceof CreateServerChannelRequest res) {
            people.createServerChannel(res.getName(),res.getType(),res.getServerUniqueID());
            return res;
        } else if (request instanceof CheckChannelNameAvailabilityRequest res) {
            res.setResult(people.checkChannelNameAvailability(res.getServerUniqueID(),res.getChannelName()));
            return res;
        } else if (request instanceof GetServerChannelsRequest res) {
            res.setChannelsName(people.getServerChannels(res.getServerUniqueID()));
            return res;
        } else {
            return null;
        }
        //endregion
    }
}
