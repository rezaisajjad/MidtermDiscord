package code.Friend;


import code.IRequest;

import java.io.Serializable;
import java.util.ArrayList;

public class GetBlockListRequest implements IRequest, Serializable {
    String userName;
    ArrayList<String> persons;

    public GetBlockListRequest(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<String> getPersons() {
        return persons;
    }

    public void setPersons(ArrayList<String> persons) {
        this.persons = persons;
    }
}
