package code.Chats;

import code.IRequest;
import code.PrivateChat;

import java.io.Serializable;
import java.util.ArrayList;

public class GetPersonPrivateChatsRequest implements IRequest, Serializable {
    String userName;
    ArrayList<PrivateChat> privateChats;

    public GetPersonPrivateChatsRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<PrivateChat> getPrivateChats() {
        return privateChats;
    }
    public void setPrivateChats(ArrayList<PrivateChat> privateChats) {
        this.privateChats = privateChats;
    }
}
