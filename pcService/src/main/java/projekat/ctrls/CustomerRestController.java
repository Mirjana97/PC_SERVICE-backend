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
import projekat.jpa.Customer;
import projekat.reps.CustomerRepository;

import static projekat.constants.Authorization.*;

@RestController
@PreAuthorize(HAS_EMPLOYEE_ROLE)
public class CustomerRestController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/customer")
    public Collection<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable Integer id) {
        if (customerRepository.existsById(id))
            return customerRepository.getOne(id);

        return null;
    }

    @GetMapping("/customerName/{name}")
    public Collection<Customer> findCustomerByName(@PathVariable String name) {
        return customerRepository.findBycustomernameContainingIgnoreCase(name);
    }

    @CrossOrigin
    @DeleteMapping("/customer/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable Integer id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);

            if (id == -10)
                jdbcTemplate.execute("INSERT INTO \"customer\"(\"customerid\", \"customername\", \"customersurname\", \"customerphone\", \"customeradress\", \"customerusername\", \"customerpassword\")"
                        + "VALUES(-10,'test','test','065555555','Test 7','testt','testt');");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @CrossOrigin
    @PostMapping("/customer")
    public ResponseEntity<HttpStatus> insertCustomer(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("/customer")
    public ResponseEntity<HttpStatus> updateCustomer(@RequestBody Customer customer) {
        if (customerRepository.existsById(customer.getCustomerid()))
            customerRepository.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
