import javax.tools.JavaCompiler;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(80);
            while (true) {
                SocketHandler.New(server.accept());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}