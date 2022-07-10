package code.SC;

import code.IRequest;

import java.io.Serializable;

public class RestrictPersonFromAChannelRequest implements Serializable, IRequest {
    Integer serverUniqueID;
    String userName;
    String channelName;

    public RestrictPersonFromAChannelRequest(Integer serverUniqueID, String userName, String channelName) {
        this.serverUniqueID = serverUniqueID;
        this.userName = userName;
        this.channelName = channelName;
    }

    public Integer getServerUniqueID() {
        return serverUniqueID;
    }

    public String getUserName() {
        return userName;
    }

    public String getChannelName() {
        return channelName;
    }
}
