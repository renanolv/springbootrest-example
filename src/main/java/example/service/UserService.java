/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.service;

import example.model.User;

/**
 *
 * @author renan
 */
public interface UserService {
    
    void save(User user);

    User findByUserName(String userName);
    
}
