package Model;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by 40031020 on 5/23/2022.
 */
public class Person extends Requestable implements Serializable {
    private String passWord;
    private String userName;
    private String email;
    private String phoneNumber;
    private Status status;
    private HashSet<String> friends;
    private HashSet<String> blockList;
    private BufferedImage image;

    // Setters
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
    public HashSet<String> getFriends() {
        return friends;
    }
    public HashSet<String> getBlockList() {
        return blockList;
    }

    //constructors
    public Person(String userName, String passWord, String email, String phoneNumber, Status status) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.blockList = new HashSet<>();
        this.friends = new HashSet<>();
    }
    public Person(String userName, String passWord, String email) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.blockList = new HashSet<>();
        this.friends = new HashSet<>();
    }

    //add or remove to lists
    public void addFriend(String friend){
        this.friends.add(friend);
    }
    public void removeFriend(String friend){
        this.friends.remove(friend);
    }
    public void block(String person){
        this.blockList.add(person);
    }
    public void unblock(String person){
        this.blockList.remove(person);
    }

}

