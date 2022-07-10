package code.SC;

import code.IRequest;
import code.Reaction;
import code.TextChannelMessage;

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
