package code.SC;


import code.IRequest;

import java.io.Serializable;

public class AddPersonToServerRequest implements Serializable, IRequest {
    String userName;
    Integer serverUniqueID;

    public AddPersonToServerRequest(String userName, Integer serverUniqueID) {
        this.userName = userName;
        this.serverUniqueID = serverUniqueID;
    }


    public String getUserName() {
        return userName;
    }

    public Integer getServerUniqueID() {
        return serverUniqueID;
    }
}
