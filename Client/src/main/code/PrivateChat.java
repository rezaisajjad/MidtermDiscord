package code;

import java.io.Serializable;
import java.util.ArrayList;

public class PrivateChat implements Serializable {
    private ArrayList<code.PrivateChatMessage> messages = new ArrayList<>();
    String p1, p2;
    public PrivateChat(String p1, String p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public String getP1() {
        return p1;
    }

    public String getP2() {
        return p2;
    }

    public ArrayList<code.PrivateChatMessage> getMessages() {
        return messages;
    }

    public void addMessage(code.PrivateChatMessage message) {
        messages.add(message);
    }

    public void setMessages(ArrayList<PrivateChatMessage> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof code.PrivateChat)) return false;

        code.PrivateChat that = (code.PrivateChat) o;

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
