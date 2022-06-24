package Model.Request.Chats;

import Model.PrivateChat;

public class UpdatePrivateChat {
    PrivateChat privateChat;

    public UpdatePrivateChat(PrivateChat privateChat) {
        this.privateChat = privateChat;
    }

    public PrivateChat getPrivateChat() {
        return privateChat;
    }
}
