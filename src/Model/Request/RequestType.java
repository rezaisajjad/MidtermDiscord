package Model.Request;

import java.io.Serializable;

public enum RequestType implements Serializable {
    /////////
    SendMessage,
    ReceiveMessage,
    SendFriendRequest,
    ReceiveFriendRequest,
    ReceiveBlockList,
    BlockPerson,
    AcceptFriendRequest,
    ReceiveFriendList,
    ReceiveNotifications,
    /////////
    Login,
    SignUp
}
