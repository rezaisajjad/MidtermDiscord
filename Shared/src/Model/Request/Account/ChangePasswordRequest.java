package Model.Request.Account;

import Model.Request.IRequest;

import java.io.Serializable;

public class ChangePasswordRequest implements Serializable, IRequest {
    String username;
    String currentPassword;
    String newPassword;
    boolean result;

    public ChangePasswordRequest(String username, String currentPassword, String newPassword) {
        this.username = username;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
