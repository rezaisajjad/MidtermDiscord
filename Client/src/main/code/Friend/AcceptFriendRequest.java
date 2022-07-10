package code.Friend;



import code.IRequest;

import java.io.Serializable;

public class AcceptFriendRequest implements Serializable, IRequest {
    code.Friend.AddFriendRequest friendRequest;

    public AcceptFriendRequest(code.Friend.AddFriendRequest friendRequest) {
        this.friendRequest = friendRequest;
    }

    public AddFriendRequest getFriendRequest() {
        return friendRequest;
    }
}
