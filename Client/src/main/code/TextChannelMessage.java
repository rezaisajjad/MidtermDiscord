package code;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;

public class TextChannelMessage implements Serializable {
    private LocalDateTime dateTime;
    private String message;
    private HashMap<code.Reaction, Integer> reactions = new HashMap<>();
    private String senderUserName;

    public TextChannelMessage(LocalDateTime dateTime, String message, String senderUserName) {
        reactions.put(code.Reaction.like, 0);
        reactions.put(code.Reaction.disLike, 0);
        reactions.put(code.Reaction.laugh, 0);
        this.dateTime = dateTime;
        this.message = message;
        this.senderUserName = senderUserName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getMessage() {
        return message;
    }

    public HashMap<code.Reaction, Integer> getReactions() {
        return reactions;
    }

    public String getSenderUserName() {
        return senderUserName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof code.TextChannelMessage)) return false;

        code.TextChannelMessage that = (code.TextChannelMessage) o;

        if (getDateTime() != null ? !getDateTime().equals(that.getDateTime()) : that.getDateTime() != null)
            return false;
        if (getMessage() != null ? !getMessage().equals(that.getMessage()) : that.getMessage() != null) return false;
        return getSenderUserName() != null ? getSenderUserName().equals(that.getSenderUserName()) : that.getSenderUserName() == null;
    }

    @Override
    public String toString() {
        return senderUserName+
                ": " + message + '\t' + "[like= " + reactions.get(code.Reaction.like)
                        + "   dislike= " + reactions.get(code.Reaction.disLike)
                        + "   laugh= " + reactions.get(Reaction.laugh)+"]";
    }
}
