package projekat.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import projekat.jpa.Appointmentconfirmationandservicetracking;

public interface AppointmentConfirmationAndServiceTrackingRepository extends JpaRepository<Appointmentconfirmationandservicetracking, Integer> {
	Collection<Appointmentconfirmationandservicetracking> findBycomputerisservicedTrue();
}
