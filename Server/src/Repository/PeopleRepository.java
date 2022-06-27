package Repository;

import Model.Person;
import Model.Request.Account.ChangeStatusRequest;
import Model.Request.Friend.AddFriendRequest;
import Model.Request.PrivateChat;
import Model.Request.PrivateChatMessage;
import Model.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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
    }

    private final HashMap<String, HashSet<String>> friendRequests = new HashMap<>();
    public final HashMap<String, Person> people = new HashMap<>();

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
            return p;
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
     * @param userName person username
     * @param bytes file bytes
     * @param extension file format
     */
    public void setPersonProfilePicture(String userName, byte[] bytes,String extension) {
        people.get(userName).setImage(bytes);
        people.get(userName).setImageFormat(extension);
    }
}
