package Model;

import java.util.*;

public class Requestable {
    protected String Name;
    protected String id;
    protected ArrayList<Message> Messages;
    protected HashMap<String, Person> Readers;
    protected HashMap<String, Person> Writers;


    public Requestable(String userName) {
        this.Name = userName;
        Messages = new ArrayList<>();
    }
    public void addMessage(Message message) {
        Messages.add(message);
    }
    public void removeMessage(Message message) {
        Messages.remove(message);
    }
    public ArrayList<Message> getMessages() {
        return Messages;
    }
    public void editMessage(Message oldMessage, String newMessage) {
        oldMessage.setMessage(newMessage);
    }
    public void addReader(String reader, HashMap<String, Person> persons) {
        Readers.put(Name, persons.get(reader));
    }
    public void addWriter(String writer, HashMap<String, Person> persons) {
        Writers.put(Name, persons.get(writer));
    }
    public void removeReader(String reader) {
        Readers.remove(reader);
    }
    public void removeWriter(String writer) {
        Writers.remove(writer);
    }
    public HashMap<String, Person> getReaders() {
        return Readers;
    }
    public HashMap<String, Person> getWriters() {
        return Writers;
    }



}
