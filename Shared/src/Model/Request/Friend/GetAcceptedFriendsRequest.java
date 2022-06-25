package Model.Request.Friend;

import Model.Person;
import Model.Request.IRequest;

import java.io.Serializable;
import java.util.ArrayList;

public class GetAcceptedFriendsRequest implements Serializable, IRequest {
    String userName;
    ArrayList<Person> friends;

    public GetAcceptedFriendsRequest(String userName) {
        this.userName = userName;
    }

    public ArrayList<Person> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Person> friends) {
        this.friends = friends;
    }

    public String getUserName() {
        return userName;
    }
}
