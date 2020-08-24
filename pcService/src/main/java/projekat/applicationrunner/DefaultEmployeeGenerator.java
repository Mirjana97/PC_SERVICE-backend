package projekat.applicationrunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import projekat.jpa.Employee;
import projekat.reps.EmployeeRepository;

@Component
public class DefaultEmployeeGenerator implements ApplicationRunner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (!adminExists()) {
            generateAdminAccount();
        }
    }

    private boolean adminExists() {
        return employeeRepository.findByemployeeusernameIgnoreCase("admin") != null;
    }

    private void generateAdminAccount() {
        Employee defaultEmployee = new Employee();
        defaultEmployee.setEmployeename("Admin");
        defaultEmployee.setEmployeesurname("Admin");
        defaultEmployee.setEmployeephone("0");
        defaultEmployee.setEmployeeadress("0");
        defaultEmployee.setEmployeeusername("admin");
        defaultEmployee.setEmployeepassword("admin");
        employeeRepository.save(defaultEmployee);
    }
}
