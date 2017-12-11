package com.cliff.jms;

import com.cliff.jms.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Quick and dirty service that returns a random user from a list
 * User: Cliff
 */
@Component
public class UserServiceImpl implements UserService {

    private List<User> userList;

    public UserServiceImpl() {
        userList = new ArrayList<>(  );
        User user1 = new User( "John", "Thompson",30 );
        User user2 = new User("Amanda","Byrne",25);
        User user3 = new User("Johnny","Knowxville",44);
        userList.add( user1 );
        userList.add( user2 );
        userList.add( user3 );
    }

    @Override
    public User getRandomUser() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, userList.size());
        return userList.get( randomNum );
    }

    @Override
    public List<User> getAllUsers() {
        return userList;
    }
}
