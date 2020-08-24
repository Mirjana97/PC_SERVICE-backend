package projekat.reps;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import projekat.jpa.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Collection<Employee> findByemployeenameContainingIgnoreCase (String name);
	Employee findByemployeeusernameIgnoreCase(String employeeusername);
}
