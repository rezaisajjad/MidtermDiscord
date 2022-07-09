package Model.Request.SC;

import Model.Request.IRequest;
import Model.Request.TextChannelMessage;

import java.io.Serializable;
import java.util.ArrayList;

public class GetPinedMessagesRequest implements Serializable , IRequest {
    ArrayList<TextChannelMessage> messages;
    String channelName;
    Integer serverID;

    public GetPinedMessagesRequest(String channelName, Integer serverID) {
        this.channelName = channelName;
        this.serverID = serverID;
    }

    public void setMessages(ArrayList<TextChannelMessage> messages) {
        this.messages = messages;
    }

    public ArrayList<TextChannelMessage> getMessages() {
        return messages;
    }

    public String getChannelName() {
        return channelName;
    }

    public Integer getServerID() {
        return serverID;
    }
}
