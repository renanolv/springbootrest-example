/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.service;

import example.model.Customer;
import org.springframework.stereotype.Service;
import example.repository.CustomerRepository;
import flexjson.JSONSerializer;
/**
 *
 * @author renan
 */
@Service
public class CustomerService {

    JSONSerializer json = new JSONSerializer();

    public CustomerService() {
    }
    
    CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    //
    public String createUser(String firstName, String secondName) {
        Customer customer = null;
        try {
            customer = new Customer(firstName, secondName);
            customerRepository.save(customer);
        } catch (Exception ex) {
            return json.serialize(ex.getMessage().toString());
        }
        return json.serialize(customer);
    }
    //
    public Customer findOne(Long id) {
        return customerRepository.findOne(id);
    }
    //
    public String delete(Long id) {
        try {
            customerRepository.delete(id);
        } catch (Exception ex) {
            return json.serialize(ex.getMessage().toString());
        }
        return ("{\"status\" : \"200\", \"description\" : \"user with id {} deleted\"}"+id);
    }
    //
    public String update(Long id, String firstName) {
        try {
            Customer customer = customerRepository.findOne(id);
            customer.setFirstName(firstName);
            customerRepository.save(customer);
        } catch (Exception ex) {
            return json.serialize(ex.getMessage().toString());
        }
        return json.serialize(customerRepository.findOne(id));
    }
}
