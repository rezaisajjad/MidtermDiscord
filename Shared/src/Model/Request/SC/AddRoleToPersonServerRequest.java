package Model.Request.SC;

import Model.Request.IRequest;

import java.io.Serializable;

public class AddRoleToPersonServerRequest  implements Serializable, IRequest {
    Integer serverUniqueID;

    String roleName;
    String personUserName;

    public AddRoleToPersonServerRequest(Integer serverUniqueID, String roleName, String personUserName) {
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
