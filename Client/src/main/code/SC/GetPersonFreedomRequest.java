package Model.Request.SC;

import Model.Request.IRequest;

import java.io.Serializable;
import java.util.HashSet;

public class GetPersonFreedomRequest implements Serializable, IRequest {
    String userName;
    Integer serverID;

    HashSet<String> channels;

    public GetPersonFreedomRequest(String userName, Integer serverID) {
        this.userName = userName;
        this.serverID = serverID;
    }

    public void setChannels(HashSet<String> channels) {
        this.channels = channels;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getServerID() {
        return serverID;
    }

    public HashSet<String> getChannels() {
        return channels;
    }
}
