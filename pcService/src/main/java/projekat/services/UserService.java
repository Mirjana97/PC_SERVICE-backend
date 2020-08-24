package projekat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import projekat.jpa.Customer;
import projekat.reps.CustomerRepository;
import projekat.reps.EmployeeRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findBycustomerusernameIgnoreCase(username);
        if (customer == null) {
            return employeeRepository.findByemployeeusernameIgnoreCase(username);
        }
        return customer;
    }
}
