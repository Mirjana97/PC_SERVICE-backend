package projekat.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import projekat.jpa.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	Collection<Customer> findBycustomernameContainingIgnoreCase (String name);
	Customer findBycustomerusernameIgnoreCase(String customerusername);

}
