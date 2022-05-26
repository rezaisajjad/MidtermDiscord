package Model.Request.Friend;

import java.io.Serializable;
import java.util.ArrayList;

public class GetFriendList implements Serializable {
    String userName;
    ArrayList<FriendRequest> requests;
    /**
     * سرور لیست دوستان فرد را پر میکند
     * در ابتدا لیست خالی است و در سرور پر شده و این کلاس برمیگردد
     */
}
