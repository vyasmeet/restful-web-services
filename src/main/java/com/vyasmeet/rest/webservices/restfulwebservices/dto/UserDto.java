package com.vyasmeet.rest.webservices.restfulwebservices.dto;

import com.vyasmeet.rest.webservices.restfulwebservices.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDto {
    private static List<User> users = new ArrayList<>();
    private static int userCount = 7;

    static {
        users.add(new User(1,"Jack", new Date()));
        users.add(new User(2,"Tom", new Date()));
        users.add(new User(3,"Rene", new Date()));
        users.add(new User(4,"Adam", new Date()));
        users.add(new User(5,"Eve", new Date()));
        users.add(new User(6,"Jennie", new Date()));
        users.add(new User(7,"Lucifer", new Date()));
    }

    public List<User> getAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    public User findWithID(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public User deleteByID(int id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return user;
            }
        }
        return null;
    }

}
