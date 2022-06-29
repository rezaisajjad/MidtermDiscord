package View;

import ClientController.Server;
import Model.Request.Account.LoginRequest;
import Model.Request.*;
import Model.Request.SC.ChatFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;

public class UI {
    //region Base
    private static UI ui = new UI();
    private Person person = null;
    private Server server = Server.getServer();
    Scn scn = Scn.getScanner();

    private UI() {
    }

    public String getPersonUserName() {
        if (person != null)
            return person.getUserName();
        return "";
    }

    public static UI get() {
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
                doPrivateChatsMenu();
            }
            case 2 -> {
                doServerMenu();
            }
            case 3 -> {
                doFriendsMenu();
            }
            case 4 -> {
                doSettingMenu();
            }
            case 5 -> {
                this.person = null;
                doStartMenu();
            }
        }
    }

    private void showMainMenu() {
        printList("chats", "servers", "friend", "settings", "log Out");
    }
    //endregion

    //region Server menu
    private void doServerMenu() {
        showServerMenu();
        serverMenuHandler();
    }

    private void serverMenuHandler() {
        switch (scn.readNumber()) {
            case 1 -> {
                serversListHandler_();
            }
            case 2 -> {
                System.out.println("Enter channel name: ");
                server.createServer(person.getUserName(), scn.readText());
                System.out.println("created");
                doServerMenu();
            }
            case 3 -> {
                doMainMenu();
            }
        }
    }

    private void showServerMenu() {
        printList("Server list", "new Server", "back");
    }

    private void serversListHandler_() {
        HashMap<Integer, String> list = server.getPersonServerChats(person.getUserName());
        int index = 0;
        for (var item : list.keySet().toArray()) {
            System.out.println((++index) + ") " + list.get(item));
        }

        System.out.println("0) back");
        if ((index = scn.readIndex()) == -1) {
            doServerMenu();
            return;
        }
        serversHandler((Integer) list.keySet().toArray()[index]);
    }

    private void serversHandler(Integer serverUniqueID) {
        var personRoles = server.getPersonRoles(person.getUserName(), serverUniqueID);
        Role allPersonRolls = Role.integrateRolls(personRoles);
        printList("channels", "members", "roles", "change server Name", "remove server", "restrict persons", "back");
        switch (scn.readNumber()) {
            case 1 -> {
                channelsMenuHandler(serverUniqueID, allPersonRolls);
            }
            case 2 -> {
                channelMemberHandler(serverUniqueID, allPersonRolls);
            }
            case 3 -> {
                channelRolesHandler(serverUniqueID, personRoles);
            }
            case 4 -> {
                if (!allPersonRolls.isChangeServerName()) {
                    System.out.println("access blocked");
                    serversHandler(serverUniqueID);
                    return;
                }
                System.out.println("Enter new name:");
                server.changeServerName(scn.readText(), serverUniqueID);
                System.out.println("changed");
                serversHandler(serverUniqueID);
            }
            case 5 -> {
                boolean isAdmin = false;
                for (var role : personRoles) {
                    if (role.getName().trim().equals("owner")) {
                        isAdmin = true;
                        break;
                    }
                }
                if (!isAdmin) {
                    System.out.println("access blocked");
                    serversHandler(serverUniqueID);
                    return;
                }
                System.out.println("Are you sure?\nPlease types yes or no");
                if (scn.readText().trim().equals("yes")) {
                    server.removeServer(serverUniqueID);
                    System.out.println("removed");
                    serversListHandler_();
                }else
                {
                    serversHandler(serverUniqueID);
                }

            }
            case 6 -> {
                restrictMenuHandler(serverUniqueID, allPersonRolls);
            }
            case 7 -> {
                serversListHandler_();
            }
        }
    }

    private void restrictMenuHandler(Integer serverUniqueID, Role allPersonRolls) {
        printList("list of persons", "see person allowed channels",
                "add person", "remove person", "add channel to person", "back");
        switch (scn.readNumber()) {
            case 1 -> {
                var list = server.getRestrictPersonsFromAllServer(serverUniqueID);
                for (var item : list) {
                    System.out.println(item);
                }
                restrictMenuHandler(serverUniqueID,allPersonRolls);
            }
            case 2 -> {
                String uName = "";
                System.out.println("Enter person userName:");
                do {
                    if (!uName.equals("")) {
                        System.out.println("user not found !!!");
                        System.out.println("Enter person userName:");
                    }
                    uName = scn.readText().trim();
                } while (!server.isPersonExistInServer(uName, serverUniqueID));

                var list = server.getPersonFreemon(uName, serverUniqueID);
                if (list != null)
                    for (var item : list) {
                        System.out.println(item);
                    }
                restrictMenuHandler(serverUniqueID,allPersonRolls);
            }
            case 3 -> {
                if (!allPersonRolls.isRestrictPersonAccess()) {
                    System.out.println("access blocked");
                    restrictMenuHandler(serverUniqueID,allPersonRolls);
                    return;
                }
                String uName = "";
                System.out.println("Enter person userName:");
                do {
                    if (!uName.equals("")) {
                        System.out.println("user not found !!!");
                        System.out.println("Enter person userName:");
                    }
                    uName = scn.readText().trim();
                } while (!server.isPersonExistInServer(uName, serverUniqueID));
                server.restrictPersonFromAllServer(uName, serverUniqueID);
                System.out.println("added");
                restrictMenuHandler(serverUniqueID,allPersonRolls);
            }
            case 4 -> {
                if (!allPersonRolls.isRestrictPersonAccess()) {
                    System.out.println("access blocked");
                    restrictMenuHandler(serverUniqueID,allPersonRolls);
                    return;
                }
                String uName = "";
                System.out.println("Enter person userName:");
                do {
                    if (!uName.equals("")) {
                        System.out.println("user not found !!!");
                        System.out.println("Enter person userName:");
                    }
                    uName = scn.readText().trim();
                } while (!server.isPersonExistInServer(uName, serverUniqueID));
                server.removeRestrictPersonFromAllServer(uName, serverUniqueID);
                System.out.println("added");
                restrictMenuHandler(serverUniqueID,allPersonRolls);
            }
            case 5 -> {
                if (!allPersonRolls.isRestrictPersonAccess()) {
                    System.out.println("access blocked");
                    restrictMenuHandler(serverUniqueID,allPersonRolls);
                    return;
                }
                String uName = "";
                System.out.println("Enter person userName:");
                do {
                    if (!uName.equals("")) {
                        System.out.println("user not found !!!");
                        System.out.println("Enter person userName:");
                    }
                    uName = scn.readText().trim();
                } while (!server.isPersonExistInServer(uName, serverUniqueID));
                String cName = "";
                System.out.println("Enter channel Name:");
                do {
                    if (!cName.equals("")) {
                        System.out.println("channel not found !!!");
                        System.out.println("Enter channel Name:");
                    }
                    cName = scn.readText().trim();
                } while (server.checkChannelNameAvailability(serverUniqueID, cName));
                server.unRestrictAllRestrictPersonFromAChannel(uName, serverUniqueID, cName);
                System.out.println("added");
                restrictMenuHandler(serverUniqueID,allPersonRolls);
            }
            case 6->{
                serversHandler(serverUniqueID);
            }
        }
    }

    private void channelRolesHandler(Integer serverUniqueID, ArrayList<Role> personRoles) {
        printList("list of roles", "add role", "back");
        switch (scn.readNumber()) {
            case 1 -> {
                listOfRolesHandler(serverUniqueID, personRoles);
            }
            case 2 -> {
                boolean isAdmin = false;
                for (var role : personRoles) {
                    if (role.getName().trim().equals("owner")) {
                        isAdmin = true;
                        break;
                    }
                }
                if (!isAdmin) {
                    System.out.println("access blocked");
                    serversHandler(serverUniqueID);
                    return;
                }
                server.addRoleToServer(scn.readRole(), serverUniqueID);
                System.out.println("added");
                channelRolesHandler(serverUniqueID, personRoles);
            }
            case 3 -> {
                serversHandler(serverUniqueID);
            }
        }
    }

    private void listOfRolesHandler(Integer serverUniqueID, ArrayList<Role> personRoles) {
        var roles = server.getServerRoles(serverUniqueID).toArray();
        int temp = 0;
        for (var item : roles) {
            System.out.println((++temp) + ") " + item);
        }
        System.out.println("0) back");
        if ((temp = scn.readIndex()) == -1) {
            serversHandler(serverUniqueID);
            return;
        }
        roleOptionsHandler(serverUniqueID, personRoles, roles, temp);
    }

    private void roleOptionsHandler(Integer serverUniqueID, ArrayList<Role> personRoles, Object[] roles, int temp) {
        printList("persons", "add person", "remove Person", "back");
        switch (scn.readNumber()) {
            case 1 -> {
                showMemberPersons(serverUniqueID, roles[temp]);
                roleOptionsHandler(serverUniqueID, personRoles,roles,temp);
            }
            case 2 -> {
                String uName = "";
                System.out.println("Enter person userName:");
                do {
                    if (!uName.equals("")) {
                        System.out.println("user not found!!!");
                        System.out.println("Enter person userName:");
                    }
                    uName = scn.readText().trim();
                } while (!server.isPersonExistInServer(uName, serverUniqueID));
                server.addRoleToPerson(uName, (String) roles[temp], serverUniqueID);
                System.out.println("added");
                roleOptionsHandler(serverUniqueID, personRoles,roles,temp);
            }
            case 3 -> {
                String uName = "";
                System.out.println("Enter person userName:");
                do {
                    if (!uName.equals("")) {
                        System.out.println("user not found!!!");
                        System.out.println("Enter person userName:");
                    }
                    uName = scn.readText().trim();
                } while (!server.isPersonExistInServer(uName, serverUniqueID));
                server.removeRoleFromPerson(uName, (String) roles[temp], serverUniqueID);
                System.out.println("removed");
                roleOptionsHandler(serverUniqueID, personRoles,roles,temp);
            }
            case 4 -> {
                listOfRolesHandler(serverUniqueID, personRoles);
            }
        }
    }

    private void showMemberPersons(Integer serverUniqueID, Object roles) {
        var members = server.getServerRoleMembers((String) roles, serverUniqueID);
        for (var item : members.keySet()) {
            System.out.println(item + " [" + members.get(item) + "]");
        }
    }

    private void channelMemberHandler(Integer serverUniqueID, Role allPersonRolls) {
        printList("list of members", "add member", "remove member", "back");
        switch (scn.readNumber()) {
            case 1 -> {
                var members = server.getServerMembers(serverUniqueID);
                for (var item : members.keySet()) {
                    System.out.println(item + " [" + members.get(item) + "]");
                }
                channelMemberHandler(serverUniqueID, allPersonRolls);
            }
            case 2 -> {
                String uName = "";
                System.out.println("Enter person userName:");
                do {
                    if (!uName.equals("")) {
                        System.out.println("user not found or already exist!!!");
                        System.out.println("Enter person userName:");
                    }
                    uName = scn.readText().trim();
                } while (server.checkUserNameAvailability(uName) || server.isPersonExistInServer(uName, serverUniqueID));
                server.addPersonToServer(uName, serverUniqueID);
                System.out.println("added");
                channelMemberHandler(serverUniqueID, allPersonRolls);
            }
            case 3 -> {
                if (!allPersonRolls.isRemovePerson()) {
                    System.out.println("access blocked");
                    channelMemberHandler(serverUniqueID, allPersonRolls);
                    return;
                }
                String uName = "";
                System.out.println("Enter person userName");
                do {
                    if (!uName.equals("")) {
                        System.out.println("user not found!!!");
                        System.out.println("Enter person userName:");
                    }
                    uName = scn.readText().trim();
                } while (!server.isPersonExistInServer(uName, serverUniqueID));
                server.removePersonFromServer(uName, serverUniqueID);
                System.out.println("removed");
                channelMemberHandler(serverUniqueID, allPersonRolls);
            }
            case 4 -> {
                serversHandler(serverUniqueID);
            }
        }
    }

    private void channelsMenuHandler(Integer serverUniqueID, Role allPersonRolls) {
        printList("list of channels", "add channel", "back");
        switch (scn.readNumber()) {
            case 1 -> {
                _ChannelsHandler(serverUniqueID, allPersonRolls);
            }
            case 2 -> {
                _ChannelsAddChannelHandler(serverUniqueID, allPersonRolls);
            }
            case 3 -> {
                serversHandler(serverUniqueID);
            }
        }
    }

    private void _ChannelsAddChannelHandler(Integer serverUniqueID, Role allPersonRolls) {
        if (!allPersonRolls.isCreateChannel()) {
            System.out.println("access blocked");
            channelsMenuHandler(serverUniqueID, allPersonRolls);
            return;
        }
        System.out.println("Enter Channel Name");
        String name = scn.readLine().trim();
        while (!server.checkChannelNameAvailability(serverUniqueID, name)) {
            System.out.println("name isn't available \n enter Again:");
            name = scn.readLine().trim();
        }
        int temp = 0;
        System.out.println("Enter Channel Type");
        for (var item : ChannelType.values()) {
            System.out.println((++temp) + ") " + item);
        }
        temp = scn.readIndex();
        server.createServerChannel(name, ChannelType.values()[temp], serverUniqueID);
        System.out.println("Created");
        channelsMenuHandler(serverUniqueID, allPersonRolls);
    }

    private void _ChannelsHandler(Integer serverUniqueID, Role allPersonRolls) {
        var names = server.getServerChannels(serverUniqueID, person.getUserName());
        int temp = 0;
        String channelName;
        for (var item : names.toArray()) {
            System.out.println((++temp) + ") " + item);
        }
        System.out.println("0) back");
        if ((temp = scn.readIndex()) == -1) {
            channelsMenuHandler(serverUniqueID, allPersonRolls);
            return;
        }
        channelName = (String) names.toArray()[temp];
        _InChannelHandler(serverUniqueID, allPersonRolls, channelName);
    }

    private void _InChannelHandler(Integer serverUniqueID, Role allPersonRolls, String channelName) {
        int temp;
        printList("messages", "send message", "send file", "pin messages", "restricted persons", "restrict person", "unRestrict person", "back");
        switch (scn.readNumber()) {
            case 1 -> {
                showChannelMessagesHandler(serverUniqueID, allPersonRolls, channelName);
            }
            case 2 -> {
                System.out.println("Enter your message: ");
                String messageText = scn.readLine();
                server.sendChannelMessage(channelName, serverUniqueID, messageText, person.getUserName());
                System.out.println("sent :)");
                _InChannelHandler(serverUniqueID, allPersonRolls, channelName);
            }
            case 3 -> {
                System.out.println("enter your file address:");
                String path = scn.readLine();
                Path _path = Paths.get(path);
                if (!Files.exists(_path)) {
                    System.out.println("File not found !!!");
                    serversHandler(serverUniqueID);
                    _InChannelHandler(serverUniqueID, allPersonRolls, channelName);
                    return;
                }
                try {
                    int fid = server.uploadFile(Files.readAllBytes(_path), path.substring(path.lastIndexOf('.') + 1));
                    server.sendChannelMessage(channelName, serverUniqueID, "File(you can download it with its id)   id:" + fid, person.getUserName());
                } catch (IOException e) {
                    System.out.println("error");
                }
                System.out.println("Saved");
                _InChannelHandler(serverUniqueID, allPersonRolls, channelName);
            }
            case 4 -> {
                for (var item : server.getPinMessages(channelName, serverUniqueID)) {
                    System.out.println("pined:\t" + item);
                }
                _InChannelHandler(serverUniqueID, allPersonRolls, channelName);
            }
            case 5 -> {
                var list = server.getRestrictPersonsFromAChannel(serverUniqueID, channelName);
                for (var item : list) {
                    System.out.println(item);
                }
                _InChannelHandler(serverUniqueID, allPersonRolls, channelName);
            }
            case 6 -> {
                if (!allPersonRolls.isRestrictAccessChannel()) {
                    System.out.println("access blocked");
                    serversHandler(serverUniqueID);
                    _InChannelHandler(serverUniqueID, allPersonRolls, channelName);
                    return;
                }
                String uName = "";
                System.out.println("Enter person userName:");
                do {
                    if (!uName.equals("")) {
                        System.out.println("user not found !!!");
                        System.out.println("Enter person userName:");
                    }
                    uName = scn.readText().trim();
                } while (!server.isPersonExistInServer(uName, serverUniqueID));
                server.restrictPersonFromAChannel(uName, serverUniqueID, channelName);
                System.out.println("Restricted");
                _InChannelHandler(serverUniqueID, allPersonRolls, channelName);
            }
            case 7 -> {
                if (!allPersonRolls.isRestrictAccessChannel()) {
                    System.out.println("access blocked");
                    serversHandler(serverUniqueID);
                    _InChannelHandler(serverUniqueID, allPersonRolls, channelName);
                    return;
                }
                String uName = "";
                System.out.println("Enter person userName:");
                do {
                    if (!uName.equals("")) {
                        System.out.println("user not found !!!");
                        System.out.println("Enter person userName:");
                    }
                    uName = scn.readText().trim();
                } while (!server.isPersonExistInServer(uName, serverUniqueID));
                server.removeRestrictPersonFromAChannel(uName, serverUniqueID, channelName);
                System.out.println("unRestricted");
                _InChannelHandler(serverUniqueID, allPersonRolls, channelName);
            }
            case 8 -> {
                _ChannelsHandler(serverUniqueID, allPersonRolls);
            }
        }
    }

    private void showChannelMessagesHandler(Integer serverUniqueID, Role allPersonRolls, String channelName) {
        int temp;
        ArrayList<TextChannelMessage> messages = server.getChannelMessages(channelName, serverUniqueID, person.getUserName());
        temp = 0;
        for (var item : messages) {
            System.out.println((++temp) + ") " + item);
        }
        System.out.println("0) back");
        if ((temp = scn.readIndex()) == -1) {
            serversHandler(serverUniqueID);
            channelsMenuHandler(serverUniqueID, allPersonRolls);
            return;
        }
        TextChannelMessage msg = messages.get(temp);
        _InChannelMessageHandler(serverUniqueID, allPersonRolls, channelName, messages, msg);
    }

    private void _InChannelMessageHandler(Integer serverUniqueID, Role allPersonRolls, String channelName, ArrayList<TextChannelMessage> messages, TextChannelMessage msg) {
        int temp;
        printList("pin message", "like", "dislike", "laugh", "back");
        if ((temp = scn.readNumber()) == 5) {
            showChannelMessagesHandler(serverUniqueID, allPersonRolls, channelName);
            return;
        } else if (temp == 4) {
            server.reactionToChannelMessage(channelName, serverUniqueID, msg, Reaction.laugh);
            System.out.println("laughed");
        } else if (temp == 3) {
            server.reactionToChannelMessage(channelName, serverUniqueID, msg, Reaction.disLike);
            System.out.println("disLiked");
        } else if (temp == 2) {
            server.reactionToChannelMessage(channelName, serverUniqueID, msg, Reaction.like);
            System.out.println("liked");
        } else if (temp == 1) {
            if (!allPersonRolls.isPinTextMessage()) {
                System.out.println("access blocked");
                serversHandler(serverUniqueID);
                _ChannelsHandler(serverUniqueID, allPersonRolls);
                return;
            }
            server.pinMessageToChannel(channelName, serverUniqueID, messages.get(temp - 1));
            System.out.println("pined");
        }
        _InChannelMessageHandler(serverUniqueID, allPersonRolls, channelName, messages, msg);
    }


    //endregion

    //region Chats Menu
    private void doPrivateChatsMenu() {
        showPrivateChatsMenu();
        privateChatsMenuHandler();
    }

    private String getPrivateChatPerson(PrivateChat pc) {
        return pc.getP1().equals(person.getUserName()) ? pc.getP2() : pc.getP1();
    }

    void privateChatsMenuHandler() {
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
        doPrivateChatsMenu();
    }

    void privateChatHandler(PrivateChat chat) {
        ArrayList<PrivateChatMessage> messages = chat.getMessages();
        for (var item : messages) {
            System.out.println(item.getSenderUserName() + ": " + item.getText());
        }
        boolean isBreak = false;
        do {
            printList("Send text", "Send file", "DownloadFile", "Back");
            switch (scn.readNumber()) {
                case 1 -> {
                    System.out.println("Enter your message: ");
                    String messageText = scn.readLine();
                    PrivateChatMessage chatMessage = new PrivateChatMessage(person.getUserName(), messageText);
                    server.sendPrivateChatMessage(chat, chatMessage);
                }
                case 2 -> {
                    System.out.println("enter your file address:");
                    String path = scn.readLine();
                    Path _path = Paths.get(path);
                    if (!Files.exists(_path)) {
                        System.out.println("File not found !!!");
                        privateChatHandler(chat);
                        return;
                    }
                    try {
                        int fid = server.uploadFile(Files.readAllBytes(_path), path.substring(path.lastIndexOf('.') + 1));
                        server.sendPrivateChatMessage(chat, new PrivateChatMessage(person.getUserName(), "File(you can download it with its id)   id:" + fid));
                    } catch (IOException e) {
                        System.out.println("error");
                    }
                    System.out.println("Saved");
                }
                case 3 -> {
                    System.out.println("Enter file code: ");
                    ChatFile file = server.downloadFile(scn.readNumber());
                    System.out.println("downloaded");
                    System.out.println("Where do you want to save?(folder)");
                    try {
                        Files.write(Path.of(scn.readText()+"file."+file.getExtension()),file.getBytes());
                    } catch (IOException e) {
                        System.out.println("error");
                    }
                    System.out.println("saved");
                }
                case 4 -> {
                    isBreak = true;
                }
            }
        } while (!isBreak);
    }

    public void showPrivateChatsMenu() {
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
                    System.out.println((++temp) + ") " + item + "[" + server.getStatus(item) + "]");
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
                if (server.checkUserNameAvailability(receiver.toLowerCase(Locale.ROOT))) {
                    System.out.println("Model.Request.Person not found");
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
                if (server.checkUserNameAvailability(receiver.toLowerCase(Locale.ROOT))) {
                    System.out.println("Model.Request.Person not found");
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
                if (server.checkUserNameAvailability(blocked.toLowerCase(Locale.ROOT))) {
                    System.out.println("Model.Request.Person not found");
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
                if (server.checkUserNameAvailability(blocked.toLowerCase(Locale.ROOT))) {
                    System.out.println("Model.Request.Person not found");
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
        showSettingMenu();
        settingMenuHandler();
    }

    void settingMenuHandler() {
        switch (scn.readNumber()) {
            case 1 -> {
                int temp = 0;
                for (var item : Status.values()) {
                    System.out.println((++temp) + ") " + item.toString());
                }
                temp = scn.readIndex();
                server.setStatus(person.getUserName(), Status.values()[temp]);
                System.out.println("Status changed !!!");
                doSettingMenu();
            }
            case 2 -> {
                System.out.println("enter your image address:");
                String path = scn.readLine();
                Path _path = Paths.get(path);
                if (!Files.exists(_path)) {
                    System.out.println("File not found !!!");
                    doSettingMenu();
                    return;
                }
                try {
                    server.setPersonProfilePicture(person.getUserName(), Files.readAllBytes(_path), path.substring(path.lastIndexOf('.') + 1));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Saved");
                doSettingMenu();
            }
            case 3 -> {
                System.out.println("current password: ");
                String currentPass = scn.readText().trim();
                String newPass = scn.readPassword();
                if (server.changePersonPassword(person.getUserName(), currentPass, newPass))
                    System.out.println("Changed");
                else
                    System.out.println("not changed!!!");
                doSettingMenu();
            }
            case 4 -> {
                doMainMenu();
            }
        }
    }

    public void showSettingMenu() {
        printList("change status", "change picture", "Change password", "Back");
    }
    //endregion
}
