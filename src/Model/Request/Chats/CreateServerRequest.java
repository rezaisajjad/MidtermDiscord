package Model.Request.Chats;

import Model.ServerChat;

import java.io.Serializable;

public class CreateServerRequest implements Serializable {
    private ServerChat server;
    /**
     * این چت باید در سرور ذخیره شود
     * سپس درصورت موفقیت برگردانده شده و در غیر این صورت پارامتر ها نال شود و برگدانده شود
     */
}
