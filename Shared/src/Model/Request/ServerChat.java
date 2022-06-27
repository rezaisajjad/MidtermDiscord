package Model.Request;

import java.util.HashMap;
import java.util.HashSet;

public class ServerChat {
    HashMap<String, Role> roles = new HashMap<>();
    HashMap<String, ServerChannel> channels = new HashMap<>();

    HashSet<String> members = new HashSet<>();
    String name;
    Integer uniqueID;

    public ServerChat(String name, String creator, Integer uniqueID) {
        this.name = name;
        this.uniqueID = uniqueID;
        Role owner = new Role("owner",true, true, true,
                true, true, true, true,
                true);
        owner.getMembers().add(creator);
        roles.put(owner.getName(), owner);
    }

    public HashMap<String, Role> getRoles() {
        return roles;
    }

    public HashMap<String, ServerChannel> getChannels() {
        return channels;
    }

    public String getCreator() {
        return (String) roles.get("owner").getMembers().toArray()[0];
    }

    public HashSet<String> getMembers() {
        return members;
    }

    public void setMembers(HashSet<String> members) {
        this.members = members;
    }

    public Integer getUniqueID() {
        return uniqueID;
    }

    public String getName() {
        return name;
    }
}
