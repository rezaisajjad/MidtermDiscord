package Model.Request;
import Model.Chat;
import Model.Requestable;

import java.io.Serializable;

abstract public class Request implements Serializable {
    abstract public boolean contactsAct();
    abstract public boolean serverAct();

}
