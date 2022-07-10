package code;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ServerTextChannel implements Serializable {
    String name;
    HashSet<String> restrictPersons = new HashSet<>();
    ChannelType type;

    ArrayList<TextChannelMessage> pins=  new ArrayList<>();
    ArrayList<TextChannelMessage> messages = new ArrayList<>();

    public ServerTextChannel(String name, ChannelType type) {
        this.name = name;
        this.type = type;
    }

    public ArrayList<TextChannelMessage> getPins() {
        return pins;
    }

    public void setPins(ArrayList<TextChannelMessage> pins) {
        this.pins = pins;
    }

    public HashSet<String> getRestrictPersons() {
        return restrictPersons;
    }

    public String getName() {
        return name;
    }

    public ArrayList<TextChannelMessage> getMessages(LocalDateTime from) {
        ArrayList<TextChannelMessage> messages = new ArrayList<>();
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
