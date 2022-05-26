package Model.Request.Friend;

import Model.Person;

import java.io.Serializable;

public class BlockPersonRequest implements Serializable {
    /**
     * شخصی که بلاک میکند
     */
    Person blocker;
    /**
     * شخصی که بلاک میشود
     */
    Person blocked;

    /**
     * این کلاس در صورت موفقیت مجدد برگردانده شده و در غیر این صورت
     * پارامتر ها نال شده و برگردانده میشود
     */
}
