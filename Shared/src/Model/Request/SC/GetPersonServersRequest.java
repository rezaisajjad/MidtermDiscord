package Model.Request.SC;

import Model.Request.IRequest;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class GetPersonServersRequest implements Serializable, IRequest {
    HashMap<String,Integer> list;
    String userName;

    public GetPersonServersRequest(String userName) {
        this.userName = userName;
    }

    public HashMap<String, Integer> getList() {
        return list;
    }

    public void setList(HashMap<String, Integer> list) {
        this.list = list;
    }

    public String getUserName() {
        return userName;
    }

}
