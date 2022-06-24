package Repository;

import Model.Person;
import Model.PrivateChat;
import Model.PrivateChatMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ChatsRepository {
    private HashSet<PrivateChat> privateChats = new HashSet<>();
    public PrivateChat findPrivateChat(PrivateChat pc) {
        for (var _pc : privateChats) {
            if (_pc.equals(pc)) {
                return _pc;
            }
        }
        privateChats.add(pc);
        return pc;
    }
    public void addMessageToPrivateChat(PrivateChat pc, PrivateChatMessage pcm)
    {
        findPrivateChat(pc).addMessage(pcm);
    }
}
