package edadamanager.controller;

import edadamanager.model.*;
import edadamanager.repository.RecipeRepository;
import edadamanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

//    private User createUser(Integer id, String login, String hashpass,
//                            Double weight, Double height,
//                            LocalDate birthday, Boolean sex) {
//
//
//        User user = new User();
//        user.setId(id);
//        user.setLogin(login);
//        user.setHashpass(hashpass);
//        user.setWeight(weight);
//        user.setHeight(height);
//        user.setBirthday(birthday);
//        user.setSex(sex);
//
//        return user;
//    }

}
