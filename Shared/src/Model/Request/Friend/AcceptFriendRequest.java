package Model.Request.Friend;

import Model.Request.IRequest;

import java.io.Serializable;

public class AcceptFriendRequest implements Serializable, IRequest {
    AddFriendRequest friendRequest;

    public AcceptFriendRequest(AddFriendRequest friendRequest) {
        this.friendRequest = friendRequest;
    }

    public AddFriendRequest getFriendRequest() {
        return friendRequest;
    }
}
