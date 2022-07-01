package ClientController;

import View.UI;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class OnlineThread extends Thread {
    @Override
    public void run() {
        Socket socket = null;
        String userName = "";
        String updates = "";
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                socket = new Socket("localhost", 14141);
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                if (!(userName = UI.get().getPersonUserName()).equals("")) {
                    pw.println(userName);
                    pw.flush();
                    socket.close();
                }
                updates = Server.getServer().getUpdates(userName);
                if (updates != null && !updates.equals(""))
                    System.err.println(updates);
                System.err.flush();
                Thread.sleep(10000);
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
