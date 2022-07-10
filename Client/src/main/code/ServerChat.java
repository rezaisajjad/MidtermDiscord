package code;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;

public class ServerChat implements Serializable {
    HashMap<String, Role> roles = new HashMap<>();
    HashMap<String, ServerTextChannel> channels = new HashMap<>();
    HashMap<String, LocalDateTime> registerDates = new HashMap<>();
    HashMap<String, HashSet<String>> restrictBut = new HashMap<>();
    HashSet<String> members = new HashSet<>();

    String name;
    Integer uniqueID;

    public HashMap<String, LocalDateTime> getRegisterDates() {
        return registerDates;
    }

    public void setRegisterDates(HashMap<String, LocalDateTime> registerDates) {
        this.registerDates = registerDates;
    }

    public HashMap<String, HashSet<String>> getRestrictBut() {
        return restrictBut;
    }

    public ServerChat(String name, String creator, Integer uniqueID) {
        this.name = name;
        this.uniqueID = uniqueID;
        members.add(creator);
        Role owner = new Role("owner",true, true, true,
                true, true, true, true,
                true);
        owner.getMembers().add(creator);
        roles.put(owner.getName(), owner);
    }

    public HashMap<String, Role> getRoles() {
        return roles;
    }

    public HashMap<String, ServerTextChannel> getChannels() {
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

    public void setName(String name) {
        this.name = name;
    }
}
