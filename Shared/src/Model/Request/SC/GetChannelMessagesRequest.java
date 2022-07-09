package Model.Request.SC;

import Model.Request.IRequest;
import Model.Request.PrivateChatMessage;
import Model.Request.TextChannelMessage;

import java.io.Serializable;
import java.util.ArrayList;

public class GetChannelMessagesRequest implements Serializable, IRequest {
    String channelName;
    Integer serverID;
    ArrayList<TextChannelMessage> messages;
    String personID;

    public GetChannelMessagesRequest(String channelName, Integer serverID, String personID) {
        this.channelName = channelName;
        this.serverID = serverID;
        this.personID = personID;
    }

    public void setMessages(ArrayList<TextChannelMessage> messages) {
        this.messages = messages;
    }

    public String getChannelName() {
        return channelName;
    }

    public Integer getServerID() {
        return serverID;
    }

    public ArrayList<TextChannelMessage> getMessages() {
        return messages;
    }

    public String getPersonID() {
        return personID;
    }
}
