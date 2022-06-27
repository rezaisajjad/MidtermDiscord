package Model.Request.Friend;

import Model.Request.IRequest;

import java.io.Serializable;
import java.util.ArrayList;

public class GetPersonRequestsRequest implements Serializable, IRequest {
    String userName;
    ArrayList<AddFriendRequest> requests;

    public GetPersonRequestsRequest(String userName) {
        this.userName = userName;
    }

    public ArrayList<AddFriendRequest> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<AddFriendRequest> requests) {
        this.requests = requests;
    }

    public String getUserName() {
        return userName;
    }

}
