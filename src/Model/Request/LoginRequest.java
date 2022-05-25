package Model.Request;

import java.io.Serializable;

/**
 * Created by 40031020 on 5/23/2022.
 */
public class LoginRequest implements Serializable {
    private String userName;
    private String passWord;
    private String token;
}
