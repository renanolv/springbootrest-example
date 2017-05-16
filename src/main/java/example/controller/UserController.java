/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.controller;

import example.model.User;
import example.repository.UserRepository;
import example.service.AccountService;
import example.service.SecretService;
import example.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import validator.UserValidator;

/**
 *
 * @author renan
 */
@RestController
@RequestMapping(path = "/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    SecretService secretService;

    @Autowired
    private UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }

    @RequestMapping(path = "/createaccount", method = RequestMethod.POST)
    public @ResponseBody
    String createAccount(@RequestParam("username") String userName, @RequestParam("password") String password, @RequestParam("passwordConfirm") String passwordConfirm, BindingResult bindingResult) {

        User user = null;

        try {

            userValidator.validate(user, bindingResult);
            user = new User(userName, password, passwordConfirm);

            userService.save(user);

        } catch (Exception ex) {
            return "Error creating account: " + ex.toString();
        }
        return "Account succesfully createad! ";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public @ResponseBody
    String login(@RequestParam("username") String userName, @RequestParam("password") String password) {

        Date now = new Date();
        Date exp = new Date(System.currentTimeMillis() + (1000 * 420));

        String jwt = Jwts.builder()
                .setHeaderParam("kid", secretService.getMyPublicCreds().getKid())
                .setIssuedAt(now)
                .setIssuer(userName)
                .setNotBefore(now)
                .setExpiration(exp)
                .signWith(
                        SignatureAlgorithm.RS256,
                        secretService.getMyPrivateKey()
                )
                .compact();

        return "redirect:welcome";

    }

}
