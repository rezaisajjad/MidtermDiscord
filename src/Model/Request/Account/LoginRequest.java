package Model.Request.Account;

import java.io.Serializable;

/**
 * این کلاس به سرور فرستاده میشود
 * در صورت درست بودن اطلاعات ، این کلاس مجدد برگردانده میشود
 * در صورت غلط بودن اطلاعات
 * فیلد ها نال برگردانده میشوند
 */
public class LoginRequest implements Serializable {
    private String userName;
    private String passWord;
}
