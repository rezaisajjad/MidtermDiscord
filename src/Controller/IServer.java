package Controller;

import Model.Request.Request;
import Model.Request.RequestType;
import Model.Response.Response;

/**
 * send and receives data to server
 */
public interface IServer {

    public Response sendRequest(Request request);

}
