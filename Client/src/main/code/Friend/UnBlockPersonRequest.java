package code.Friend;


import code.IRequest;

import java.io.Serializable;

public class UnBlockPersonRequest implements IRequest, Serializable {
    String blocker;
    String blocked;

    public UnBlockPersonRequest(String blocker, String blocked) {
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
