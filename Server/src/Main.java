import Repository.PeopleRepository;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    final static String address = "MySave.save";

    public static void main(String[] args) {
        if (Files.exists(Path.of(address)))
        {
            try {
                FileInputStream inputStream = new FileInputStream(address);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                PeopleRepository.pr = (PeopleRepository) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        new _OnlineHandler().start();
        new _SocketHandler().start();
        new _OnlineHandler_().start();
    }
}