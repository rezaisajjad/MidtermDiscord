package Model.Request;
import Model.*;

import java.io.*;
import java.util.*;

abstract public class Request implements Serializable {
    abstract public boolean serverAct(HashMap<String,Person> persons);
    abstract public HashSet<String> selfAct(HashMap<String,Person> persons);
}
