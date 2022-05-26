package Model.Request.Friend;

import java.io.Serializable;
import java.util.ArrayList;

public class GetBlockListRequest implements Serializable {
    String userName;
    ArrayList<FriendRequest> requests;
    /**
     * سرور لیست افراد بلاک شده توسط فرد را پر میکند
     * در ابتدا لیست خالی است و در سرور پر شده و این کلاس برمیگردد
     */
}
