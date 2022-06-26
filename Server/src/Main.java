import Model.*;

import javax.tools.JavaCompiler;
import java.io.*;
import java.net.*;
import java.util.*;

public class Main {
    public static ArrayList<Requestable> requestAbles;
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(11223);
            while (true) SocketHandler.New(server.accept());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}