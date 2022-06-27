package Model.Request;

import java.util.HashMap;

public class ServerChat {
    HashMap<String, Role> roles = new HashMap<>();
    HashMap<String, ServerChannel> channels = new HashMap<>();
    String name;
    String creator;

    Integer uniqueID;

    public ServerChat(String name, String creator,Integer uniqueID) {
        this.name = name;
        this.creator = creator;
        this.uniqueID=uniqueID;
    }

    public HashMap<String, Role> getRoles() {
        return roles;
    }

    public HashMap<String, ServerChannel> getChannels() {
        return channels;
    }

    public String getCreator() {
        return creator;
    }

    public Integer getUniqueID() {
        return uniqueID;
    }

    public String getName() {
        return name;
    }
}
