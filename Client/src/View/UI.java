package View;

import ClientController.Server;
import Model.Person;
import Model.PrivateChat;
import Model.Request.Account.GetPersonPrivateChats;
import Model.Request.Account.LoginRequest;
import Model.Request.Account.SignUpRequest;

import java.util.ArrayList;


/**
 * Created by 40031020 on 5/23/2022.
 */
public class UI {
    //region Base
    Person person = null;

    private static UI ui = new UI();

    private UI() {
    }

    public static void start() {
        ui.doStartMenu();
    }

    private void printList(String... args) {
        int i = 0;
        for (String arg : args) {
            System.out.println((++i) + ") " + arg);
        }
    }
    //endregion

    //region Start Menu
    void doStartMenu() {
        showStartMenu();
        startMenuHandler();
    }

    void startMenuHandler() {
        switch (Scn.readNumber()) {
            case 1 -> {
                doLoginMenu();
            }
            case 2 -> {
                doSignUpMenu();
            }
            case 3 -> {
                System.out.println("It's not completed");
                doStartMenu();
            }
            case 4 -> {
                System.exit(0);
            }
        }
    }

    public void showStartMenu() {
        printList("Login", "SignUp", "Forgot password", "Exit");
    }
    //endregion

    //region SignUp Menu
    void doSignUpMenu() {
        showSignUpMenu();
        signUpMenuHandler();
    }

    void signUpMenuHandler() {
        Person person = Scn.readPerson();
        SignUpRequest request = new SignUpRequest(person);
        SignUpRequest response = (SignUpRequest) Server.sendRequest(request);
        if (response.getP() != null) {
            System.out.println("Successfully added");
            this.person = response.getP();
            doMainMenu();
        } else {
            System.out.println("Error");
            doStartMenu();
        }
    }

    public void showSignUpMenu() {
        //we can add menu option if is needed;
    }

    //endregion

    //region Login Menu
    void doLoginMenu() {
        showLoginMenu();
        loginMenuHandler();
    }

    void loginMenuHandler() {
        LoginRequest request = Scn.readLoginRequest();
        LoginRequest response = (LoginRequest) Server.sendRequest(request);
        if (response.getP() != null) {
            System.out.println("Successfully logged in");
            this.person = response.getP();
            doMainMenu();
        } else {
            System.out.println("Error");
            doStartMenu();
        }
    }

    public void showLoginMenu() {
        //we can add menu option if is needed;
    }

    //endregion

    //region Main menu
    void doMainMenu() {
        showMainMenu();
        mainMenuHandler();
    }

    void mainMenuHandler() {
        switch (Scn.readNumber()) {
            case 1 -> {
                doChatsMenu();
            }
            case 2 -> {
                doFriendsMenu();
            }
            case 3 -> {
                doSettingMenu();
            }
            case 4 -> {
                this.person = null;
                doStartMenu();
            }
        }
    }

    public void showMainMenu() {
        printList("chats", "friend", "settings", "log Out");
    }
    //endregion

    //region Chats Menu
    void doChatsMenu() {
        showChatsMenu();
        chatsMenuHandler();
    }

    void chatsMenuHandler() {
        int counter = 0;
        GetPersonPrivateChats request = new GetPersonPrivateChats(person.getUserName());
        GetPersonPrivateChats response = (GetPersonPrivateChats) Server.sendRequest(request);
        if (response.getPrivateChats() != null) {
            for (var chat : response.getPrivateChats()) {
                System.out.println((++counter) + ") " + ((chat.getP1().equals(person)) ? chat.getP2().getUserName() : chat.getP1().getUserName()));
            }
        }
        privateChatHandler(Scn.readNumber()-1,response.getPrivateChats());
    }

    void privateChatHandler(int index, ArrayList<PrivateChat> chats) {
        printList("Send text", "Send file", "exit");
        switch (Scn.readNumber()) {
            case 1 -> {
                System.out.println("Enter your message: ");

            }
            case 2 -> {
                System.out.println("Enter your file address: ");
            }
            case 3 -> {
                System.exit(0);
            }
        }
    }

    public void showChatsMenu() {
        //////
    }
    //endregion

    //region Friends Menu
    void doFriendsMenu() {
        showMainMenu();
        mainMenuHandler();
    }

    void friendsMenuHandler() {
        switch (Scn.readNumber()) {
            case 1 -> {
                doLoginMenu();
            }
            case 2 -> {
                doSignUpMenu();
            }
            case 3 -> {
                System.out.println("It's not completed");
                doStartMenu();
            }
            case 4 -> {
                this.person = null;
                doStartMenu();
            }
        }
    }

    public void showFriendsMenu() {
        printList("chats", "friend", "settings", "log Out");
    }
    //endregion

    //region Setting Menu
    void doSettingMenu() {
        showMainMenu();
        mainMenuHandler();
    }

    void settingMenuHandler() {
        switch (Scn.readNumber()) {
            case 1 -> {
                doLoginMenu();
            }
            case 2 -> {
                doSignUpMenu();
            }
            case 3 -> {
                System.out.println("It's not completed");
                doStartMenu();
            }
            case 4 -> {
                this.person = null;
                doStartMenu();
            }
        }
    }

    public void showSettingMenu() {
        printList("chats", "friend", "settings", "log Out");
    }
    //endregion

    ///////////////////
    //region
    //endregion
    //region
    //endregion
    //region
    //endregion
    //region
    //endregion
    //region
    //endregion
    //region
    //endregion
    //region
    //endregion
    //region
    //endregion
    //region
}
