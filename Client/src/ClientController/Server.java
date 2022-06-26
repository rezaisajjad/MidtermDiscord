package ClientController;

import Model.Person;
import Model.Request.Friend.*;
import Model.Request.PrivateChat;
import Model.Request.PrivateChatMessage;
import Model.Request.Account.CheckUserNameAvailabilityRequest;
import Model.Request.Chats.GetPersonPrivateChatsRequest;
import Model.Request.Account.LoginRequest;
import Model.Request.Account.SignUpRequest;
import Model.Request.Chats.SendMessagePrivateChatRequest;
import Model.Request.IRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private static Server server = new Server();

    private Server() {
    }

    public static Server getServer() {
        return server;
    }

    private IRequest sendRequest(IRequest request) {
        Socket socket = null;
        try {
            socket = new Socket("localhost", 12344);
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

    /**
     * sign up a new account in server
     *
     * @param p Person
     * @return signed up person
     */
    public Person signUpPerson(Person p) {
        SignUpRequest request = new SignUpRequest(p);
        return ((SignUpRequest) sendRequest(request)).getP();
    }

    /**
     * login an account
     *
     * @param userName UserName
     * @param passWord PassWord
     * @return logged in person
     */
    public Person loginPerson(String userName, String passWord) {
        LoginRequest request = new LoginRequest(userName, passWord);
        return ((LoginRequest) sendRequest(request)).getP();
    }

    /**
     * check if a username is available
     *
     * @param userName username
     * @return true:available false:unavailable
     */
    public boolean CheckUserNameAvailability(String userName) {
        CheckUserNameAvailabilityRequest request = new CheckUserNameAvailabilityRequest(userName);
        return ((CheckUserNameAvailabilityRequest) sendRequest(request)).isAvailable();
    }

    /**
     * return private chats of a person
     *
     * @param userName userName
     * @return list of chats
     */
    public ArrayList<PrivateChat> getPersonPrivateChats(String userName) {
        GetPersonPrivateChatsRequest request = new GetPersonPrivateChatsRequest(userName);
        return ((GetPersonPrivateChatsRequest) sendRequest(request)).getPrivateChats();
    }

    /**
     * send a message to private chat
     *
     * @param pc  private chat
     * @param pcm message
     */
    public void sendPrivateChatMessage(PrivateChat pc, PrivateChatMessage pcm) {
        SendMessagePrivateChatRequest request = new SendMessagePrivateChatRequest(pc, pcm);
        sendRequest(request);
    }

    /**
     * the friends of a person
     *
     * @param userName Username
     * @return friendList
     */
    public ArrayList<String> getPersonFriends(String userName) {
        GetAcceptedFriendsRequest request = new GetAcceptedFriendsRequest(userName);
        return ((GetAcceptedFriendsRequest) sendRequest(request)).getFriends();
    }

    /**
     * sends a friend request
     *
     * @param sender   friend request sender
     * @param receiver friend request receiver
     */
    public void sendFriendRequest(String sender, String receiver) {
        AddFriendRequest request = new AddFriendRequest(sender, receiver);
        sendRequest(request);
    }

    /**
     * removes a friend
     *
     * @param remover  friend remover
     * @param receiver removed friend
     */
    public void removePersonFriend(String remover, String receiver) {
        RemoveFriendRequest request = new RemoveFriendRequest(remover, receiver);
        sendRequest(request);
    }

    /**
     * returns list of requests
     *
     * @param userName who you want to get requests
     * @return list of request
     */
    public ArrayList<AddFriendRequest> getPersonFriendRequests(String userName) {
        GetPersonRequestsRequest request = new GetPersonRequestsRequest(userName);
        return ((GetPersonRequestsRequest) sendRequest(request)).getRequests();
    }

    /**
     * accept a friend request
     *
     * @param friendRequest request
     */
    public void acceptFriendRequest(AddFriendRequest friendRequest) {
        AcceptFriendRequest acceptFriendRequest = new AcceptFriendRequest(friendRequest);
        sendRequest(acceptFriendRequest);
    }

    /**
     * blocks a person
     *
     * @param blocker person who block
     * @param blocked blocked person
     */
    public void blockPerson(String blocker, String blocked) {
        BlockPersonRequest request = new BlockPersonRequest(blocker, blocked);
        sendRequest(request);
    }
    /**
     * unblocks a person
     *
     * @param blocker person who block
     * @param blocked blocked person
     */
    public void unBlockPerson(String blocker, String blocked) {
        UnBlockPersonRequest request = new UnBlockPersonRequest(blocker, blocked);
        sendRequest(request);
    }
    /**
     * returns blocked persons
     * @param userName person who block
     * @return block list
     */
    public ArrayList<String> getPersonBlockList(String userName) {
        GetBlockListRequest request = new GetBlockListRequest(userName);
        return ((GetBlockListRequest) sendRequest(request)).getPersons();
    }
}
