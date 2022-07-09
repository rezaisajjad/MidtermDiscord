package Model.Request.SC;

import Model.Request.IRequest;

import java.io.Serializable;

public class CheckIsPersonExistInServerRequest implements Serializable, IRequest {
    String userName;
    Integer serverID;
    boolean result = false;

    public CheckIsPersonExistInServerRequest(String userName, Integer serverID) {
        this.userName = userName;
        this.serverID = serverID;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getServerID() {
        return serverID;
    }

    public boolean isExist() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
