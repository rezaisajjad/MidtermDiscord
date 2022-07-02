package View;

import ClientController.InputValidator;
import ClientController.Server;
import Model.Request.Account.LoginRequest;
import Model.Request.FormatErrorException;
import Model.Request.Person;
import Model.Request.Role;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Scn {
    private static final Scanner scn = new Scanner(System.in);
    private static final Scn _scn = new Scn();
    private Server server = Server.getServer();

    public static Scn getScanner() {
        return _scn;
    }

    public void printTimer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int counter = 0;
            @Override
            public void run() {
                counter ++;
                System.out.write('\r');
                System.out.print("recording : ");
                System.out.print((counter/60)+":"+(counter%60));
            }
        }, 10,1000);
        scn.nextLine();
        scn.nextLine();
        timer.cancel();
    }

    public boolean readYesOrNo(String text) {
        System.out.println(text);
        System.out.println("1)Yes   2)No");
        return readNumber()==1;
    }

    public Role readRole() {
        String name;
        System.out.println("Name: ");
        name = readText().toLowerCase().trim();
        boolean createChannel = readYesOrNo("createChannel");
        boolean removeChannel = readYesOrNo("removeChannel");
        boolean removePerson = readYesOrNo("removePerson");
        boolean restrictAccessChannel = readYesOrNo("restrictAccessChannel");
        boolean restrictPersonAccess = readYesOrNo("restrictPersonAccess");
        boolean changeServerName = readYesOrNo("changeServerName");
        boolean observeChatHistory = readYesOrNo("observeChatHistory");
        boolean pinTextMessage = readYesOrNo("pinTextMessage");
        return new Role(name, createChannel, removeChannel, removePerson, restrictAccessChannel, restrictPersonAccess,
                changeServerName, observeChatHistory, pinTextMessage);
    }

    public int readNumber() {
        return scn.nextInt();
    }

    public int readIndex() {
        return readNumber() - 1;
    }

    public String readLine() {
        String txt = scn.nextLine().trim();
        return txt.equals("") ? readLine() : txt;
    }

    public String readText() {
        return scn.next();
    }

    private String temp = "";
    private int temp2 = 0;

    public Person readPerson() {
        Person p = new Person();
        temp = "";
        p.setUserName(_getUserName());
        temp = "";
        p.setPassWord(_getPassWord());
        temp = "";
        p.setEmail(_getEmail());
        temp = "";
        p.setPhoneNumber(_getPhoneNumber());
        return p;
    }
public String readPassword()
{
    temp = "";
    return  _getPassWord();
}
    private String _getPhoneNumber() {
        try {
            return getPhoneNumber();
        } catch (FormatErrorException e) {
            return _getPhoneNumber();
        }
    }

    private String getPhoneNumber() throws FormatErrorException {
        if (temp.trim().equals("")) {
            System.out.println("do you want to enter phone number??\n\ttrue (1)\n\tfalse (2)");
        }
        if (temp2 == 1 || (temp2 = readNumber()) == 1) {
            if (!temp.equals(""))
                System.out.println("Invalid Phone number !!!");
            System.out.println("Phone number: ");
            temp = scn.next();
            if (!InputValidator.validatePhoneNumber(temp))
                throw new FormatErrorException("Phone Number");

        }
        return temp;
    }

    private String _getEmail() {
        try {
            return getEmail();
        } catch (FormatErrorException e) {
            return _getEmail();
        }
    }

    private String getEmail() throws FormatErrorException {
        if (!temp.trim().equals("")) {
            System.out.println("Invalid password !!!");
        }
        System.out.println("Email: ");
        temp = scn.next();
        if (!InputValidator.validateEmail(temp))
            throw new FormatErrorException("Email");

        return temp;
    }

    private String _getPassWord() {
        try {
            return getPassWord();
        } catch (FormatErrorException e) {
            return _getPassWord();
        }
    }

    private String getPassWord() throws FormatErrorException {
        if (!temp.trim().equals("")) {
            System.out.println("Invalid password !!!");
        }
        System.out.println("Password: ");
        temp = scn.next();
        if (!InputValidator.validatePassword(temp))
            throw new FormatErrorException("Pass word");
        return temp;
    }

    private String _getUserName() {
        try {
            return getUserName();
        } catch (FormatErrorException e) {
            return _getUserName();
        }
    }

    private String getUserName() throws FormatErrorException {
        String userName = "";
        boolean res;
        System.out.println("User Name: ");
        userName = scn.next().toLowerCase();
        while (!InputValidator.validateUserName(userName)) {
            System.out.println("Invalid User Name !!!");
            System.out.println("User Name: ");
            userName = scn.next().toLowerCase();
        }
        res = server.checkUserNameAvailability(userName);
        if (!res) {
            System.out.println("User name isn't available !!!");
        }
        if (!res || !InputValidator.validateUserName(userName))
            throw new FormatErrorException("User name");
        return userName;
    }

    public LoginRequest readLoginRequest() {
        System.out.println("User Name: ");
        String userName = scn.next().toLowerCase();
        System.out.println("Password: ");
        String passWord = scn.next();
        if (InputValidator.validateUserName(userName) && InputValidator.validatePassword(passWord)) {
            return new LoginRequest(userName, passWord);
        }
        System.out.println("Login failed!!! ");
        return readLoginRequest();
    }
}
