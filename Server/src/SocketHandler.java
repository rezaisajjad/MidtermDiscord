import Model.Request.Account.CheckUserNameAvailabilityRequest;
import Model.Request.Account.GetPersonPrivateChats;
import Model.Request.Account.LoginRequest;
import Model.Request.Account.SignUpRequest;
import Repository.PeopleRepository;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketHandler extends Thread {
    public static PeopleRepository people = new PeopleRepository();

    private Socket socket;

    private SocketHandler(Socket socket) {
        this.socket = socket;
        start();
        System.out.println("Connected: \n\t\t" + socket.toString());
    }

    public static SocketHandler New(Socket socket) {
        return new SocketHandler(socket);
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Object request = (Object) inputStream.readObject();
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            // do work
            if (request instanceof CheckUserNameAvailabilityRequest res) {
                res.setAvailability(people.isUserNameAvailable(res.getUserName()));
                outputStream.writeObject(res);
            }
            else if (request instanceof LoginRequest res) {
                res.setP(people.checkLogin(res.getUserName(), res.getPassWord()));
                outputStream.writeObject(res);
            }
            else if (request instanceof SignUpRequest res) {
                if (!people.addPerson(res.getP())) {
                    res.setP(null);
                }
                outputStream.writeObject(res);
            } else if (request instanceof GetPersonPrivateChats res) {
                res.setPrivateChats(people.getPersonPrivateChats(res.getUserName()));
                outputStream.writeObject(res);
            }
            socket.close();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println(e.getMessage());
            //throw new RuntimeException(e);
        }
    }
}
