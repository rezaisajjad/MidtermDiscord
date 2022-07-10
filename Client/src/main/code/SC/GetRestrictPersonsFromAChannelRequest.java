package code.SC;

import code.IRequest;

import java.io.Serializable;
import java.util.HashSet;

public class GetRestrictPersonsFromAChannelRequest implements Serializable, IRequest {
    HashSet<String> list;
    Integer serverUniqueID;
    String channelName;

    public GetRestrictPersonsFromAChannelRequest(Integer serverUniqueID, String channelName) {
        this.serverUniqueID = serverUniqueID;
        this.channelName = channelName;
    }

    public String getChannelName() {
        return channelName;
    }

    public Integer getServerUniqueID() {
        return serverUniqueID;
    }

    public HashSet<String> getList() {
        return list;
    }

    public void setList(HashSet<String> list) {
        this.list = list;
    }
}

