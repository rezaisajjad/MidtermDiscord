package code.SC;

import code.IRequest;

import java.io.Serializable;

public class CheckChannelNameAvailabilityRequest implements Serializable, IRequest {
    Integer serverUniqueID;
    String channelName;
    boolean result=false;

    public CheckChannelNameAvailabilityRequest(Integer serverUniqueID, String channelName) {
        this.serverUniqueID = serverUniqueID;
        this.channelName = channelName;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Integer getServerUniqueID() {
        return serverUniqueID;
    }

    public String getChannelName() {
        return channelName;
    }
}
