package com.in28minutes.demo.dao;

import com.in28minutes.demo.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserDao {
    private static List<User> users = new ArrayList<>();
    private static int userId = 1;

    static {
        users.add(new User(userId++, "Reham", new Date()));
        users.add(new User(userId++, "Reham", new Date()));
        users.add(new User(userId++, "Reham", new Date()));
        users.add(new User(userId++, "Reham", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        user.setId(userId++);
        users.add(user);
        return user;
    }

    public User findById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User deleteById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                users.remove(user);
                return user;
            }
        }
        return null;
    }
}


