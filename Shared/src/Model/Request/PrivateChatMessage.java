package Model.Request;

import java.io.Serializable;

public class PrivateChatMessage implements Serializable {
    String senderUserName;
    String text;
    String fileId;
    public PrivateChatMessage(String senderUserName, String text, String fileId) {
        this.senderUserName = senderUserName;
        this.text = text;
        this.fileId = fileId;
    }
    public PrivateChatMessage(String senderUserName, String text) {
        this(senderUserName,text,"");
    }

    public String getText() {
        return text;
    }

    public String getFileId() {
        return fileId;
    }
    public String getSenderUserName() {
        return senderUserName;
    }
}
