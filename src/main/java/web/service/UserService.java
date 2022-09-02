package web.service;

import web.models.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    void addUser(User user);
    void deleteUserById(long id);
    void editUser(User user);
    User getUserById(long id);

}
