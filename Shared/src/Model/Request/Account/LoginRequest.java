package Model.Request.Account;

import Model.Person;
import Model.Request.IRequest;
import com.sun.net.httpserver.Request;

import java.io.Serializable;

/**
 * این کلاس به سرور فرستاده میشود
 * در صورت درست بودن اطلاعات ، این کلاس مجدد برگردانده میشود
 * در صورت غلط بودن اطلاعات
 * فیلد ها نال برگردانده میشوند
 */
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
