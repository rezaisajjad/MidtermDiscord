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
    private String email;
    private String phoneNumber;
    private Status status;
    private HashSet<Person> friends;

    private BufferedImage image;

    public Person(String userName, String passWord, String email, String phoneNumber, Status status, HashSet<Person> friends) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.friends = friends;
    }

    public Person(String userName, String passWord, String email) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
    }

    public void addFriend(Person friend){
        this.friends.add(friend);
    }

    public void removeFriend(Person friend){
        this.friends.remove(friend);
    }
}

