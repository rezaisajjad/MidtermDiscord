package code;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by 40031020 on 5/23/2022.
 */
public class Person implements Serializable {
    private String passWord;
    private String userName;
    private String email;
    private String phoneNumber;
    private code.Status status = code.Status.Idle;
    private boolean isOnline = false;
    private ArrayList<String> friends = new ArrayList<>();
    private ArrayList<String> blockList = new ArrayList<>();
    private ArrayList<code.PrivateChat> privateChatList = new ArrayList<>();
    private HashSet<Integer> serverChatsList = new HashSet<>();
    private Integer imageID;

    public code.Person cloneWithoutList() {
        var person = new code.Person();
        person.setPassWord(passWord);
        person.setUserName(userName);
        person.setEmail(email);
        person.setPhoneNumber(phoneNumber);
        person.setStatus(status);
        person.setOnline(false);
        person.setFriends(null);
        person.setBlockList(null);
        person.setPrivateChatList(null);
        person.setImageID(imageID);
        return person;
    }

    // list methods
    public boolean addPrivateChat(code.PrivateChat pc) {
        return privateChatList.add(pc);
    }

    public boolean addBlockedPerson(String userName) {
        return blockList.add(userName);
    }

    public boolean addFriend(String userName) {
        return friends.add(userName);
    }

    public boolean removePrivateChat(code.PrivateChat pc) {
        return privateChatList.remove(pc);
    }

    public boolean removeBlockedPerson(String userName) {
        return blockList.remove(userName);
    }

    public boolean removeFriend(String userName) {
        return friends.remove(userName);
    }

    public boolean addServerChat(Integer id) {
        return serverChatsList.add(id);
    }

    public boolean removeServerChat(Integer id) {
        return serverChatsList.remove(id);
    }

    //getters


    public HashSet<Integer> getServerChatsList() {
        return serverChatsList;
    }


    public boolean getIsOnline() {
        return isOnline;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public code.Status getStatus() {
        return status;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public ArrayList<String> getBlockList() {
        return blockList;
    }

    public ArrayList<code.PrivateChat> getPrivateChatList() {
        return privateChatList;
    }

    public Integer getImageID() {
        return imageID;
    }
    //setters


    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    public void setBlockList(ArrayList<String> blockList) {
        this.blockList = blockList;
    }

    public void setPrivateChatList(ArrayList<PrivateChat> privateChatList) {
        this.privateChatList = privateChatList;
    }

    public void setImageID(Integer imageID) {
        this.imageID = imageID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof code.Person)) return false;

        code.Person person = (code.Person) o;

        return getUserName().equals(person.getUserName());
    }

    public void setServerChatsList(HashSet<Integer> serverChatsList) {
        this.serverChatsList = serverChatsList;
    }

    @Override
    public int hashCode() {
        return getUserName().hashCode();
    }
}

