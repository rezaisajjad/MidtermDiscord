package Model.Request;

import java.util.ArrayList;
import java.util.HashSet;

public class ServerChannel {
    String name;
    HashSet<String> restrictPersons = new HashSet<>();
    ChannelType type;
    ArrayList<PrivateChatMessage> messages = new ArrayList<>();

    public ServerChannel(String name, ChannelType type) {
        this.name = name;
        this.type = type;
    }

    public HashSet<String> getRestrictPersons() {
        return restrictPersons;
    }

    public String getName() {
        return name;
    }
}
