package Model.Request.Chats;

import Model.Request.IRequest;
import Model.Request.SC.ChatFile;

import java.io.File;
import java.io.Serializable;

public class UploadFileRequest implements Serializable, IRequest {
    ChatFile file ;
    Integer fileID;

    public UploadFileRequest(ChatFile file) {
        this.file = file;
    }

    public ChatFile getFile() {
        return file;
    }

    public Integer getFileID() {
        return fileID;
    }

    public void setFileID(Integer fileID) {
        this.fileID = fileID;
    }
}
