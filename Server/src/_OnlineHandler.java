import java.io.IOException;
import java.net.ServerSocket;

public class _OnlineHandler extends Thread{
    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(14141);
            while (true) OnlineHandler.New(server.accept());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
