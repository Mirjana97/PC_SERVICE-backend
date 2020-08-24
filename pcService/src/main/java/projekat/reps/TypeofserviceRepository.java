package projekat.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import projekat.jpa.Typeofservice;

public interface TypeofserviceRepository extends JpaRepository<Typeofservice, Integer>{
	Collection<Typeofservice> findBytypeofservicenameContainingIgnoreCase(String name);
}
