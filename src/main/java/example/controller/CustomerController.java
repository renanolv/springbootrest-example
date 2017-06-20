/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.controller;

import example.model.Customer;
import example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(path = "/v1/customer")
@CrossOrigin(origins = "*")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;

    public CustomerController() {
    }

    //Create customer method
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createUser(@RequestParam("firstName") String firstName, @RequestParam("secondName") String secondName) {
        return customerService.createUser(firstName, secondName);
    }
    //Find by customer id method
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Customer findOne(@PathVariable("id") Long id) {
        return customerService.findOne(id);
    }
    //Delete by customer id
    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    String delete(@PathVariable Long id) {
        return customerService.delete(id);
    }
    //Update by customer id
    @RequestMapping(path = "/update/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    String update(@PathVariable("id") Long id, @RequestParam("firstName") String firstName) {;
        return customerService.update(id, firstName);
    }
}
