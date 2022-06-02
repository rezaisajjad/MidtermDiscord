package Model.Request.Friend;

import Model.Person;
import Model.Request.Request;

import java.io.*;
import java.util.*;

public class BlockPersonRequest extends Request implements Serializable {
    /**
     * شخصی که بلاک میکند
     */
    private String blocker;
    /**
     * شخصی که بلاک میشود
     */
    private String blocked;

    @Override
    public boolean serverAct(HashMap<String,Person> persons) {
        if(persons.get(blocker)==null || persons.get(blocked)==null)
            return false;
        persons.get(persons.get(blocker).getUserName()).block(blocked);
        return true;
    }
    @Override
    public HashSet<String> selfAct(HashMap<String,Person> persons) {
        return null;
    }
}
