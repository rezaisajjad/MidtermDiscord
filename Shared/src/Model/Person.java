package Model;

import Model.Request.PrivateChat;
import Model.Request.IRequest;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

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
    private ArrayList<PrivateChat> privateChatList;
    private BufferedImage image;

    public Person() {
        friends = new ArrayList<>();
        blockList = new ArrayList<>();
        privateChatList = new ArrayList<>();
    }

    public Person cloneWithoutList() {
        var person = new Person();
        person.setPassWord(passWord);
        person.setUserName(userName);
        person.setEmail(email);
        person.setPhoneNumber(phoneNumber);
        person.setStatus(status);
        person.setFriends(null);
        person.setBlockList(null);
        person.setPrivateChatList(null);
        person.setImage(image);
        return person;
    }

    // list methods
    public boolean addPrivateChat(PrivateChat pc) {
        return privateChatList.add(pc);
    }

    public boolean addBlockedPerson(String userName) {
        return blockList.add(userName);
    }

    public boolean addFriend(String userName) {
        return friends.add(userName);
    }

    public boolean removePrivateChat(PrivateChat pc) {
        return privateChatList.remove(pc);
    }

    public boolean removeBlockedPerson(String userName) {
        return blockList.remove(userName);
    }

    public boolean removeFriend(String userName) {
        return friends.remove(userName);
    }

    //getters
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

    public ArrayList<String> getFriends() {
        return friends;
    }

    public ArrayList<String> getBlockList() {
        return blockList;
    }

    public ArrayList<PrivateChat> getPrivateChatList() {
        return privateChatList;
    }

    public BufferedImage getImage() {
        return image;
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

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    public void setBlockList(ArrayList<String> blockList) {
        this.blockList = blockList;
    }

    public void setPrivateChatList(ArrayList<PrivateChat> privateChatList) {
        this.privateChatList = privateChatList;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        return getUserName().equals(person.getUserName());
    }

    @Override
    public int hashCode() {
        return getUserName().hashCode();
    }
}

