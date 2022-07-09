package Model.Request.SC;

import Model.Request.IRequest;
import Model.Request.Role;

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
