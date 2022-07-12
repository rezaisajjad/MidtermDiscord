package code.Account;


import code.IRequest;

import java.io.Serializable;

public class ChangeProfilePictureRequest implements IRequest, Serializable {
    String userName;
    Integer fileID;

    public ChangeProfilePictureRequest(String userName,Integer fileID) {
        this.userName = userName;
        this.fileID = fileID;
    }

    public Integer getImageID() {
        return fileID;
    }


    public String getUserName() {
        return userName;
    }
}
