package code.Account;

import code.IRequest;

import java.io.Serializable;

public class CheckEmailAvailabilityRequest implements Serializable, IRequest {
    String email;
    boolean available;
    public CheckEmailAvailabilityRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
