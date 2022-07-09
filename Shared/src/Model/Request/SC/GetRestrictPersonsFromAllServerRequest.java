package Model.Request.SC;

import Model.Request.IRequest;

import java.io.Serializable;
import java.util.HashSet;

public class GetRestrictPersonsFromAllServerRequest implements Serializable, IRequest {
    HashSet<String> list;
    Integer serverUniqueID;

    public GetRestrictPersonsFromAllServerRequest(Integer serverUniqueID) {
        this.serverUniqueID = serverUniqueID;
    }

    public Integer getServerUniqueID() {
        return serverUniqueID;
    }

    public HashSet<String> getList() {
        return list;
    }

    public void setList(HashSet<String> list) {
        this.list = list;
    }
}
