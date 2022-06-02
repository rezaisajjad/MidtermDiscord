package Model.Request.Friend;

import Model.Person;
import Model.Request.Request;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

/**
 * درخواست دوستی که شامل ارسال کننده و دریافت کنند است
 * این درخواست به سرور میرود و درصورت موفقیت باز میگردد
 * در غیر این صورت با پارامتر های نال برگردانده میشود
 */
public class FriendRequest extends Request implements Serializable {
    private final String sender;
    private final String receiver;

    public FriendRequest(String sender, String receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }
    public String getSender() {
        return sender;
    }
    public String getReceiver() {
        return receiver;
    }

    @Override
    public boolean serverAct(HashMap<String, Person> persons) {
        persons.get(receiver).addRequest(sender);
        return true;
    }
    @Override
    public HashSet<String> selfAct(HashMap<String,Person> persons) {
        return null;
    }}
