package Model.Request.Friend;

import Model.Person;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class GetFriendList  implements Serializable {
    String userName;

    public boolean serverAct(HashMap<String, Person> persons) {
        return false;
    }

    /* public HashSet<String> selfAct(HashMap<String, Person> persons) {
       // return persons.get(userName).getFriends();
    }*/
}
