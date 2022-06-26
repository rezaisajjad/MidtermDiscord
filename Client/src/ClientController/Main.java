package ClientController;

import View.UI;

public class Main {
    public static void main(String[] args) {
        OnlineThread onlineThread= new OnlineThread();
        onlineThread.start();
        UI ui= UI.get();
        ui.start();
    }
}