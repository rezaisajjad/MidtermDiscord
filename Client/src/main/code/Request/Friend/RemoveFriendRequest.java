package Model.Request.Friend;

import Model.Request.IRequest;

import java.io.Serializable;

public class RemoveFriendRequest implements Serializable, IRequest {
    String senderUserName;
    String receiverUserName;

    public RemoveFriendRequest(String senderUserName, String receiverUserName) {
        this.senderUserName = senderUserName;
        this.receiverUserName = receiverUserName;
    }

    public String getSenderUserName() {
        return senderUserName;
    }

    public String getReceiverUserName() {
        return receiverUserName;
    }
}
