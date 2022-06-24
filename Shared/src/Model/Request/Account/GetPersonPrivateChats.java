package Model.Request.Account;

import Model.Person;
import Model.PrivateChat;
import Model.Request.IRequest;

import java.io.Serializable;
import java.util.ArrayList;

public class GetPersonPrivateChats implements IRequest, Serializable {
    String userName;
    ArrayList<PrivateChat> privateChats;

    public GetPersonPrivateChats(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ArrayList<PrivateChat> getPrivateChats() {
        return privateChats;
    }

    public void setPrivateChats(ArrayList<PrivateChat> privateChats) {
        this.privateChats = privateChats;
    }
}
