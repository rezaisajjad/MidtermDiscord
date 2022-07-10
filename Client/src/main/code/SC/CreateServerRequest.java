package code.SC;


import code.IRequest;

import java.io.Serializable;

public class CreateServerRequest implements Serializable, IRequest {
    String creatorUserName;
    String name;

    public CreateServerRequest(String creatorUserName, String name) {
        this.creatorUserName = creatorUserName;
        this.name = name;
    }

    public String getCreatorUserName() {
        return creatorUserName;
    }

    public String getName() {
        return name;
    }
}
