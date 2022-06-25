package View;

import ClientController.Server;
import Model.Person;
import Model.Request.PrivateChat;
import Model.Request.PrivateChatMessage;
import Model.Request.Account.LoginRequest;

import java.util.ArrayList;

public class UI {
    //region Base
    private Person person = null;
    private Server server = Server.getServer();
    Scn scn = Scn.getScanner();

    public void start() {
        doStartMenu();
    }

    private void printList(String... args) {
        int i = 0;
        for (String arg : args) {
            System.out.println((++i) + ") " + arg);
        }
    }
    //endregion

    //region Start Menu
    private void doStartMenu() {
        showStartMenu();
        startMenuHandler();
    }

    private void startMenuHandler() {
        switch (scn.readNumber()) {
            case 1 -> {
                doLoginMenu();
            }
            case 2 -> {
                doSignUpMenu();
            }
            case 3 -> {
                System.exit(0);
            }
        }
    }

    private void showStartMenu() {
        printList("Login", "SignUp", "Exit");
    }
    //endregion

    //region SignUp Menu
    private void doSignUpMenu() {
        showSignUpMenu();
        signUpMenuHandler();
    }

    private void signUpMenuHandler() {
        Person person = scn.readPerson();
        person = server.signUpPerson(person);
        if (person != null) {
            System.out.println("Successfully added");
            this.person = person;
            doMainMenu();
        } else {
            System.out.println("Error");
            doStartMenu();
        }
    }

    private void showSignUpMenu() {
        //we can add menu option if is needed;
    }

    //endregion

    //region Login Menu
    private void doLoginMenu() {
        showLoginMenu();
        loginMenuHandler();
    }

    private void loginMenuHandler() {
        LoginRequest request = scn.readLoginRequest();
        Person p = server.loginPerson(request.getUserName(), request.getPassWord());
        if (p != null) {
            System.out.println("Successfully logged in");
            this.person = p;
            doMainMenu();
        } else {
            System.out.println("Error");
            doStartMenu();
        }
    }

    private void showLoginMenu() {
        //we can add menu option if is needed;
    }

    //endregion

    //region Main menu
    private void doMainMenu() {
        showMainMenu();
        mainMenuHandler();
    }

    private void mainMenuHandler() {
        switch (scn.readNumber()) {
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

    private void showMainMenu() {
        printList("chats", "friend", "settings", "log Out");
    }
    //endregion

    //region Chats Menu
    private void doChatsMenu() {
        showChatsMenu();
        chatsMenuHandler();
    }

    private Person getPrivateChatPerson(PrivateChat pc) {
        return pc.getP1().equals(person) ? pc.getP2() : pc.getP1();
    }

    void chatsMenuHandler() {
        int temp = 0;
        ArrayList<PrivateChat> chats = server.getPersonPrivateChats(person.getUserName());
        if (chats != null) {
            for (var chat : chats) {
                System.out.println((++temp) + ") " + chat.getMessages());
            }
        }
        System.out.println("0) Back");
        if ((temp = scn.readIndex()) == -1) {
            doMainMenu();
            return;
        }
        privateChatHandler(chats.get(temp));
        doChatsMenu();
    }

    void privateChatHandler(PrivateChat chat) {
        ArrayList<PrivateChatMessage> messages = chat.getMessages();
        for (var item : messages) {
            System.out.println(item.getSenderUserName() + ": " + item.getText());
        }
        boolean isBreak = false;
        do {
            printList("Send text", "Send file", "Back");
            switch (scn.readNumber()) {
                case 1 -> {
                    System.out.println("Enter your message: ");
                    PrivateChatMessage chatMessage = new PrivateChatMessage(person.getUserName(), scn.readLine());
                    server.sendPrivateChatMessage(chat, chatMessage);
                }
                case 2 -> {
                    System.out.println("Sorry isn't complete !!!");
                }
                case 3 -> {
                    doChatsMenu();
                    isBreak = true;
                }
            }
        } while (!isBreak);
    }

    public void showChatsMenu() {
        ///
    }
    //endregion

    //region Friends Menu
    void doFriendsMenu() {
        showFriendsMenu();
        friendsMenuHandler();
    }

    void friendsMenuHandler() {
        switch (scn.readNumber()) {
            case 1 -> {
                var friendList = server.getPersonFriends(person.getUserName());
                int temp = 0;
                for (var item : friendList) {
                    System.out.println((++temp) + ") " + item.getUserName());
                }
                System.out.println("0) Back");
                if ((temp = scn.readIndex()) == -1) {
                    doMainMenu();
                    return;
                }
                PrivateChat privateChat = new PrivateChat(person, friendList.get(temp));
                for (var item : server.getPersonPrivateChats(person.getUserName())) {
                    if (item.equals(privateChat)) {
                        privateChatHandler(item);
                        return;
                    }
                }
                privateChatHandler(privateChat);
            }
            case 2 -> {
                System.out.println("Enter userName: ");
                String receiver = scn.readText();
                server.sendFriendRequest(person.getUserName(), receiver);
                System.out.println("sent");
            }
            case 3 -> {
                var requests = server.getPersonFriendRequests(person.getUserName());
                int temp = 0;
                for (var item : requests) {
                    System.out.println((++temp) + ") " + item.getSenderUserName());
                }
                System.out.println("0) Back");
                if ((temp = scn.readIndex()) == -1) {
                    doMainMenu();
                    return;
                }
                server.acceptFriendRequest(requests.get(temp));
                System.out.println("Accepted");
            }
            case 4 -> {
                System.out.println("Enter userName: ");
                String receiver = scn.readText();
                server.removePersonFriend(person.getUserName(), receiver);
                System.out.println("removed");
            }
            case 5 -> {
                doMainMenu();
                return;
            }
        }
        doChatsMenu();
    }

    public void showFriendsMenu() {
        printList("List of friends", "New", "Requests", "Remove", "Back");
    }
    //endregion

    //region Setting Menu
    void doSettingMenu() {
        showMainMenu();
        mainMenuHandler();
    }

    void settingMenuHandler() {
        switch (scn.readNumber()) {
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
        printList("change status", "change picture", "change PassWord", "change PhoneNumber", "change Email", "Exit");
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
