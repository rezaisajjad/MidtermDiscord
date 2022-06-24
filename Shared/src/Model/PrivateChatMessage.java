package Model;

import java.io.Serializable;

public class PrivateChatMessage implements Serializable {
    String text;
    String fileId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
