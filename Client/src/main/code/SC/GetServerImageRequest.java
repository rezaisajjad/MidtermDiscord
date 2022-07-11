package code.SC;

import code.IRequest;

import java.io.Serializable;

public class GetServerImageRequest implements IRequest, Serializable {
    Integer serverID;
    Integer serverImage;

    public GetServerImageRequest(Integer serverID) {
        this.serverID = serverID;
    }

    public Integer getServerID() {
        return serverID;
    }

    public Integer getServerImage() {
        return serverImage;
    }

    public void setServerImage(Integer serverImage) {
        this.serverImage = serverImage;
    }
}
