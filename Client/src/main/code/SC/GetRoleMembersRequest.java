package Model.Request.SC;

import Model.Request.IRequest;

import java.io.Serializable;
import java.util.HashMap;

public class GetRoleMembersRequest implements Serializable, IRequest {
    Integer serverUniqueID;
    HashMap<String,String> users;

    String roleName;

    public GetRoleMembersRequest(Integer serverUniqueID, String roleName) {
        this.serverUniqueID = serverUniqueID;
        this.roleName = roleName;
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

    public String getRoleName() {
        return roleName;
    }
}
