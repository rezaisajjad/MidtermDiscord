package code.SC;

import code.IRequest;
import code.Role;

import java.io.Serializable;
import java.util.ArrayList;

public class GetPersonRolesRequest implements Serializable, IRequest {
    Integer uniqueID;
    String userName;
    ArrayList<Role> roles;

    public GetPersonRolesRequest(Integer uniqueID, String userName) {
        this.uniqueID = uniqueID;
        this.userName = userName;
    }

    public ArrayList<Role> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Role> roles) {
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getUniqueID() {
        return uniqueID;
    }
}
