package Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class Chat extends Requestable implements Serializable {
    private Person sender;
    private Person receiver;
    private String message;

    public Chat(String userName, Person sender, Person receiver, String message) {
        super(userName);
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public Person getReceiver() {
        return receiver;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}