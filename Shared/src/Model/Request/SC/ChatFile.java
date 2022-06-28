package Model.Request.SC;

import java.io.Serializable;

public class ChatFile implements Serializable {
    byte[] bytes;
    String extension;

    public ChatFile(byte[] bytes, String extension) {
        this.bytes = bytes;
        this.extension = extension;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public String getExtension() {
        return extension;
    }
}
