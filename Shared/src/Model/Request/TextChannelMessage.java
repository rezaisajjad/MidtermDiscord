package Model.Request;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;

public class TextChannelMessage implements Serializable {
  private   LocalDateTime dateTime;
  private   String message;
  private   HashMap<Reaction,Integer> reactions=new HashMap<>();
  private   String senderUserName;

    public TextChannelMessage(LocalDateTime dateTime, String message, String senderUserName) {
        reactions.put(Reaction.like,0);
        reactions.put(Reaction.disLike,0);
        reactions.put(Reaction.laugh,0);
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

    public HashMap<Reaction, Integer> getReactions() {
        return reactions;
    }

    public String getSenderUserName() {
        return senderUserName;
    }


}
