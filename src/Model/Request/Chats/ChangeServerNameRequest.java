package Model.Request.Chats;

import Model.ServerChat;

import java.io.Serializable;

public class ChangeServerNameRequest implements Serializable {
    String newName;
    ServerChat server;
    /**
     * یک سرور و یک نام فرستاده میشود که باید نام این سرور تغییر کند
     * سپس سرور تغییر یافته و این کلاس برگردانده میشود
     * که در این کلاس سرور تغییر نام داده است
     */
}
