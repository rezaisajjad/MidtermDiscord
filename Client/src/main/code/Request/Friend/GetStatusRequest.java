package Model.Request.Friend;

import Model.Request.IRequest;

import java.io.Serializable;

public class GetStatusRequest implements Serializable, IRequest {
    String userName;
    String status;

    public GetStatusRequest(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
