package code.Account;


import code.IRequest;

import java.io.Serializable;

public class ChangeProfilePictureRequest implements IRequest, Serializable {
    String userName;
    byte[] image;
    String suffix;

    public ChangeProfilePictureRequest(String userName, byte[] image, String suffix) {
        this.userName = userName;
        this.image = image;
        this.suffix = suffix;
    }

    public byte[] getImage() {
        return image;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getUserName() {
        return userName;
    }
}
