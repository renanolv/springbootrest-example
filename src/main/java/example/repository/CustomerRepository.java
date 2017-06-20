/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.repository;

import example.model.Customer;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author renan
 */
@Transactional
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
