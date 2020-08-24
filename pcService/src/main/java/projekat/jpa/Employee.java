package projekat.jpa;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import projekat.constants.Roles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static projekat.constants.Roles.EMPLOYEE;


/**
 * The persistent class for the employee database table.
 */
@Entity
@NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
public class Employee extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeid;

    private String employeeadress;

    private String employeename;

    private String employeepassword;

    private String employeephone;

    private String employeesurname;

    private String employeeusername;

    //bi-directional many-to-one association to Appointmentconfirmationandservicetracking
    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<Appointmentconfirmationandservicetracking> appointmentconfirmationandservicetrackings;

    //bi-directional many-to-one association to Pcservice
    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<Pcservice> pcservices;

    @Transient
    private String role = EMPLOYEE;

    public Employee() {
    }

    public Integer getEmployeeid() {
        return this.employeeid;
    }

    public void setEmployeeid(Integer employeeid) {
        this.employeeid = employeeid;
    }

    public String getEmployeeadress() {
        return this.employeeadress;
    }

    public void setEmployeeadress(String employeeadress) {
        this.employeeadress = employeeadress;
    }

    public String getEmployeename() {
        return this.employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public String getEmployeepassword() {
        return this.employeepassword;
    }

    public void setEmployeepassword(String employeepassword) {
        this.employeepassword = employeepassword;
    }

    public String getEmployeephone() {
        return this.employeephone;
    }

    public void setEmployeephone(String employeephone) {
        this.employeephone = employeephone;
    }

    public String getEmployeesurname() {
        return this.employeesurname;
    }

    public void setEmployeesurname(String employeesurname) {
        this.employeesurname = employeesurname;
    }

    public String getEmployeeusername() {
        return this.employeeusername;
    }

    public void setEmployeeusername(String employeeusername) {
        this.employeeusername = employeeusername;
    }

    public List<Appointmentconfirmationandservicetracking> getAppointmentconfirmationandservicetrackings() {
        return this.appointmentconfirmationandservicetrackings;
    }

    @Override
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setAppointmentconfirmationandservicetrackings(List<Appointmentconfirmationandservicetracking> appointmentconfirmationandservicetrackings) {
        this.appointmentconfirmationandservicetrackings = appointmentconfirmationandservicetrackings;
    }

    public Appointmentconfirmationandservicetracking addAppointmentconfirmationandservicetracking(Appointmentconfirmationandservicetracking appointmentconfirmationandservicetracking) {
        getAppointmentconfirmationandservicetrackings().add(appointmentconfirmationandservicetracking);
        appointmentconfirmationandservicetracking.setEmployee(this);

        return appointmentconfirmationandservicetracking;
    }

    public Appointmentconfirmationandservicetracking removeAppointmentconfirmationandservicetracking(Appointmentconfirmationandservicetracking appointmentconfirmationandservicetracking) {
        getAppointmentconfirmationandservicetrackings().remove(appointmentconfirmationandservicetracking);
        appointmentconfirmationandservicetracking.setEmployee(null);

        return appointmentconfirmationandservicetracking;
    }

    public List<Pcservice> getPcservices() {
        return this.pcservices;
    }

    public void setPcservices(List<Pcservice> pcservices) {
        this.pcservices = pcservices;
    }

    public Pcservice addPcservice(Pcservice pcservice) {
        getPcservices().add(pcservice);
        pcservice.setEmployee(this);

        return pcservice;
    }

    public Pcservice removePcservice(Pcservice pcservice) {
        getPcservices().remove(pcservice);
        pcservice.setEmployee(null);

        return pcservice;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    @Override
    public String getPassword() {
        return employeepassword;
    }

    @Override
    public String getUsername() {
        return employeeusername;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}