package Model.Request.SC;

import Model.Request.IRequest;

import java.io.Serializable;
import java.util.HashMap;

public class RemoveRoleFromPersonServerRequest implements Serializable, IRequest {
    Integer serverUniqueID;

    String roleName;
    String personUserName;

    public RemoveRoleFromPersonServerRequest(Integer serverUniqueID, String roleName, String personUserName) {
        this.serverUniqueID = serverUniqueID;
        this.roleName = roleName;
        this.personUserName = personUserName;
    }

    public Integer getServerUniqueID() {
        return serverUniqueID;
    }

    public String getRoleName() {
        return roleName;
    }

    public String getPersonUserName() {
        return personUserName;
    }
}
