package Controller;

import Model.Request.Request;
import Model.Request.RequestType;

/**
 * send and receives data to server
 */
public interface IServer {

    public Request sendRequest(Request request);

}
