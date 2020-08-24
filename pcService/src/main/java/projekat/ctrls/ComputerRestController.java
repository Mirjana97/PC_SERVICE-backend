package projekat.ctrls;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import projekat.jpa.Computer;
import projekat.jpa.Customer;
import projekat.reps.ComputerRepository;
import projekat.reps.CustomerRepository;

import static projekat.constants.Authorization.*;

@RestController
@PreAuthorize(HAS_EMPLOYEE_ROLE)
public class ComputerRestController {
	
	@Autowired
	private ComputerRepository computerRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@GetMapping("/computer")
	public Collection<Computer> getComputers() {
		return computerRepository.findAll();
	}

	@GetMapping("/computer/{id}") 
	public Computer getComputer(@PathVariable Integer id) {
		if(computerRepository.existsById(id)) 
		return computerRepository.getOne(id);
		
		return null;
	}
	
	@GetMapping("/computerManufacturerName/{name}")
	public Collection<Computer> findByManufacturerName(@PathVariable String name) {
		return computerRepository.findBymanufacturernameContainingIgnoreCase(name);
	}
	
	@GetMapping("/computerByCustomer/{id}")
	public Collection<Computer> findComputerbyCustomer(@PathVariable("id") int id){
		Customer c = customerRepository.getOne(id);
		return computerRepository.findByCustomer(c);
	}
	
	@CrossOrigin
	@DeleteMapping("/computer/{id}")
	public ResponseEntity<Computer> deleteComputer(@PathVariable Integer id) {
		if(computerRepository.existsById(id)) {
			computerRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin
	@PostMapping("/computer")
	public ResponseEntity<Computer> insertComputer(@RequestBody Computer computer) {
		computerRepository.save(computer);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@CrossOrigin
	@PutMapping("/computer")
	public ResponseEntity<Computer> updateComputer(@RequestBody Computer computer) {
		if(computerRepository.existsById(computer.getComputerid()))
			computerRepository.save(computer);
		return new ResponseEntity<>(HttpStatus.OK);
			
	}
}
