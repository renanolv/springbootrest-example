package example.controller;

import example.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import example.repository.EmployeeRepository;
import example.service.KafkaProducer;



import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    
    @Autowired
    KafkaProducer producer = new KafkaProducer();

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public @ResponseBody
    String create(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("salary") double salary) {
        Employee employee = null;
        try {

            employee = new Employee(name, email, salary);
            employeeRepository.save(employee);
            producer.receiver(employee);

        } catch (Exception ex) {
            return "Error creating the employee: " + ex.toString();
        }     
        return "Employee succesfully createad! (id = " + employee.getID() + ")";
    }
    

    
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Employee findOne(@PathVariable("id") Long id) {
        return employeeRepository.findOne(id);
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    String delete(@PathVariable Long id) {
        Employee employee = new Employee(id);
        employeeRepository.delete(employee);

        return "Deleted employee with id: " + employee.getID();
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    Employee update(@PathVariable("id") Long id, @RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("salary") double salary) {

        Employee employee = employeeRepository.findOne(id);
        employee.setName(name);
        employee.setEmail(email);
        employee.setSalary(salary);
        employeeRepository.save(employee);

        return employeeRepository.findOne(id);
    }

}
