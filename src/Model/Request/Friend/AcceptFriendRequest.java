package Model.Request.Friend;

import Model.Person;
import Model.Request.*;

import java.io.*;
import java.util.*;

public class AcceptFriendRequest extends Request implements Serializable {
    private final FriendRequest friendRequest;

    public AcceptFriendRequest(FriendRequest friendRequest) {
        this.friendRequest = friendRequest;
    }
    public FriendRequest getFriendRequest() {
        return friendRequest;
    }

    @Override
    public boolean serverAct(HashMap<String,Person> persons) {
            persons.get(friendRequest.getSender()).addFriend(friendRequest.getReceiver());
            persons.get(friendRequest.getReceiver()).addFriend(friendRequest.getReceiver());
            return true;
    }
    @Override
    public HashSet<String> selfAct(HashMap<String,Person> persons) {
        return null;
    }
}
