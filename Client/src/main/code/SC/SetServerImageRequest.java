package code.SC;

import code.IRequest;

import java.io.Serializable;

public class SetServerImageRequest implements IRequest, Serializable {
    Integer serverID;
    Integer imageID;

    public SetServerImageRequest(Integer serverID, Integer imageID) {
        this.serverID = serverID;
        this.imageID = imageID;
    }

    public Integer getServerID() {
        return serverID;
    }

    public Integer getImageID() {
        return imageID;
    }
}
