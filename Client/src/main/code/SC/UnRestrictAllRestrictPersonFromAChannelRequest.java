package Model.Request.SC;

import Model.Request.IRequest;

import java.io.Serializable;

public class UnRestrictAllRestrictPersonFromAChannelRequest implements Serializable, IRequest {
    Integer serverUniqueID;
    String userName;
    String channelName;

    public UnRestrictAllRestrictPersonFromAChannelRequest(Integer serverUniqueID, String userName, String channelName) {
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
