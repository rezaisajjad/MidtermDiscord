package Repository;

import Model.Person;
import Model.Request.Friend.AddFriendRequest;
import Model.Request.PrivateChat;
import Model.Request.PrivateChatMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PeopleRepository {
    public PeopleRepository() {
        Person p1 = new Person();
        p1.setUserName("sajjadre");
        p1.setPassWord("Sajjadre1");
        p1.setEmail("srsjd@yahoo.com");
        Person p2 = new Person();
        p2.setUserName("HamidReza");
        p2.setPassWord("HamidReza");
        p2.setEmail("HamidReza@yahoo.com");
        Person p3 = new Person();
        p3.setUserName("Kambiz");
        p3.setPassWord("Kambiz");
        p3.setEmail("Kambiz@yahoo.com");
        Person p4 = new Person();
        p4.setUserName("Mohammad");
        p4.setPassWord("Mohammad");
        p4.setEmail("Mohammad@yahoo.com");
        people.put(p1.getUserName(), p1);
        people.put(p2.getUserName(), p2);
        people.put(p3.getUserName(), p3);
        people.put(p4.getUserName(), p4);
        var chat = new PrivateChat(p1, p2);
        var message = new PrivateChatMessage("HamidReza", "Salam");
        chat.addMessage(message);
        message = new PrivateChatMessage("HamidReza", "Khoobi?");
        chat.addMessage(message);
        message = new PrivateChatMessage("Sajjad", "Khoobam");
        chat.addMessage(message);
        message = new PrivateChatMessage("Sajjad", "To Chetori?");
        chat.addMessage(message);
        p1.addPrivateChat(chat);
        p1.getFriends().add(p2);
        p1.getFriends().add(p3);
        p1.getFriends().add(p4);
    }

    private final HashMap<String, HashSet<String>> friendRequests = new HashMap<>();
    private final HashMap<String, Person> people = new HashMap<>();

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
        Person p = null;
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
        Person p = null;
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
        boolean isFound = false;
        for (var item : getPersonPrivateChats(privateChat.getP1().getUserName())) {
            if (item.equals(privateChat)) {
                item.getMessages().add(message);
                isFound = true;
                break;
            }
        }
        for (var item : getPersonPrivateChats(privateChat.getP2().getUserName())) {
            if (item.equals(privateChat)) {
                item.getMessages().add(message);
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            privateChat.addMessage(message);
            privateChat.getP1().addPrivateChat(privateChat);
            privateChat.getP2().addPrivateChat(privateChat);
        }
    }
    ///////////////////////////////////

    public ArrayList<Person> getPersonFriends(String userName) {
        return people.get(userName).getFriends();
    }

    public void removeFriend(String userName1, String userName2) {
        for (var item : getPersonFriends(userName1)) {
            if (item.getUserName().equals(userName2)) {
                getPersonFriends(userName1).remove(item);
                break;
            }
        }
        for (var item : getPersonFriends(userName2)) {
            if (item.getUserName().equals(userName1)) {
                getPersonFriends(userName2).remove(item);
                break;
            }
        }
    }

    public void _addFriend(String senderUserName, String receiverUserName) {
        people.get(senderUserName).getFriends().add(people.get(receiverUserName));
        people.get(receiverUserName).getFriends().add(people.get(senderUserName));
    }

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

    public void acceptFriendRequest(String userName1, String userName2) {
        if (friendRequests.get(userName1) != null) {
            for (var item : friendRequests.get(userName1)) {
                if (item.equals(userName2)) {
                    friendRequests.get(userName1).remove(userName2);
                    if (friendRequests.get(userName1).size() == 0) friendRequests.put(userName1, null);
                }
            }
        }
        if (friendRequests.get(userName2) != null) {
            for (var item : friendRequests.get(userName2)) {
                if (item.equals(userName1)) {
                    friendRequests.get(userName2).remove(userName1);
                    if (friendRequests.get(userName2).size() == 0) friendRequests.put(userName2, null);
                }
            }
        }
        _addFriend(userName1, userName2);
    }
}
