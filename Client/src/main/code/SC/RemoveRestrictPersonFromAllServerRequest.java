package code.SC;

import code.IRequest;

import java.io.Serializable;

public class RemoveRestrictPersonFromAllServerRequest implements Serializable, IRequest {
    Integer serverUniqueID;
    String userName;

    public RemoveRestrictPersonFromAllServerRequest(Integer serverUniqueID, String userName) {
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
