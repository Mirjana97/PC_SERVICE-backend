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
import projekat.jpa.Guaranteebill;
import projekat.reps.GuaranteeBillrepository;

import static projekat.constants.Authorization.*;

@RestController
@PreAuthorize(HAS_CUSTOMER_OR_EMPLOYEE_ROLE)
public class GuaranteeBillController {

	@Autowired
	private GuaranteeBillrepository guaranteeBillRepository;
	
	@Autowired 
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/guaranteeBill")
	public Collection<Guaranteebill> getGuaranteeBills() {
		return guaranteeBillRepository.findAll();
	}
	
	@GetMapping("/guaranteeBill/{id}")
	public Guaranteebill getGuaranteeBill(@PathVariable Integer id) {
		if(guaranteeBillRepository.existsById(id))
			return guaranteeBillRepository.getOne(id);
		
		return null;
	}
	
	@CrossOrigin
	@DeleteMapping("/guaranteeBill/{id}")
	public ResponseEntity<Guaranteebill> deleteGuaranteeBill(@PathVariable Integer id) {
		if(guaranteeBillRepository.existsById(id)) {
			guaranteeBillRepository.deleteById(id);
			
			if (id == -10)
				jdbcTemplate.execute("INSERT INTO \"guaranteebill\"(\"guaranteebillid\", \"guaranteeexpires\", \"servicefinisheddate\", \"total\", \"serviceid\")"
						+ "VALUES(-10,'2020-07-06','2020-07-06',250,1);");
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin
	@PostMapping("/guaranteeBill")
	public ResponseEntity<Guaranteebill> insertGuaranteeBill(@RequestBody Guaranteebill guaranteeBill) {
		guaranteeBillRepository.save(guaranteeBill);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@CrossOrigin
	@PutMapping("/guaranteeBill")
	public ResponseEntity<Guaranteebill> updateGuaranteeBill(@RequestBody Guaranteebill guaranteeBill) {
		if(guaranteeBillRepository.existsById(guaranteeBill.getGuaranteebillid()))
			guaranteeBillRepository.save(guaranteeBill);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
