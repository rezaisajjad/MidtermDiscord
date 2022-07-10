package code.SC;


import code.IRequest;

import java.io.Serializable;

public class RestrictPersonFromAllServerRequest implements Serializable, IRequest {
    Integer serverUniqueID;
    String userName;

    public RestrictPersonFromAllServerRequest(Integer serverUniqueID, String userName) {
        this.serverUniqueID = serverUniqueID;
        this.userName = userName;
    }

    public Integer getServerUniqueID() {
        return serverUniqueID;
    }

    public String getUserName() {
        return userName;
    }
}
