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
import projekat.jpa.Appointmentconfirmationandservicetracking;
import projekat.reps.AppointmentConfirmationAndServiceTrackingRepository;

import static projekat.constants.Authorization.*;

@RestController
@PreAuthorize(HAS_CUSTOMER_OR_EMPLOYEE_ROLE)
public class AppointmentConfirmationAndServiceTrackingRestController {

	@Autowired
	private AppointmentConfirmationAndServiceTrackingRepository serviceTrackingRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping("/serviceTracking")
	public Collection<Appointmentconfirmationandservicetracking> getAppointmentConfirmationAndServiceTrackings(){
		return serviceTrackingRepository.findAll();
	}
	
	@GetMapping("/serviceTracking/{id}")
	public Appointmentconfirmationandservicetracking serviceTracking(@PathVariable Integer id){
		if (serviceTrackingRepository.existsById(id))
			 return serviceTrackingRepository.getOne(id);
		return null;
	}
	
	@GetMapping("/serviceTrackingServiced")
	public Collection<Appointmentconfirmationandservicetracking> getAppointmentsByStatus(){
		return serviceTrackingRepository.findBycomputerisservicedTrue();
	}
	
	@CrossOrigin
	@DeleteMapping("serviceTracking/{id}")
	public ResponseEntity<Appointmentconfirmationandservicetracking> deleteAppointment(@PathVariable Integer id){
		if (serviceTrackingRepository.existsById(id)) {
			serviceTrackingRepository.deleteById(id);
			
			if (id == -10)
				jdbcTemplate.execute("INSERT INTO \"appointmentconfirmationandservicetracking\"(\"confirmationid\", \"employeeid\", \"customerid\", \"appointmentdate\", \"computerreceived\", \"computerisserviced\", \"takingbackdate\")"
						+ "VALUES(-10,3,3,'2020-07-03',false,true,'2020-07-17');");
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@CrossOrigin
	@PostMapping("/serviceTracking")
	public ResponseEntity<Appointmentconfirmationandservicetracking> insertAppointment(@RequestBody Appointmentconfirmationandservicetracking serviceTracking){
		serviceTrackingRepository.save(serviceTracking);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@CrossOrigin
	@PutMapping("/serviceTracking")
	public ResponseEntity<Appointmentconfirmationandservicetracking> updateAppointment(@RequestBody Appointmentconfirmationandservicetracking serviceTracking){
		if (serviceTrackingRepository.existsById(serviceTracking.getConfirmationid())) 
			serviceTrackingRepository.save(serviceTracking);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	
}
