import java.io.IOException;
import java.net.ServerSocket;

public class _SocketHandler extends Thread{
    @Override
    public void run() {
        try {
            ServerSocket server = new ServerSocket(11223);
            while (true) SocketHandler.New(server.accept());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
