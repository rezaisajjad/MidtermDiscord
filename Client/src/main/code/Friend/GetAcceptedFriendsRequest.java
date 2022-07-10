package code.Friend;


import code.IRequest;

import java.io.Serializable;
import java.util.ArrayList;

public class GetAcceptedFriendsRequest implements Serializable, IRequest {
    String userName;
    ArrayList<String> friends;

    public GetAcceptedFriendsRequest(String userName) {
        this.userName = userName;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    public String getUserName() {
        return userName;
    }
}
