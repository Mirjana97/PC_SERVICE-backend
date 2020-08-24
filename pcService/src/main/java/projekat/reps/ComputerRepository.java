package projekat.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import projekat.jpa.Computer;
import projekat.jpa.Customer;

public interface ComputerRepository extends JpaRepository<Computer, Integer> {
	Collection<Computer> findBymanufacturernameContainingIgnoreCase (String name);
	Collection<Computer> findByCustomer(Customer c);
	@Query(value = "select coalesce(max(id)+1, 1) from computer where customerid = ?1", nativeQuery = true)
	Integer nextRBr(Integer customerid);
}
