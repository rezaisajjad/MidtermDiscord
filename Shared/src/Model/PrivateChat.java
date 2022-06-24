package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class PrivateChat implements Serializable {
    private ArrayList<PrivateChatMessage> messages = new ArrayList<>();
    Person p1, p2;

    public PrivateChat(Person p1, Person p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Person getP1() {
        return p1;
    }

    public Person getP2() {
        return p2;
    }

    public ArrayList<PrivateChatMessage> getMessages() {
        return messages;
    }

    public void addMessage(PrivateChatMessage message) {
        messages.add(message);
    }

    public void setMessages(ArrayList<PrivateChatMessage> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PrivateChat)) return false;

        PrivateChat that = (PrivateChat) o;

        return (p2.equals(that.p1) && p1.equals(that.p2)) || (p1.equals(that.p1) && p2.equals(that.p2));
    }

    @Override
    public int hashCode() {
        int result = getMessages().hashCode();
        result = 31 * result + p1.hashCode();
        result = 31 * result + p2.hashCode();
        return result;
    }
}
