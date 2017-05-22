/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.controller;

import example.model.User;
import example.repository.UserRepository;
import example.service.KafkaProducer;
import example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author renan
 */
@RestController
@RequestMapping(path = "/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

    @RequestMapping(path = "/createaccount", method = RequestMethod.POST)
    public String createAccount(@RequestParam("username") String userName, @RequestParam("password") String password) {

        User user = null;

        try {

            user = new User(userName, password);
            userService.save(user);
            kafkaProducer.receiveUser(user);

        } catch (Exception ex) {
            return "Error creating account: " + ex.toString();
        }
        return "Account succesfully createad! ";
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    User findOne(@PathVariable("id") Long id) {
        return userRepository.findOne(id);
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    String delete(@PathVariable Long id) {
        User user = new User(id);
        userRepository.delete(user);

        return "Deleted employee with id: " + user.getId();
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    User update(@PathVariable("id") Long id, @RequestParam("name") String name) {

        User user = userRepository.findOne(id);
        user.setUserName(name);
        userRepository.save(user);

        return userRepository.findOne(id);
    }

}
