package Model.Request.Chats;

import Model.Person;

import java.util.HashMap;
import java.util.HashSet;

public class JoinChat {
    private final Person joiner;
    private final String chatId;

    public JoinChat(Person joiner, String chatId) {
        this.joiner = joiner;
        this.chatId = chatId;
    }

    public Person getJoiner() {
        return joiner;
    }
    public String getChatId() {
        return chatId;
    }

    public boolean serverAct(HashMap<String, Person> persons) {
        return false;
    }

    public HashSet<String> selfAct(HashMap<String, Person> persons) {
        return null;
    }
}
