package code.SC;

import code.IRequest;

import java.io.Serializable;
import java.util.HashMap;

public class GetPersonServersRequest implements Serializable, IRequest {
    HashMap<Integer, String> list;
    String userName;

    public GetPersonServersRequest(String userName) {
        this.userName = userName;
    }

    public HashMap<Integer, String> getList() {
        return list;
    }

    public void setList(HashMap<Integer, String> list) {
        this.list = list;
    }

    public String getUserName() {
        return userName;
    }

}
