package code.Account;

import code.IRequest;

import java.io.Serializable;

public class ChangePhoneNumberRequest implements Serializable, IRequest {
    String username;

    String phoneNumber;

    public ChangePhoneNumberRequest(String username, String phoneNumber) {
        this.username = username;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
