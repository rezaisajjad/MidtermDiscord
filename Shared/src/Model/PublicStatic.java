package Model;

import java.util.HashMap;

public class PublicStatic {
    public static HashMap<String, Person> persons = new HashMap<>();
    public static Person findPerson(String userName, HashMap<String, Person> persons) {
        if (persons.containsKey(userName)) {
            return persons.get(userName);
        }
        return null;
    }
    public static void addPerson(Person person) {
        persons.put(person.getUserName(), person);
    }
    public static void  removePerson(String userName) {
        persons.remove(userName);
    }

}
