import code.*;
import Repository.PeopleRepository;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.HashMap;
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
            Thread.sleep(2000);
            Scanner scn = new Scanner(socket.getInputStream());
            if (scn.hasNext())
            if ((p = PeopleRepository.getInstance().people.get(scn.next())) != null) {
                p.setOnline(true);
                lastOnline.put(p.getUserName(), LocalDateTime.now());
            }
            socket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
