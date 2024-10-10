package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;

import java.util.List;

@Component
public interface UserDao {
    void saveUser(User user);

    List<User> getListUser();

    List<User> getListUserLimit(int limit);

    User getUserById(int id);

    void update(User user, int id);

    void deleteUser(User user);
}