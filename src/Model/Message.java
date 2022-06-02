package Model;

import java.util.*;

public class Message {
    private Person sender;
    private String receiverId;
    private String message;
    private final HashMap<String,Feedback> feedbacks;

    public Message(Person sender, String receiverUsername, String message) {
        this.sender = sender;
        this.receiverId = receiverUsername;
        this.message = message;
        this.feedbacks = new HashMap<>();
    }

    public Person getSender() {
        return sender;
    }
    public void setSender(Person sender) {
        this.sender = sender;
    }
    public String getReceiverId() {
        return receiverId;
    }
    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public HashMap<String,Feedback> getFeedbacks() {
        return feedbacks;
    }
    public void addFeedback(String username, Feedback feedback) {
        this.feedbacks.put(username, feedback);
    }
}
