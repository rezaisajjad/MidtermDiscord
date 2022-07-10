package code;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

public class ServerTextChannel implements Serializable {
    String name;
    HashSet<String> restrictPersons = new HashSet<>();
    code.ChannelType type;

    ArrayList<code.TextChannelMessage> pins=  new ArrayList<>();
    ArrayList<code.TextChannelMessage> messages = new ArrayList<>();

    public ServerTextChannel(String name, ChannelType type) {
        this.name = name;
        this.type = type;
    }

    public ArrayList<code.TextChannelMessage> getPins() {
        return pins;
    }

    public void setPins(ArrayList<code.TextChannelMessage> pins) {
        this.pins = pins;
    }

    public HashSet<String> getRestrictPersons() {
        return restrictPersons;
    }

    public String getName() {
        return name;
    }

    public ArrayList<code.TextChannelMessage> getMessages(LocalDateTime from) {
        ArrayList<code.TextChannelMessage> messages = new ArrayList<>();
        for (var item:this.messages) {
            if (!Duration.between(from,item.getDateTime()).isNegative())
                messages.add(item);
        }
        return messages;
    }
    public ArrayList<TextChannelMessage> getMessages() {
        return messages;
    }
}
