package Model.Request.Friend;

import java.io.Serializable;
import java.util.ArrayList;

public class GetFriendRequest implements Serializable {
    String userName;
    ArrayList<FriendRequest> requests;
    /**
     * سرور لیست درخواست های دوستی را برای یوزنیم داده شده پر میکند
     * در ابتدا لیست خالی است و در سرور پر شده و این کلاس برمیگردد
     */
}
