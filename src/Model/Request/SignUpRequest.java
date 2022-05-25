package Model.Request;

import Model.Person;

import java.io.Serializable;

public class SignUpRequest implements Serializable {
    Person p;

    public SignUpRequest(Person p) {
        this.p = p;
    }
}
