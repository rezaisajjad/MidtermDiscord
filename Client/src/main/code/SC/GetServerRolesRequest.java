package code.SC;

import code.IRequest;

import java.io.Serializable;
import java.util.HashSet;

public class GetServerRolesRequest implements Serializable, IRequest {
    HashSet<String> roles;
    Integer serverID;

    public GetServerRolesRequest(Integer serverID) {
        this.serverID = serverID;
    }

    public HashSet<String> getRoles() {
        return roles;
    }

    public void setRoles(HashSet<String> roles) {
        this.roles = roles;
    }

    public Integer getServerID() {
        return serverID;
    }
}
