package projekat.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import projekat.jpa.Employee;
import projekat.jpa.Pcservice;

public interface PCServiceRepository extends JpaRepository<Pcservice, Integer> {
	Collection<Pcservice> findByisfinishedserviceTrue();

}
