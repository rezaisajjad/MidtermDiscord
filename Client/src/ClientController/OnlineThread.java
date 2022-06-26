package ClientController;

import Model.Request.IRequest;
import View.UI;

import java.beans.Transient;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.Executors;

public class OnlineThread extends Thread {
    @Override
    public void run() {
        Socket socket = null;
        String userName = "";
        while (true) {
            try {
                socket = new Socket("localhost", 15151);
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                if (!(userName = UI.get().getPersonUserName()).equals("")) {
                    pw.println(userName);
                    pw.flush();
                    socket.close();
                }
                Thread.sleep(15000);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (socket != null)
                        socket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
