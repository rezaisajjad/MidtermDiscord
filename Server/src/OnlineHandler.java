import Model.Person;
import Repository.PeopleRepository;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class OnlineHandler extends Thread {
    public static final HashMap<String, LocalDateTime> lastOnline = new HashMap<>();
    private Socket socket;
    private Person p;

    private OnlineHandler(Socket socket) {
        this.socket = socket;
        start();
        System.err.println("Connected: \n\t\t" + socket.toString());
        System.err.flush();
    }

    public static OnlineHandler New(Socket socket) {
        return new OnlineHandler(socket);
    }

    @Override
    public void run() {
        try {
            Scanner scn = new Scanner(socket.getInputStream());
            if ((p = PeopleRepository.getInstance().people.get(scn.next())) != null) {
                p.setOnline(true);
                lastOnline.put(p.getUserName(), LocalDateTime.now());
            }
            socket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
