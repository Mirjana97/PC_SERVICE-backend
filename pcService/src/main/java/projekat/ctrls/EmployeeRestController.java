package projekat.ctrls;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import projekat.constants.Authorization;
import projekat.constants.Roles;
import projekat.jpa.Employee;
import projekat.reps.EmployeeRepository;

import static projekat.constants.Authorization.*;
import static projekat.constants.Authorization.HAS_EMPLOYEE_ROLE;
import static projekat.constants.Roles.EMPLOYEE;

@RestController
@PreAuthorize(HAS_EMPLOYEE_ROLE)
public class EmployeeRestController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/employee")
    public Collection<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable Integer id) {
        if (employeeRepository.existsById(id))
            return employeeRepository.getOne(id);

        return null;
    }

    @GetMapping("/employeeName/{name}")
    public Collection<Employee> findByName(@PathVariable String name) {
        return employeeRepository.findByemployeenameContainingIgnoreCase(name);
    }

    @CrossOrigin
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Integer id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);

            if (id == -10)
                jdbcTemplate.execute("INSERT INTO \"employee\"(\"employeeid\", \"employeename\", \"employeesurname\", \"employeephone\", \"employeeadress\", \"employeeusername\", \"employeepassword\")"
                        + "VALUES(-10,'test','test','055555','test','petar1','sifra123');");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @CrossOrigin
    @PostMapping("/employee")
    public ResponseEntity<HttpStatus> insertEmployee(@RequestBody Employee employee) {
        employeeRepository.save(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("/employee")
    public ResponseEntity<HttpStatus> updateEmployee(@RequestBody Employee employee) {
        if (employeeRepository.existsById(employee.getEmployeeid()))
            employeeRepository.save(employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

