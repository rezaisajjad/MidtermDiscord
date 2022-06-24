package Model.Request.Account;

import Model.Person;
import Model.Request.IRequest;

import java.io.Serializable;

public class SignUpRequest implements Serializable, IRequest {
    Person p;

    public SignUpRequest(Person p) {
        this.p = p;
    }

    public Person getP() {
        return p;
    }

    public void setP(Person p) {
        this.p = p;
    }
}
