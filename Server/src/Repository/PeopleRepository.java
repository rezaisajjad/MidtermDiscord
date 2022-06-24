package Repository;

import Model.Person;
import Model.PrivateChat;

import java.util.ArrayList;
import java.util.HashMap;

public class PeopleRepository {
    public PeopleRepository() {
        Person p1 = new Person();
        p1.setUserName("sajjadre");
        p1.setPassWord("Sajjadre1");
        p1.setEmail("srsjd@yahoo.com");
        Person p2 = new Person();
        p2.setUserName("HamidReza");
        p2.setPassWord("HamidReza");
        p2.setEmail("HamidReza@yahoo.com");
        Person p3 = new Person();
        p3.setUserName("Kambiz");
        p3.setPassWord("Kambiz");
        p3.setEmail("Kambiz@yahoo.com");
        Person p4 = new Person();
        p4.setUserName("Mohammad");
        p4.setPassWord("Mohammad");
        p4.setEmail("Mohammad@yahoo.com");
        persons.put(p1.getUserName(),p1);
        persons.put(p2.getUserName(),p2);
        persons.put(p3.getUserName(),p3);
        persons.put(p4.getUserName(),p4);
        p1.addPrivateChat(new PrivateChat(p1,p2));
        p2.addPrivateChat(new PrivateChat(p1,p2));
        p1.addPrivateChat(new PrivateChat(p1,p3));
        p3.addPrivateChat(new PrivateChat(p1,p3));
        p1.addPrivateChat(new PrivateChat(p1,p4));
        p4.addPrivateChat(new PrivateChat(p1,p4));
    }

    private final HashMap<String, Person> persons = new HashMap<>();

    public boolean addPerson(Person p) {
        if (persons.get(p.getUserName()) == null) {
            persons.put(p.getUserName(), p);
            return true;
        }
        return false;
    }

    public boolean isUserNameAvailable(String userName) {
        return persons.get(userName) == null;
    }

    public Person checkLogin(String userName, String password) {
        Person p = null;
        if ((p = persons.get(userName)) != null && p.getPassWord().equals(password)) {
            return p;
        } else {
            return null;
        }
    }

    public ArrayList<PrivateChat> getPersonPrivateChats(String userName) {
        Person p = null;
        if ((p = persons.get(userName)) != null) {
            return p.getPrivateChatList();
        } else {
            return null;
        }
    }
}
