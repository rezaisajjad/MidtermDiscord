package View;

import ClientController.InputValidator;
import ClientController.Server;
import Model.Person;
import Model.Request.Account.CheckUserNameAvailabilityRequest;
import Model.Request.Account.LoginRequest;

import javax.xml.validation.Validator;
import java.util.Scanner;

public class Scn {
    private static final Scanner scn = new Scanner(System.in);
    private static final Scn _scn = new Scn();
    private Server server = Server.getServer();

    public static Scn getScanner() {
        return _scn;
    }

    public int readNumber() {
        return scn.nextInt();
    }

    public int readIndex() {
        return readNumber() - 1;
    }

    public String readLine() {
        String txt = scn.nextLine().trim();
        return txt.equals("") ? scn.nextLine() : txt;
    }

    public String readText() {
        return scn.next();
    }

    public Person readPerson() {
        Person p = new Person();
        {
            String userName = "";
            CheckUserNameAvailabilityRequest result = null;
            boolean res;
            do {
                System.out.println("User Name: ");
                userName = scn.next().toLowerCase();
                while (!InputValidator.validateUserName(userName)) {
                    System.out.println("Invalid User Name !!!");
                    System.out.println("User Name: ");
                    userName = scn.next().toLowerCase();
                }
                res = server.CheckUserNameAvailability(userName);
                if (!res) {
                    System.out.println("User name isn't available !!!");
                }
            } while (!res || !InputValidator.validateUserName(userName));
            p.setUserName(userName);
        }
        {
            String password = "";
            do {
                if (!password.equals(""))
                    System.out.println("Invalid password !!!");
                System.out.println("Password: ");
                password = scn.next();
            }
            while (!InputValidator.validatePassword(password));
            p.setPassWord(password);
        }
        {
            String email = "";
            do {
                if (!email.equals(""))
                    System.out.println("Invalid password !!!");
                System.out.println("Email: ");
                email = scn.next();
            }
            while (!InputValidator.validateEmail(email));
            p.setEmail(email);
        }
        {
            System.out.println("do you want to enter phone number??\n\ttrue (1)\n\tfalse (2)");
            if (readNumber() == 1) {
                String phoneNumber = "";
                do {
                    if (!phoneNumber.equals(""))
                        System.out.println("Invalid Phone number !!!");
                    System.out.println("Phone number: ");
                    phoneNumber = scn.next();
                }
                while (!InputValidator.validatePhoneNumber(phoneNumber));
                p.setPhoneNumber(phoneNumber);
            }
        }
        return p;
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
