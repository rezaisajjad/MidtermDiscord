package code.Chats;



import code.IRequest;
import code.SC.ChatFile;

import java.io.Serializable;

public class DownloadFileRequest implements IRequest, Serializable {
    ChatFile file;
    Integer id;

    public DownloadFileRequest(Integer id) {
        this.id = id;
    }

    public ChatFile getFile() {
        return file;
    }

    public Integer getId() {
        return id;
    }

    public void setFile(ChatFile file) {
        this.file = file;
    }
}
