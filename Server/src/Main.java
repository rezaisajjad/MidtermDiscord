import Model.*;

import javax.tools.JavaCompiler;
import java.io.*;
import java.net.*;
import java.util.*;

public class Main {
    public static ArrayList<Requestable> requestAbles;

    public static void main(String[] args) {
        new _OnlineHandler().start();
        new _SocketHandler().start();
        new _OnlineHandler_().start();
    }
}