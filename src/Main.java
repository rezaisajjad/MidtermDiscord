import Controller.IServer;
import Controller.Server;
import Model.Person;
import Model.Request.Request;
import Model.Request.RequestType;
import Model.Request.SignUpRequest;
import Model.Status;

import java.io.Serializable;

public class Main {
    public static void main(String[] args) {
        IServer server = new Server();
        server.sendRequest(
                new Request(RequestType.SignUp,
                        new SignUpRequest(
                                new Person("Rezai_sajjad","SrezaiS81","rezai_sajjad@yahoo.com",
                                        "09190345318", Status.Idle,null) {
        })));
    }
}