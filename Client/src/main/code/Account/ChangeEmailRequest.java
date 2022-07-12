package code.Account;

import code.IRequest;

import java.io.Serializable;

public class ChangeEmailRequest implements Serializable, IRequest {
    String username;
    String newMail;

    public ChangeEmailRequest(String username, String newMail) {
        this.username = username;
        this.newMail = newMail;
    }

    public String getUsername() {
        return username;
    }

    public String getNewMail() {
        return newMail;
    }
}
