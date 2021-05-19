package org.com.service;

import org.com.entity.Talon;
import org.com.entity.User;

public interface UserService extends Service<User>{
    User readByLogin(String login);
}
