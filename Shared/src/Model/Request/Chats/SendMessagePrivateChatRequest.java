package Model.Request.Chats;

import Model.Request.PrivateChat;
import Model.Request.PrivateChatMessage;
import Model.Request.IRequest;

import java.io.Serializable;

public class SendMessagePrivateChatRequest implements Serializable, IRequest {
    PrivateChat privateChat;
    PrivateChatMessage message;

    public SendMessagePrivateChatRequest(PrivateChat privateChat, PrivateChatMessage message) {
        this.privateChat = privateChat;
        this.message = message;
    }

    public PrivateChat getPrivateChat() {
        return privateChat;
    }

    public void setPrivateChat(PrivateChat privateChat) {
        this.privateChat = privateChat;
    }

    public PrivateChatMessage getMessage() {
        return message;
    }

    public void setMessage(PrivateChatMessage message) {
        this.message = message;
    }
}
