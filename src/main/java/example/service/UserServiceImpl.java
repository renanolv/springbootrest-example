/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.service;

import example.model.User;
import example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author renan
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

   
    @Override
    public void save(User user) {
        
        user.setPassword(user.getPassword());
        userRepository.save(user);

    }

    @Override
    public User findByUserName(String userName) {
       return userRepository.findByUserName(userName);
    }

}
