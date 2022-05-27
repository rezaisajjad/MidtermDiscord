package Model.Request;
import java.io.Serializable;

public class Request implements Serializable {
    private RequestType requestType;
    private Serializable object;

    public RequestType getRequestType() {
        return requestType;
    }

    public Serializable getObject() {
        return object;
    }

    public Request(RequestType requestType, Serializable object) {
        this.requestType = requestType;
        this.object = object;
    }
}
