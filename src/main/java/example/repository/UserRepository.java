/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.repository;

import example.model.User;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author renan
 */
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
    
    User findByUserName(String userName);
    
   
}
