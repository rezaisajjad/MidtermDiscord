package Model.Request.Friend;

import Model.Person;
import Model.Request.Request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GetBlockListRequest extends Request implements Serializable {
    String userName;

    @Override
    public boolean serverAct(HashMap<String, Person> persons) {
        return false;
    }
    @Override
    public HashSet<String> selfAct(HashMap<String, Person> persons) {
        return persons.get(userName).getBlockList();
    }
}
