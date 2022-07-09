package Model.Request.Account;

import Model.Request.IRequest;

import java.io.Serializable;

public class GetUpdatesRequest implements Serializable, IRequest {
    String userName;
    String update;

    public GetUpdatesRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }
}
