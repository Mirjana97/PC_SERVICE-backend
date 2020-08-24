package projekat.jpa;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the appointmentconfirmationandservicetracking database table.
 * 
 */
@Entity
@NamedQuery(name="Appointmentconfirmationandservicetracking.findAll", query="SELECT a FROM Appointmentconfirmationandservicetracking a")
public class Appointmentconfirmationandservicetracking implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer confirmationid;

	@Temporal(TemporalType.DATE)
	private Date appointmentdate;

	private Boolean computerisserviced;

	private Boolean computerreceived;

	@Temporal(TemporalType.DATE)
	private Date takingbackdate;

	//bi-directional many-to-one association to Computer
	@ManyToOne
	@JoinColumn(name="computerid")
	private Computer computer;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customerid")
	private Customer customer;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="employeeid")
	private Employee employee;

	public Appointmentconfirmationandservicetracking() {
	}

	public Integer getConfirmationid() {
		return this.confirmationid;
	}

	public void setConfirmationid(Integer confirmationid) {
		this.confirmationid = confirmationid;
	}

	public Date getAppointmentdate() {
		return this.appointmentdate;
	}

	public void setAppointmentdate(Date appointmentdate) {
		this.appointmentdate = appointmentdate;
	}

	public Boolean getComputerisserviced() {
		return this.computerisserviced;
	}

	public void setComputerisserviced(Boolean computerisserviced) {
		this.computerisserviced = computerisserviced;
	}

	public Boolean getComputerreceived() {
		return this.computerreceived;
	}

	public void setComputerreceived(Boolean computerreceived) {
		this.computerreceived = computerreceived;
	}

	public Date getTakingbackdate() {
		return this.takingbackdate;
	}

	public void setTakingbackdate(Date takingbackdate) {
		this.takingbackdate = takingbackdate;
	}

	public Computer getComputer() {
		return this.computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}