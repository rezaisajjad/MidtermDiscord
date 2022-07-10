package code.SC;

import code.IRequest;
import code.TextChannelMessage;

import java.io.Serializable;

public class PinMessageToChannelRequest implements Serializable, IRequest {
    TextChannelMessage message;
    String channelName;
    Integer serverID;

    public PinMessageToChannelRequest(TextChannelMessage message, String channelName, Integer serverID) {
        this.message = message;
        this.channelName = channelName;
        this.serverID = serverID;
    }

    public TextChannelMessage getMessage() {
        return message;
    }

    public String getChannelName() {
        return channelName;
    }

    public Integer getServerID() {
        return serverID;
    }
}
