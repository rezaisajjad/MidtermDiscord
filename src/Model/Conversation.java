package Model;

import java.util.ArrayList;

public class Conversation {
    private ArrayList <Message> messages;

    public Conversation() {
        this.messages = new ArrayList<>();
    }
    public void addMessage(Message message) {
        messages.add(message);
    }
    public void removeMessage(Message message){
        messages.remove(message);
    }

}
