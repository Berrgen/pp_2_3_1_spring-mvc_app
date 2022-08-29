package web.service;

import web.models.User;

import java.util.List;

public interface UserService {

    List<User> allUsers();
    void add(User user);
    void deleteById(long id);
    void edit(User user);
    User getById(long id);

}
