package Repository;

import Model.Request.*;
import Model.Request.Friend.AddFriendRequest;
import Model.Request.SC.ChatFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class PeopleRepository {

    private static PeopleRepository pr = new PeopleRepository();

    public static PeopleRepository getInstance() {
        return pr;
    }

    private PeopleRepository() {
        Person p1 = new Person();
        p1.setUserName("sajjadre");
        p1.setPassWord("Sajjadre1");
        p1.setEmail("srsjd@yahoo.com");
        Person p11 = new Person();
        p11.setUserName("alireza");
        p11.setPassWord("Alireza1");
        p11.setEmail("srsjd@yahoo.com");
        p1.addFriend(p11.getUserName());
        p11.addFriend(p1.getUserName());
        people.put(p1.getUserName(), p1);
        people.put(p11.getUserName(), p11);
        servers.put(1414, new ServerChat("SSSSS1", "sajjadre", 1414));
        servers.put(1416, new ServerChat("AAA2", "sajjadre", 1416));
        servers.put(1418, new ServerChat("asddsaadsdsa3", "sajjadre", 1418));
        p1.addServerChat(1414);
        p1.addServerChat(1416);
        p1.addServerChat(1418);
    }

    private final HashMap<String, HashSet<String>> friendRequests = new HashMap<>();
    public final HashMap<String, Person> people = new HashMap<>();
    public final HashMap<Integer, ServerChat> servers = new HashMap<>();

    public final HashMap<Integer, ChatFile> files = new HashMap<>();

    public final Random random = new Random();

    /**
     * adds a person to person list
     *
     * @param p person
     * @return adding result
     */
    public boolean addPerson(Person p) {
        if (people.get(p.getUserName()) == null) {
            people.put(p.getUserName(), p);
            return true;
        }
        return false;
    }

    /**
     * check if a username is available
     *
     * @param userName username
     * @return true:available false:unavailable
     */
    public boolean isUserNameAvailable(String userName) {
        return people.get(userName) == null;
    }

    /**
     * login an account is username and password is true
     *
     * @param userName UserName
     * @param passWord PassWord
     * @return logged in person
     */
    public Person loginPerson(String userName, String passWord) {
        Person p;
        if ((p = people.get(userName)) != null && p.getPassWord().equals(passWord)) {
            return p.cloneWithoutList();
        } else {
            return null;
        }
    }

    /**
     * return private chats of a person
     *
     * @param userName userName
     * @return list of chats
     */
    public ArrayList<PrivateChat> getPersonPrivateChats(String userName) {
        Person p;
        if ((p = people.get(userName)) != null) {
            return p.getPrivateChatList();
        } else {
            return null;
        }
    }

    /**
     * send a message to private chat
     *
     * @param privateChat private chat
     * @param message     message
     */
    public void sendPrivateChatMessage(PrivateChat privateChat, PrivateChatMessage message) {
        if (people.get(privateChat.getP1()).getBlockList().contains(privateChat.getP2())
                || people.get(privateChat.getP2()).getBlockList().contains(privateChat.getP1())) {
            return;
        }
        boolean isFound = false;
        PrivateChat pc = null;
        for (var item : getPersonPrivateChats(privateChat.getP1())) {
            if (item.equals(privateChat)) {
                pc = item;
                item.getMessages().add(message);
                isFound = true;
                break;
            }
        }
        for (var item : getPersonPrivateChats(privateChat.getP2())) {
            if (item.equals(privateChat)) {
                if (pc == item)
                    break;
                item.getMessages().add(message);
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            privateChat = new PrivateChat(people.get(privateChat.getP1()).getUserName(), people.get(privateChat.getP2()).getUserName());
            privateChat.addMessage(message);
            people.get(privateChat.getP1()).addPrivateChat(privateChat);
            people.get(privateChat.getP2()).addPrivateChat(privateChat);
        }
    }

    /**
     * returns list of friends
     *
     * @param userName who you want to get friends
     * @return friend list
     */
    public ArrayList<String> getPersonFriends(String userName) {
        return people.get(userName).getFriends();
    }

    /**
     * remover friend
     *
     * @param userName1 person one
     * @param userName2 person two
     */
    public void removeFriend(String userName1, String userName2) {
        for (var item : getPersonFriends(userName1)) {
            if (item.equals(userName2)) {
                getPersonFriends(userName1).remove(item);
                break;
            }
        }
        for (var item : getPersonFriends(userName2)) {
            if (item.equals(userName1)) {
                getPersonFriends(userName2).remove(item);
                break;
            }
        }
    }

    /**
     * add a friend to both friend list
     *
     * @param senderUserName   person one
     * @param receiverUserName person two
     */
    private void _addFriend(String senderUserName, String receiverUserName) {
        people.get(senderUserName).addFriend(people.get(receiverUserName).getUserName());
        people.get(receiverUserName).addFriend(people.get(senderUserName).getUserName());
    }

    /**
     * insert a friendship to friend list of both
     *
     * @param senderUserName   sender
     * @param receiverUserName receiver
     */
    public void addFriend(String senderUserName, String receiverUserName) {
        if (friendRequests.get(senderUserName) != null) {
            for (var item : friendRequests.get(senderUserName)) {
                if (item.equals(receiverUserName)) {
                    _addFriend(senderUserName, receiverUserName);
                    return;
                }
            }
        }
        if (!friendRequests.containsKey(receiverUserName) || friendRequests.get(receiverUserName) == null)
            friendRequests.put(receiverUserName, new HashSet<>());
        friendRequests.get(receiverUserName).add(senderUserName);
    }

    /**
     * returns request of a person
     *
     * @param userName person
     * @return request list
     */
    public ArrayList<AddFriendRequest> getPersonFriendRequests(String userName) {
        if (friendRequests.get(userName) == null || friendRequests.get(userName).size() == 0) {
            return null;
        } else {
            ArrayList<AddFriendRequest> result = new ArrayList<>();
            for (var item : friendRequests.get(userName)) {
                result.add(new AddFriendRequest(item, userName));
            }
            return result;
        }
    }

    /**
     * accept a friend request
     *
     * @param userName1 person one
     * @param userName2 person two
     */
    public void acceptFriendRequest(String userName1, String userName2) {
        _acceptFriendRequest(userName2, userName1);
        _acceptFriendRequest(userName1, userName2);
        _addFriend(userName1, userName2);
    }

    /**
     * accept a friend request
     *
     * @param userName1 person one
     * @param userName2 person two
     */
    private void _acceptFriendRequest(String userName1, String userName2) {
        if (friendRequests.get(userName2) != null) {
            for (var item : friendRequests.get(userName2)) {
                if (item.equals(userName1)) {
                    friendRequests.get(userName2).remove(userName1);
                    if (friendRequests.get(userName2).size() == 0) friendRequests.put(userName2, null);
                }
            }
        }
    }

    /**
     * returns blocked persons
     *
     * @param userName person who block
     * @return block list
     */
    public ArrayList<String> getPersonBlockedList(String userName) {
        return people.get(userName).getBlockList();
    }

    /**
     * block a person
     *
     * @param blocker who blocks
     * @param blocked who blocked
     */
    public void blockAPerson(String blocker, String blocked) {
        people.get(blocker).addBlockedPerson(blocked);
    }

    /**
     * unblock a person
     *
     * @param blocker who unblocks
     * @param blocked who unblocked
     */
    public void unBlockAPerson(String blocker, String blocked) {
        people.get(blocker).removeBlockedPerson(blocked);
    }

    /**
     * return person status
     *
     * @return person status
     */
    public String getStatus(String userName) {
        return people.get(userName).getIsOnline() ? people.get(userName).getStatus().toString() : "Offline";
    }
    /**
     * sets person status
     *
     */
    public void setStatus(String userName, Status status) {
        people.get(userName).setStatus(status);
    }

    /**
     * sets person picture
     *
     * @param userName  person username
     * @param bytes     file bytes
     * @param extension file format
     */
    public void setPersonProfilePicture(String userName, byte[] bytes, String extension) {
        people.get(userName).setImage(bytes);
        people.get(userName).setImageFormat(extension);
    }

    /**
     * creat a new serverChat
     *
     * @param creator    creator username
     * @param serverName name
     */
    public void createServer(String creator, String serverName) {
        Integer id = 0;
        do {
            id = random.nextInt(0, 20000);
        }
        while (servers.get(id) != null);
        ServerChat serverChat = new ServerChat(serverName, creator, id);
        serverChat.getMembers().add(creator);
        servers.put(id, serverChat);
        people.get(creator).addServerChat(id);
    }

    /**
     * returns server list name and uniqueID
     *
     * @param userName person who want server list
     * @return server list name and uniqueID
     */
    public HashMap<Integer, String> getPersonServerChats(String userName) {
        HashMap<Integer, String> list = new HashMap<>();
        for (var item : people.get(userName).getServerChatsList()) {
            list.put(item, servers.get(item).getName());
        }
        return list;
    }

    /**
     * create a channel in specific serve
     *
     * @param name           channel name
     * @param type           channel type
     * @param serverUniqueID channel server
     */
    public void createServerChannel(String name, ChannelType type, Integer serverUniqueID) {
        servers.get(serverUniqueID).getChannels().put(name, new ServerChannel(name, type));
    }

    /**
     * check if a name is free
     *
     * @param serverUniqueID serverUniqueID
     * @param channelName    channelName
     * @return result
     */
    public boolean checkChannelNameAvailability(Integer serverUniqueID, String channelName) {
        return !servers.get(serverUniqueID).getChannels().containsKey(channelName);
    }

    /**
     * return all channels name of a server
     *
     * @param uniqueID serverID
     * @return list of names
     */
    public HashSet<String> getServerChannels(Integer uniqueID, String userName) {
        HashSet<String> res;
        if (servers.get(uniqueID).getRestrictBut().containsKey(userName)) {
            res = servers.get(uniqueID).getRestrictBut().get(userName);
        } else {
            res = new HashSet<>(servers.get(uniqueID).getChannels().keySet());
            for (var channel : servers.get(uniqueID).getChannels().values()) {
                if (channel.getRestrictPersons().contains(userName))
                    res.remove(channel.getName());
            }
        }
        return res;
    }

    /**
     * returns person roles
     *
     * @param userName person username
     * @param serverUniqueID serverID
     * @return list of rules
     */
    public ArrayList<Role> getPersonRoles(String userName, Integer serverUniqueID) {
        ArrayList<Role> roles = new ArrayList<>();
        for (var item : servers.get(serverUniqueID).getRoles().values()) {
            for (var item2 : item.getMembers()) {
                if (item2.trim().equals(userName.trim())) {
                    roles.add(item);
                    break;
                }
            }
        }
        return roles;
    }

    /**
     * removes server
     *
     * @param serverID serverID
     */
    public void removeServer(Integer serverID) {
        for (var item : servers.get(serverID).getMembers()) {
            people.get(item).getServerChatsList().remove(serverID);
        }
        servers.remove(serverID);
    }

    /**
     * adds a perosn to server
     *
     * @param userName       person username
     * @param serverUniqueID server id
     */
    public void addPersonToServer(String userName, Integer serverUniqueID) {
        servers.get(serverUniqueID).getMembers().add(userName);
        people.get(userName).addServerChat(serverUniqueID);
        var pc = new PrivateChat(userName, servers.get(serverUniqueID).getName());
        pc.addMessage(new PrivateChatMessage(servers.get(serverUniqueID).getName(), "Welcome to server!!!"));
        people.get(userName).addPrivateChat(pc);

    }

    /**
     * returns list of server members
     *
     * @param serverUniqueID server id
     * @return Hashset <#UserName, #Status>
     */
    public HashMap<String, String> getServerMembers(Integer serverUniqueID) {
        HashMap<String, String> result = new HashMap<>();
        for (var item:servers.get(serverUniqueID).getMembers()) {
            result.put(item,getStatus(item));
        }
        return result;
    }
    /**
     * removes a person from server
     * @param userName person username
     * @param serverUniqueID server id
     */
    public void removePersonFromServer(String userName, Integer serverUniqueID)
    {
        people.get(userName).removeServerChat(serverUniqueID);
        servers.get(serverUniqueID).getMembers().remove(userName);
        for (var item:servers.get(serverUniqueID).getRoles().values()
             ) {
            item.getMembers().remove(userName);
        }
    }

    /**
     * adds a role to server
     * @param role role
     * @param uniqueID server id
     */
    public void addRoleToServer(Role role,Integer uniqueID)
    {
        servers.get(uniqueID).getRoles().put(role.getName(),role);
    }

    /**
     * return all roles
     *
     * @param serverID serverID
     * @return list of roles name
     */
    public HashSet<String> getServerRoles(Integer serverID) {
        return new HashSet<String>(servers.get(serverID).getRoles().keySet());
    }

    /**
     * changes server name
     *
     * @param name           new name
     * @param serverUniqueID server id
     */
    public void changeServerName(String name, Integer serverUniqueID) {
        servers.get(serverUniqueID).setName(name);
    }

    /**
     * checks if a person exist in server or no
     *
     * @param personUserName person username
     * @param serverID       server id
     * @return result
     */
    public boolean isPersonExistInServer(String personUserName, Integer serverID) {
        return servers.get(serverID).getMembers().contains(personUserName);
    }

    /**
     * changes user password
     *
     * @param username    person username
     * @param currentPass current password
     * @param newPass     new password
     * @return chnging result
     */
    public boolean changePersonPassword(String username, String currentPass, String newPass) {
        if (people.get(username).getPassWord().equals(currentPass)) {
            people.get(username).setPassWord(newPass);
            return true;
        }
        return false;
    }

    /**
     * upload a file in server
     * @param bytes file bytes
     * @return
     */
    public Integer uploadFile(byte[] bytes,String extension) {
        Integer id = 0;
        do {
            id = random.nextInt(0, 20000);
        }
        while (files.get(id) != null);
        files.put(id,new ChatFile(bytes,extension));
        return id;
    }

    /**
     * downloads a file from server
     *
     * @param id file id
     * @return ChatFile
     */
    public ChatFile downloadFile(Integer id) {
        return files.get(id);
    }

    /**
     * removes a role from person
     *
     * @param userName       person username
     * @param roleName       name of role
     * @param serverUniqueID server id
     */
    public void removeRoleFromPerson(String userName, String roleName, Integer serverUniqueID) {
        servers.get(serverUniqueID).getRoles().get(roleName).getMembers().remove(userName);
    }

    /**
     * adds a role to person
     *
     * @param userName       person username
     * @param roleName       name of role
     * @param serverUniqueID server id
     */
    public void addRoleToPerson(String userName, String roleName, Integer serverUniqueID) {
        servers.get(serverUniqueID).getRoles().get(roleName).getMembers().add(userName);
    }

    /**
     * returns member list of a server role
     *
     * @param roleName       role name
     * @param serverUniqueID server id
     * @return HashMap<# username, # status }>
     */
    public HashMap<String, String> getServerRoleMembers(String roleName, Integer serverUniqueID) {
        HashMap<String, String> result = new HashMap<>();
        for (var item : servers.get(serverUniqueID).getRoles().get(roleName).getMembers()) {
            result.put(item, getStatus(item));
        }
        return result;
    }

    /**
     * restrict person from whole server
     *
     * @param userName       person userName
     * @param serverUniqueID server id
     */
    public void restrictPersonFromAllServer(String userName, Integer serverUniqueID) {
        if (!servers.get(serverUniqueID).getRestrictBut().containsKey(userName)) {
            servers.get(serverUniqueID).getRestrictBut().put(userName, new HashSet<>());
        }
    }

    /**
     * restrict person from a channel
     *
     * @param userName       person userName
     * @param serverUniqueID server id
     */
    public void restrictPersonFromAChannel(String userName, Integer serverUniqueID, String channelName) {
        servers.get(serverUniqueID).getChannels().get(channelName).getRestrictPersons().add(userName);
    }

    /**
     * remove restrict person from whole server
     *
     * @param userName       person userName
     * @param serverUniqueID server id
     */
    public void removeRestrictPersonFromAllServer(String userName, Integer serverUniqueID) {
        servers.get(serverUniqueID).getRestrictBut().remove(userName);
    }

    /**
     * remove restrict person from a channel
     *
     * @param userName       person userName
     * @param serverUniqueID server id
     */
    public void removeRestrictPersonFromAChannel(String userName, Integer serverUniqueID, String channelName) {
        servers.get(serverUniqueID).getChannels().get(channelName).getRestrictPersons().remove(userName);
    }

    /**
     * returns list of person id
     *
     * @param serverUniqueID server id
     * @param channelName    channel name
     * @return list of person id
     */
    public HashSet<String> getRestrictPersonsFromAChannel(Integer serverUniqueID, String channelName) {
        return  servers.get(serverUniqueID).getChannels().get(channelName).getRestrictPersons();
    }

    /**
     * returns whole restrict persons
     *
     * @param serverUniqueID server id
     * @return list of person id
     */
    public HashSet<String> getRestrictPersonsFromAllServer(Integer serverUniqueID) {
        return (HashSet<String>) servers.get(serverUniqueID).getRestrictBut().keySet();
    }

    /**
     * unrestict a person just from a channel
     *
     * @param userName       person username
     * @param serverUniqueID server id
     * @param channelName    channelName
     */
    public void unRestrictAllRestrictPersonFromAChannel(String userName, Integer serverUniqueID, String channelName) {
        servers.get(serverUniqueID).getRestrictBut().get(userName).add(channelName);
    }

    /**
     * returns a list of channels that person can see
     * @param userName person userName
     * @param serverUniqueID serverID
     * @return list of channels that person can see
     */
    public HashSet<String> getPersonFreemon(String userName, Integer serverUniqueID)
    {
        return servers.get(serverUniqueID).getRestrictBut().get(userName);
    }
}
