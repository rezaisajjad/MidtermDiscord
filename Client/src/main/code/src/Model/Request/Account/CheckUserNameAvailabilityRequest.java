package Model.Request.Account;

import Model.Request.IRequest;

import java.io.Serializable;

public class CheckUserNameAvailabilityRequest implements IRequest , Serializable {
    String userName;
    boolean result=false;

    public CheckUserNameAvailabilityRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isAvailable() {
        return result;
    }

    public void setAvailability(boolean result) {
        this.result = result;
    }
}
