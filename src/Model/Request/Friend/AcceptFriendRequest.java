package Model.Request.Friend;

import java.io.Serializable;

public class AcceptFriendRequest implements Serializable {
    /**
     * درخواست دوستی که از سرور دریافت شده بود در این کلاس قرار دارد
     * این کلاس به سرور میرود و سرور در صورت موفقیت همین را بازمیگرداند
     * در غیر این صورت
     * friendRequest
     * برابر با نال شده و این کلاس برمیگردد
     */
    private FriendRequest friendRequest;

    public AcceptFriendRequest(FriendRequest friendRequest) {
        this.friendRequest = friendRequest;
    }

    public FriendRequest getFriendRequest() {
        return friendRequest;
    }
}
