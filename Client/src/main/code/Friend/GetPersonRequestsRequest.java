package code.Friend;


import code.IRequest;

import java.io.Serializable;
import java.util.ArrayList;

public class GetPersonRequestsRequest implements Serializable, IRequest {
    String userName;
    ArrayList<code.Friend.AddFriendRequest> requests;

    public GetPersonRequestsRequest(String userName) {
        this.userName = userName;
    }

    public ArrayList<code.Friend.AddFriendRequest> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<AddFriendRequest> requests) {
        this.requests = requests;
    }

    public String getUserName() {
        return userName;
    }

}
