package Model.Request.SC;

import Model.Request.IRequest;

import java.io.Serializable;

public class SendChannelServerMessageRequest implements IRequest, Serializable {
    String text;
    String sender;
    String channel;
    Integer serverID;

    public SendChannelServerMessageRequest(String text, String sender, String channel, Integer serverID) {
        this.text = text;
        this.sender = sender;
        this.channel = channel;
        this.serverID = serverID;
    }

    public String getChannel() {
        return channel;
    }

    public Integer getServerID() {
        return serverID;
    }

    public String getText() {
        return text;
    }

    public String getSender() {
        return sender;
    }
}
