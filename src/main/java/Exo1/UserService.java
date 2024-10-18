package Exo1;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    private Map<String, User> users = new HashMap<>();

    public UserService() {
        User defaultUser = new User("default@example.com", "password123", "testuser");
        users.put("defaultuser", defaultUser);
    }

    public boolean createAccount(String username, String email, String password) {
        if (users.containsKey(username) || emailExists(email)) {
            return false;
        }
        User newUser = new User(username, email, password);
        users.put(username, newUser);
        return true;
    }

    public boolean login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    private boolean emailExists(String email) {
        return users.values().stream().anyMatch(u -> u.getEmail().equals(email));
    }
}

