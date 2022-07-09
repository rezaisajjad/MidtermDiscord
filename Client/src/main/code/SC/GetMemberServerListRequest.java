package Model.Request.SC;

import Model.Request.IRequest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class GetMemberServerListRequest implements Serializable, IRequest {
    Integer serverUniqueID;
    HashMap<String,String> users;

    public GetMemberServerListRequest(Integer serverUniqueID) {
        this.serverUniqueID = serverUniqueID;
    }

    public void setUsers(HashMap<String, String> users) {
        this.users = users;
    }

    public Integer getServerUniqueID() {
        return serverUniqueID;
    }

    public HashMap<String, String> getUsers() {
        return users;
    }
}
