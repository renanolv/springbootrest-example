package example.repository;

import example.model.Employee;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;

@Transactional
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    public Employee findByEmail(String email);
    

}
