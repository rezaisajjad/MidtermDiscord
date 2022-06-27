package Model.Request;

import java.util.ArrayList;
import java.util.HashSet;

public class ServerChannel {
    String name;
    HashSet<String> users = new HashSet<>();
    ChannelType type;
    ArrayList<PrivateChatMessage> messages = new ArrayList<>();

    public ServerChannel(String name, ChannelType type) {
        this.name = name;
        this.type = type;
    }
}
