

package assignment7.view;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;

public class Users {
    public static final HashMap<String, String> users = new HashMap();

    public static final ArrayList<String> lists = new ArrayList<String>();

    public static ObservableList<String> choiceusers = FXCollections.observableArrayList("- Select User -");

    public Users() {
    }

    static {
        users.put("User1", "testcase123");
        users.put("User2", "testcase123");
        users.put("User3", "testcase123");
    }
}
