package code.SC;

import code.IRequest;

import java.io.Serializable;
import java.util.HashSet;

public class GetServerChannelsRequest implements Serializable, IRequest {
    Integer serverUniqueID;
    HashSet<String> channelsName;

    String userName;

    public GetServerChannelsRequest(Integer serverUniqueID, String userName) {
        this.serverUniqueID = serverUniqueID;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }
}
