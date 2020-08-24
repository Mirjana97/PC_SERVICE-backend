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

import com.fasterxml.jackson.annotation.JsonIgnore;

import projekat.constants.Authorization;
import projekat.jpa.Employee;
import projekat.jpa.Pcservice;
import projekat.reps.EmployeeRepository;
import projekat.reps.PCServiceRepository;

import static projekat.constants.Authorization.*;

@RestController
@PreAuthorize(HAS_EMPLOYEE_ROLE)
public class PCServiceRestController {
	
	@Autowired
	private PCServiceRepository pcserviceRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/pcService")
	public Collection<Pcservice> getPCServices(){
		return pcserviceRepository.findAll();
	}
	
	@GetMapping("/pcService/{id}")
	public Pcservice getPCService(@PathVariable Integer id) {
		if (pcserviceRepository.existsById(id))
			return pcserviceRepository.getOne(id);
		return null;
	}
	
	@GetMapping("/pcServiceFinished")
	public Collection<Pcservice> getPCServiceByStatus(){
		return pcserviceRepository.findByisfinishedserviceTrue();
	}
	
	/*@GetMapping("/pcserviceByEmployee/{id}")
	public Collection<Pcservice> findServicebyEmployee(@PathVariable("id") int id){
		Employee e = employeeRepository.getOne(id);
		return pcserviceRepository.findByEmployee(e);
	}*/
	
	@CrossOrigin
	@DeleteMapping("/pcService/{id}")
	public  ResponseEntity<Pcservice> deletePCService(@PathVariable Integer id){
		if (pcserviceRepository.existsById(id)) {
			pcserviceRepository.deleteById(id);
			
			if (id == -10)
				jdbcTemplate.execute("INSERT INTO \"pcservice\"(\"serviceid\", \"computerid\", \"typeofserviceid\", \"employeeid\", \"isfinishedservice\")"
						+ "VALUES(-10,2,2,2,true);");
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin
	@PostMapping("/pcService")
	public ResponseEntity<Pcservice> insertPcService(@RequestBody Pcservice pcService){
		pcserviceRepository.save(pcService);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@CrossOrigin
	@PutMapping("/pcService")
	public ResponseEntity<Pcservice> updatePcService(@RequestBody Pcservice pcService){
		if (pcserviceRepository.existsById(pcService.getServiceid()))
			pcserviceRepository.save(pcService);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
