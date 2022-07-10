package code.Account;


import code.IRequest;
import code.Person;

import java.io.Serializable;

public class LoginRequest implements Serializable, IRequest {
    private String userName;
    private String passWord;
    private Person p;

    public LoginRequest(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public Person getP() {
        return p;
    }

    public void setP(Person p) {
        this.p = p;
    }
}
