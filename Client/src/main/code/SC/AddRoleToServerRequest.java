package code.SC;

import code.IRequest;
import code.Role;

import java.io.Serializable;

public class AddRoleToServerRequest implements IRequest, Serializable {
    Role role;
    Integer serverUniqueID;

    public AddRoleToServerRequest(Role role, Integer serverUniqueID) {
        this.role = role;
        this.serverUniqueID = serverUniqueID;
    }

    public Role getRole() {
        return role;
    }

    public Integer getServerUniqueID() {
        return serverUniqueID;
    }
}
