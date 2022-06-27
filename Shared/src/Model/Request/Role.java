package Model.Request;

import java.util.ArrayList;
import java.util.HashSet;

public class Role {
    HashSet<String> members = new HashSet<>();
    String name;
    private boolean createChannel = false;
    private boolean removeChannel = false;
    private boolean removePerson = false;
    private boolean restrictAccessChannel = false;
    private boolean restrictPersonAccess = false;
    private boolean changeServerName = false;
    private boolean observeChatHistory = false;
    private boolean pinTextMessage = false;

    public Role(String name, boolean createChannel, boolean removeChannel, boolean removePerson, boolean restrictAccessChannel, boolean restrictPersonAccess, boolean changeServerName, boolean observeChatHistory, boolean pinTextMessage) {
        this.name = name;
        this.createChannel = createChannel;
        this.removeChannel = removeChannel;
        this.removePerson = removePerson;
        this.restrictAccessChannel = restrictAccessChannel;
        this.restrictPersonAccess = restrictPersonAccess;
        this.changeServerName = changeServerName;
        this.observeChatHistory = observeChatHistory;
        this.pinTextMessage = pinTextMessage;
    }

    public String getName() {
        return name;
    }

    public HashSet<String> getMembers() {
        return members;
    }

    public boolean isCreateChannel() {
        return createChannel;
    }

    public boolean isRemoveChannel() {
        return removeChannel;
    }

    public boolean isRemovePerson() {
        return removePerson;
    }

    public boolean isRestrictAccessChannel() {
        return restrictAccessChannel;
    }

    public boolean isRestrictPersonAccess() {
        return restrictPersonAccess;
    }

    public boolean isChangeServerName() {
        return changeServerName;
    }

    public boolean isObserveChatHistory() {
        return observeChatHistory;
    }

    public boolean isPinTextMessage() {
        return pinTextMessage;
    }

    public static Role integrateRolls(ArrayList<Role> roles) {
        boolean createChannel = false;
        boolean removeChannel = false;
        boolean removePerson = false;
        boolean restrictAccessChannel = false;
        boolean restrictPersonAccess = false;
        boolean changeServerName = false;
        boolean observeChatHistory = false;
        boolean pinTextMessage = false;
        for (var role : roles) {
            if (role.isCreateChannel())
                createChannel = true;
            if (role.isRemoveChannel())
                removeChannel = true;
            if (role.isRemovePerson())
                removePerson = true;
            if (role.isRestrictAccessChannel())
                restrictAccessChannel = true;
            if (role.isRestrictPersonAccess())
                restrictPersonAccess = true;
            if (role.isChangeServerName())
                changeServerName = true;
            if (role.isObserveChatHistory())
                observeChatHistory = true;
            if (role.isPinTextMessage())
                pinTextMessage = true;
        }
        return new Role("integrated",createChannel, removeChannel, removePerson, restrictAccessChannel, restrictPersonAccess,
                changeServerName, observeChatHistory, pinTextMessage);
    }
}
