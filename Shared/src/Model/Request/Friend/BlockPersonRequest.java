package Model.Request.Friend;

import Model.Request.IRequest;

import java.io.Serializable;

public class BlockPersonRequest implements Serializable, IRequest {
    String blocker;
    String blocked;

    public BlockPersonRequest(String blocker, String blocked) {
        this.blocker = blocker;
        this.blocked = blocked;
    }

    public String getBlocker() {
        return blocker;
    }

    public String getBlocked() {
        return blocked;
    }
}
