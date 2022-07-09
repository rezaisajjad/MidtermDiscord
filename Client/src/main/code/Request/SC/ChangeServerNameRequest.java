package Model.Request.SC;

import Model.Request.IRequest;

import java.io.Serializable;

public class ChangeServerNameRequest implements Serializable, IRequest {
    Integer serverUniqueID;
    String name;

    public ChangeServerNameRequest(Integer serverUniqueID, String name) {
        this.serverUniqueID = serverUniqueID;
        this.name = name;
    }

    public Integer getServerUniqueID() {
        return serverUniqueID;
    }

    public String getName() {
        return name;
    }
}
