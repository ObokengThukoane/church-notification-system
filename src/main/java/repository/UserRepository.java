package repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.mycompany.webfish.User;
public class UserRepository {

    private static final Map<String, User> users =
            new ConcurrentHashMap<>();

    private UserRepository() {
    }

    public static boolean userExists(String username) {
        return users.containsKey(username.toLowerCase());
    }

    public static boolean addUser(User user) {

        String key = user.getUsername().toLowerCase();

        if (users.containsKey(key)) {
            return false;
        }

        users.put(key, user);
        return true;
    }

    public static User findUser(String username) {
        return users.get(username.toLowerCase());
    }
    
    
}