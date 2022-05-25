package Model.Request;
import java.io.Serializable;

public class Request implements Serializable {
    RequestType requestType;
    Serializable object;

    public Request(RequestType requestType, Serializable object) {
        this.requestType = requestType;
        this.object = object;
    }
}
