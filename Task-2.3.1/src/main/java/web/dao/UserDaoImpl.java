package web.dao;

import jakarta.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import web.model.User;

import java.util.List;


@Repository
@EnableTransactionManagement
@Transactional
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getListUserLimit(int limit) {

        String queryHQL = "from User ORDER BY id ASC LIMIT ?1";
        Query q = sessionFactory.getCurrentSession().createQuery(queryHQL, User.class);
        q.setParameter(1, limit);


        List<User> users = q.getResultList();
        return users;
    }

    @Override
    public User getUserById(int id) {
        String queryHQL = "from User where id=?1";
        Query q = sessionFactory.getCurrentSession().createQuery(queryHQL, User.class);
        q.setParameter(1, id);
        return (User) q.getResultList().stream().findFirst().orElse(new User());
    }

    @Override
    public void update(User user, int id) {
        User userToBeUpDated = getUserById(id);
        userToBeUpDated.setName(user.getName());
        userToBeUpDated.setSurname(user.getSurname());
        userToBeUpDated.setAge(user.getAge());
        saveUser(userToBeUpDated);
    }

    @Override
    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().remove(user);
    }

    @Override
    public List<User> getListUser() {
        String queryHQL = "from User";
        List<User> users = sessionFactory.getCurrentSession()
                .createQuery(queryHQL, User.class)
                .getResultList();
        return users;
    }

    @Override
    public void saveUser(User user) {
        sessionFactory.getCurrentSession().persist(user);
    }
}