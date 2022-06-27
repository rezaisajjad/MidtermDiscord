package Model.Request.SC;

import Model.Request.IRequest;

import java.io.Serializable;
import java.util.HashSet;

public class GetServerChannelsRequest implements Serializable, IRequest {
    Integer serverUniqueID;
    HashSet<String> channelsName;

    public GetServerChannelsRequest(Integer serverUniqueID) {
        this.serverUniqueID = serverUniqueID;
    }

    public Integer getServerUniqueID() {
        return serverUniqueID;
    }

    public HashSet<String> getChannelsName() {
        return channelsName;
    }

    public void setChannelsName(HashSet<String> channelsName) {
        this.channelsName = channelsName;
    }
}
