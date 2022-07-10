package code.Friend;



import code.IRequest;

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
