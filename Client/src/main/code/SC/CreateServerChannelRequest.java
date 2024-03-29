package code.SC;

import code.ChannelType;
import code.IRequest;

import java.io.Serializable;

public class CreateServerChannelRequest implements Serializable, IRequest {
    String name;
    ChannelType type;
    Integer serverUniqueID;

    public CreateServerChannelRequest(String name, ChannelType type, Integer serverUniqueID) {
        this.name = name;
        this.type = type;
        this.serverUniqueID = serverUniqueID;
    }

    public String getName() {
        return name;
    }

    public ChannelType getType() {
        return type;
    }

    public Integer getServerUniqueID() {
        return serverUniqueID;
    }
}
