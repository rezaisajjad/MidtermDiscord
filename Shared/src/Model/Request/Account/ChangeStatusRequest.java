package Model.Request.Account;

import Model.Request.IRequest;
import Model.Status;

import java.io.Serializable;

public class ChangeStatusRequest implements Serializable, IRequest {
    String userName;
    Status status;

    public ChangeStatusRequest(String userName, Status status) {
        this.userName = userName;
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public Status getStatus() {
        return status;
    }
}
