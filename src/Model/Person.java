package Model;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 40031020 on 5/23/2022.
 */
public class Person implements Serializable {
    private String userName;
    private String passWord;
    private String email;
    private String phoneNumber;
    private Status status;
    private ArrayList<Person> friends;

    private BufferedImage image;

    public Person(String userName, String passWord, String email, String phoneNumber, Status status, ArrayList<Person> friends) {
        this.userName = userName;
        this.passWord = passWord;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.friends = friends;
    }
}
