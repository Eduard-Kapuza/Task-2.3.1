package web.service;

import org.springframework.stereotype.Component;
import web.model.User;

import java.util.List;

@Component
public interface UserService {

    void saveUser(User user);

    List<User> getListUser();

    int showAmountUsers();

    List<User> getListUserLimit(int limit);

    User getUserById(int id);

    void updateUser(User user, int id);

    void deleteUser(User user);
}