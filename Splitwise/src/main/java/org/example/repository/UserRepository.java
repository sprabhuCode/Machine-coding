package org.example.repository;

import org.example.dto.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserRepository {
    private final Map<String, User> userMap = new HashMap<>();

    public void addUser(User user) {
        userMap.put(user.getUserId(), user);
    }

    public User getUserById(String userId) {
        if (userMap.containsKey(userId))
            return userMap.get(userId);
        else
            System.out.println(" User with userId=" + userId + " not found");
        return null;
    }

    public boolean deleteUser(String userId) {
        userMap.remove(userId);
        return true;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }
}
