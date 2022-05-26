package Model.Request;

import java.io.Serializable;

public enum RequestType implements Serializable {
    /////////
    SendFriendRequest,
    ReceiveFriendRequest,
    ReceiveBlockList,
    BlockPerson,
    AcceptFriendRequest,
    ReceiveFriendList,
    /////////
    Login,
    SignUp
}
