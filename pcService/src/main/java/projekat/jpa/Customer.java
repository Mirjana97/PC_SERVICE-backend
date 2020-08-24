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

import static projekat.constants.Roles.CUSTOMER;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer extends User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerid;

	private String customeradress;

	private String customername;

	private String customerpassword;

	private String customerphone;

	private String customersurname;

	private String customerusername;

	//bi-directional many-to-one association to Appointmentconfirmationandservicetracking
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<Appointmentconfirmationandservicetracking> appointmentconfirmationandservicetrackings;

	//bi-directional many-to-one association to Computer
	@JsonIgnore
	@OneToMany(mappedBy="customer")
	private List<Computer> computers;

	@Transient
	private String role = CUSTOMER;

	public Customer() {
	}

	public Integer getCustomerid() {
		return this.customerid;
	}

	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}

	public String getCustomeradress() {
		return this.customeradress;
	}

	public void setCustomeradress(String customeradress) {
		this.customeradress = customeradress;
	}

	public String getCustomername() {
		return this.customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCustomerpassword() {
		return this.customerpassword;
	}

	public void setCustomerpassword(String customerpassword) {
		this.customerpassword = customerpassword;
	}

	public String getCustomerphone() {
		return this.customerphone;
	}

	public void setCustomerphone(String customerphone) {
		this.customerphone = customerphone;
	}

	public String getCustomersurname() {
		return this.customersurname;
	}

	public void setCustomersurname(String customersurname) {
		this.customersurname = customersurname;
	}

	public String getCustomerusername() {
		return this.customerusername;
	}

	public void setCustomerusername(String customerusername) {
		this.customerusername = customerusername;
	}

	@Override
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Appointmentconfirmationandservicetracking> getAppointmentconfirmationandservicetrackings() {
		return this.appointmentconfirmationandservicetrackings;
	}

	public void setAppointmentconfirmationandservicetrackings(List<Appointmentconfirmationandservicetracking> appointmentconfirmationandservicetrackings) {
		this.appointmentconfirmationandservicetrackings = appointmentconfirmationandservicetrackings;
	}

	public Appointmentconfirmationandservicetracking addAppointmentconfirmationandservicetracking(Appointmentconfirmationandservicetracking appointmentconfirmationandservicetracking) {
		getAppointmentconfirmationandservicetrackings().add(appointmentconfirmationandservicetracking);
		appointmentconfirmationandservicetracking.setCustomer(this);

		return appointmentconfirmationandservicetracking;
	}

	public Appointmentconfirmationandservicetracking removeAppointmentconfirmationandservicetracking(Appointmentconfirmationandservicetracking appointmentconfirmationandservicetracking) {
		getAppointmentconfirmationandservicetrackings().remove(appointmentconfirmationandservicetracking);
		appointmentconfirmationandservicetracking.setCustomer(null);

		return appointmentconfirmationandservicetracking;
	}

	public List<Computer> getComputers() {
		return this.computers;
	}

	public void setComputers(List<Computer> computers) {
		this.computers = computers;
	}

	public Computer addComputer(Computer computer) {
		getComputers().add(computer);
		computer.setCustomer(this);

		return computer;
	}

	public Computer removeComputer(Computer computer) {
		getComputers().remove(computer);
		computer.setCustomer(null);

		return computer;
	}

	@Override
	@JsonIgnore
	public List<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role));
		return authorities;
	}

	@Override
	public String getPassword() {
		return customerpassword;
	}

	@Override
	public String getUsername() {
		return customerusername;
	}
}