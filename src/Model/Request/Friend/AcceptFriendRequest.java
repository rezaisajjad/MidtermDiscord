package Model.Request.Friend;

import Model.Request.Request;

import java.io.Serializable;

public class AcceptFriendRequest extends Request implements Serializable {
    /**
     * درخواست دوستی که از سرور دریافت شده بود در این کلاس قرار دارد
     * این کلاس به سرور میرود و سرور در صورت موفقیت همین را بازمیگرداند
     * در غیر این صورت
     * friendRequest
     * برابر با نال شده و این کلاس برمیگردد
     */
    private FriendRequest friendRequest;

    public AcceptFriendRequest(FriendRequest friendRequest) {
        super();

        this.friendRequest = friendRequest;
    }
    public FriendRequest getFriendRequest() {
        return friendRequest;
    }

    @Override
    public boolean contactsAct() {
        return false;
    }

    @Override
    public boolean serverAct() {
        return false;
    }
}
