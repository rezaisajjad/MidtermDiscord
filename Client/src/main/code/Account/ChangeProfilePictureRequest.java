package code.Account;


import code.IRequest;

import java.io.Serializable;

public class ChangeProfilePictureRequest implements IRequest, Serializable {
    String userName;
    String fileID;

    public ChangeProfilePictureRequest(String userName,String fileID) {
        this.userName = userName;
        this.fileID = fileID;
    }

    public String getImageID() {
        return fileID;
    }


    public String getUserName() {
        return userName;
    }
}
