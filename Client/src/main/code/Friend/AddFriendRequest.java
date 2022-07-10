package code.Friend;


import code.IRequest;

import java.io.Serializable;

public class AddFriendRequest  implements Serializable, IRequest {
    String senderUserName;
    String receiverUserName;

    public AddFriendRequest(String senderUserName, String receiverUserName) {
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
