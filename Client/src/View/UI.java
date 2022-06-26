package View;

import ClientController.Server;
import Model.Person;
import Model.PublicStatic;
import Model.Request.PrivateChat;
import Model.Request.PrivateChatMessage;
import Model.Request.Account.LoginRequest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class UI {
    //region Base
    private static UI ui=new UI();
    private Person person = null;
    private Server server = Server.getServer();
    Scn scn = Scn.getScanner();

    private UI() {
    }
    public String getPersonUserName()
    {
        if (person!=null)
            return person.getUserName();
        return "";
    }
    public static UI get()
    {
        return ui;
    }

    public void start() {
        doStartMenu();
    }

    private void printList(String... args) {
        int i = 0;
        for (String arg : args) {
            System.out.println((++i) + ") " + arg);
        }
    }

    private boolean isListNullOrEmpty(Collection collection) {
        return (collection == null) || (collection.size() == 0);
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

    private String getPrivateChatPerson(PrivateChat pc) {
        return pc.getP1().equals(person.getUserName()) ? pc.getP2() : pc.getP1();
    }

    void chatsMenuHandler() {
        int temp = 0;
        ArrayList<PrivateChat> chats = server.getPersonPrivateChats(person.getUserName());
        if (isListNullOrEmpty(chats)) {
            System.out.println("There isn't any chat");
            doMainMenu();
            return;
        }
        for (var chat : chats) {
            System.out.println((++temp) + ") " + getPrivateChatPerson(chat));
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
                    String messageText = scn.readLine();
                    PrivateChatMessage chatMessage = new PrivateChatMessage(person.getUserName(), messageText);
                    server.sendPrivateChatMessage(chat, chatMessage);
                }
                case 2 -> {

                    System.out.println("Sorry isn't complete !!!");
                }
                case 3 -> {
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
                if (isListNullOrEmpty(friendList)) {
                    System.out.println("There isn't any friend !!!");
                    doFriendsMenu();
                    return;
                }
                for (var item : friendList) {
                    System.out.println((++temp) + ") " + item+"["+server.getStatus(item)+"]");
                }
                System.out.println("0) Back");
                if ((temp = scn.readIndex()) == -1) {
                    doFriendsMenu();
                    return;
                }
                PrivateChat privateChat = new PrivateChat(person.getUserName(), friendList.get(temp));
                for (var item : server.getPersonPrivateChats(person.getUserName())) {
                    if (item.equals(privateChat)) {
                        privateChatHandler(item);
                        doFriendsMenu();
                        return;
                    }
                }
                privateChatHandler(privateChat);
                doFriendsMenu();
            }
            case 2 -> {
                System.out.println("Enter userName: ");
                String receiver = scn.readText();
                if (server.CheckUserNameAvailability(receiver.toLowerCase(Locale.ROOT))) {
                    System.out.println("Person not found");
                    doFriendsMenu();
                    return;
                }
                server.sendFriendRequest(person.getUserName(), receiver.toLowerCase(Locale.ROOT));
                System.out.println("sent");
                doFriendsMenu();
            }
            case 3 -> {
                var requests = server.getPersonFriendRequests(person.getUserName());
                int temp = 0;
                if (isListNullOrEmpty(requests)) {
                    System.out.println("There isn't any request");
                    doFriendsMenu();
                    return;
                }
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
                doFriendsMenu();
            }
            case 4 -> {
                System.out.println("Enter userName: ");
                String receiver = scn.readText();
                if (server.CheckUserNameAvailability(receiver.toLowerCase(Locale.ROOT))) {
                    System.out.println("Person not found");
                    doFriendsMenu();
                    return;
                }
                server.removePersonFriend(person.getUserName(), receiver);
                System.out.println("removed");
                doFriendsMenu();
            }
            case 5 -> {
                System.out.println("Enter userName: ");
                String blocked = scn.readText();
                if (server.CheckUserNameAvailability(blocked.toLowerCase(Locale.ROOT))) {
                    System.out.println("Person not found");
                    doFriendsMenu();
                    return;
                }
                server.blockPerson(person.getUserName(), blocked);
                System.out.println("Blocked");
                doFriendsMenu();
            }
            case 6 -> {
                System.out.println("Enter userName: ");
                String blocked = scn.readText();
                if (server.CheckUserNameAvailability(blocked.toLowerCase(Locale.ROOT))) {
                    System.out.println("Person not found");
                    doFriendsMenu();
                    return;
                }
                server.unBlockPerson(person.getUserName(), blocked);
                System.out.println("UnBlocked");
                doFriendsMenu();
            }
            case 7 -> {
                var friendList = server.getPersonBlockList(person.getUserName());
                int temp = 0;
                if (isListNullOrEmpty(friendList)) {
                    System.out.println("There isn't any blocked person !!!");
                    doFriendsMenu();
                    return;
                }
                for (var item : friendList) {
                    System.out.println((++temp) + ") " + item);
                }
                doFriendsMenu();
            }
            case 8 -> {
                doMainMenu();
            }
        }
    }

    public void showFriendsMenu() {
        printList("friend list", "New", "Requests", "Remove", "Block", "Unblock", "Block list", "Back");
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
