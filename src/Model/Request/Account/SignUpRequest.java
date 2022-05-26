package Model.Request.Account;

import Model.Person;

import java.io.Serializable;

public class SignUpRequest implements Serializable {
    /**
     * طبق کلاس لاگین
     * درصورت درست بودن همین کلاس برگردانده میشود و در غیر اینصورت
     * person
     * برابر با نال قرار گرفته و برمیگردد
     */
    Person p;
    public SignUpRequest(Person p) {
        this.p = p;
    }
}
