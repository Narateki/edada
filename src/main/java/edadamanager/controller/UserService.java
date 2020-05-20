package edadamanager.controller;

import edadamanager.model.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserService {
    private List<User> users = new LinkedList();

    public List<User> findAllUs() {
        return this.users;
    }

    public void save(User user) {
        this.users.add(user);
    }

    private User createUser(Integer id, String login, String hashpass,
                            Double weight, Double height,
                            LocalDate birthday, Boolean sex) {


        User user = new User();
        user.setId(id);
        user.setLogin(login);
        user.setHashpass(hashpass);
        user.setWeight(weight);
        user.setHeight(height);
        user.setBirthday(birthday);
        user.setSex(sex);

        return user;
    }

}
