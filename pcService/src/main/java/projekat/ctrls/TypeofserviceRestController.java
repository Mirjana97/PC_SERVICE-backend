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
import projekat.jpa.Typeofservice;
import projekat.reps.TypeofserviceRepository;

import static projekat.constants.Authorization.*;

@RestController
@PreAuthorize(HAS_EMPLOYEE_ROLE)
public class TypeofserviceRestController {
	
	@Autowired
	private TypeofserviceRepository typeOfServiceRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/typeOfService")
	public Collection<Typeofservice> getTypeOfServices() {
		return typeOfServiceRepository.findAll();
	}
	
	@GetMapping("/typeOfService/{id}")
	public Typeofservice getTypeOfService(@PathVariable Integer id) {
		if(typeOfServiceRepository.existsById(id))
		return typeOfServiceRepository.getOne(id);
		
		return null;
	}
	
	@GetMapping("/typeOfServiceName/{name}")
	public Collection<Typeofservice> findByTypeOfServiceName(@PathVariable String name) {
		return typeOfServiceRepository.findBytypeofservicenameContainingIgnoreCase(name);
	}
	
	@CrossOrigin
	@DeleteMapping("/typeOfService/{id}") 
	public ResponseEntity<HttpStatus> deleteTypeOfService(@PathVariable Integer id) {
		if(typeOfServiceRepository.existsById(id)) {
			typeOfServiceRepository.deleteById(id);
			
			if (id == -10)
				jdbcTemplate.execute("INSERT INTO \"typeofservice\"(\"typeofserviceid\", \"typeofservicename\", \"typeofserviceprice\")"
						+ "VALUES(-10,'test',500);");
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin
	@PostMapping("/typeOfService")
	public ResponseEntity<HttpStatus> insertTypeOfService(@RequestBody Typeofservice typeOfService) {
		typeOfServiceRepository.save(typeOfService);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@CrossOrigin
	@PutMapping("/typeOfService")
	public ResponseEntity<HttpStatus> updateTypeOfService(@RequestBody Typeofservice typeOfService) {
		if(typeOfServiceRepository.existsById(typeOfService.getTypeofserviceid()))
			typeOfServiceRepository.save(typeOfService);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
