

package assignment7.view;
import java.util.HashMap;

public class Users {
    public static final HashMap<String, String> users = new HashMap();

    public Users() {
    }

    static {
        users.put("User1", "testcase123");
        users.put("User2", "testcase123");
        users.put("User3", "testcase123");
    }
}
