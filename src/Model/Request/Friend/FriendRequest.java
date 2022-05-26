package Model.Request.Friend;

import Model.Person;

import java.io.Serializable;

/**
 * درخواست دوستی که شامل ارسال کننده و دریافت کنند است
 * این درخواست به سرور میرود و درصورت موفقیت باز میگردد
 * در غیر این صورت با پارامتر های نال برگردانده میشود
 */
public class FriendRequest implements Serializable {
    private Person sender;
    private Person receiver;
}
