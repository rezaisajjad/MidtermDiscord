package Model;

import java.awt.image.BufferedImage;
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
    private Status status;
    private ArrayList<String> friends;
    private ArrayList<String> blockList;
    private ArrayList<String> Requests;
    private ArrayList<PrivateChat> privateChatList;
    private BufferedImage image;

    // Setters


    public void setPrivateChatList(ArrayList<PrivateChat> privateChatList) {
        this.privateChatList = privateChatList;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getters
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

    public Status getStatus() {
        return status;
    }

    public BufferedImage getImage() {
        return image;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    public ArrayList<String> getBlockList() {
        return blockList;
    }

    public void setBlockList(ArrayList<String> blockList) {
        this.blockList = blockList;
    }

    public ArrayList<String> getRequests() {
        return Requests;
    }

    public void setRequests(ArrayList<String> requests) {
        Requests = requests;
    }

    public ArrayList<PrivateChat> getPrivateChatList() {
        return privateChatList;
    }

    //constructors
    public Person() {
        this.blockList = new ArrayList<>();
        this.friends = new ArrayList<>();
        this.privateChatList = new ArrayList<>();
        this.Requests = new ArrayList<>();
    }

    //add or remove to lists
    public void addFriend(String friend) {
        this.friends.add(friend);
    }

    public void removeFriend(String friend) {
        this.friends.remove(friend);
    }

    public void block(String person) {
        this.blockList.add(person);
    }

    public void unblock(String person) {
        this.blockList.remove(person);
    }

    public void addRequest(String request) {
        this.Requests.add(request);
    }

    public void removeRequest(String request) {
        this.Requests.remove(request);
    }

    public void addPrivateChat(PrivateChat chat) {
        this.privateChatList.add(chat);
    }

    public void removeChat(String chat) {
        this.privateChatList.remove(chat);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (getPassWord() != null ? !getPassWord().equals(person.getPassWord()) : person.getPassWord() != null)
            return false;
        if (getUserName() != null ? !getUserName().equals(person.getUserName()) : person.getUserName() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(person.getEmail()) : person.getEmail() != null) return false;
        return getPhoneNumber() != null ? getPhoneNumber().equals(person.getPhoneNumber()) : person.getPhoneNumber() == null;
    }

    @Override
    public int hashCode() {
        int result = getPassWord() != null ? getPassWord().hashCode() : 0;
        result = 31 * result + (getUserName() != null ? getUserName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getFriends() != null ? getFriends().hashCode() : 0);
        result = 31 * result + (getBlockList() != null ? getBlockList().hashCode() : 0);
        result = 31 * result + (getRequests() != null ? getRequests().hashCode() : 0);
        result = 31 * result + (getPrivateChatList() != null ? getPrivateChatList().hashCode() : 0);
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        return result;
    }
}
