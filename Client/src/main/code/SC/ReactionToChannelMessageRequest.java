package Model.Request.SC;

import Model.Request.IRequest;
import Model.Request.Reaction;
import Model.Request.TextChannelMessage;

import java.io.Serializable;

public class ReactionToChannelMessageRequest implements Serializable, IRequest {
    TextChannelMessage message;
    Reaction reaction;
    String channelName;
    Integer serverID;

    public ReactionToChannelMessageRequest(TextChannelMessage message, Reaction reaction, String channelName, Integer serverID) {
        this.message = message;
        this.reaction = reaction;
        this.channelName = channelName;
        this.serverID = serverID;
    }

    public TextChannelMessage getMessage() {
        return message;
    }

    public Reaction getReaction() {
        return reaction;
    }

    public String getChannelName() {
        return channelName;
    }

    public Integer getServerID() {
        return serverID;
    }
}
