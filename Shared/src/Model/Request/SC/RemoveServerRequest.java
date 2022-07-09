package Model.Request.SC;

import Model.Request.IRequest;

import java.io.Serializable;

public class RemoveServerRequest implements Serializable, IRequest {
    Integer id;

    public RemoveServerRequest(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
