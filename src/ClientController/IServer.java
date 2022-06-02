package ClientController;

import Model.Request.Request;

/**
 * send and receives data to server
 */
public interface IServer {

    Request sendRequest(Request request);

}
