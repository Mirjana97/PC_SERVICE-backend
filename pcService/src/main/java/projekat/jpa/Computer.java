package projekat.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the computer database table.
 * 
 */
@Entity
@NamedQuery(name="Computer.findAll", query="SELECT c FROM Computer c")
public class Computer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer computerid;

	private String manufacturername;

	private String modelname;

	private Integer serialnumber;

	private String typeofpc;

	//bi-directional many-to-one association to Appointmentconfirmationandservicetracking
	@JsonIgnore
	@OneToMany(mappedBy="computer")
	private List<Appointmentconfirmationandservicetracking> appointmentconfirmationandservicetrackings;

	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customerid")
	private Customer customer;

	//bi-directional many-to-one association to Pcservice
	@JsonIgnore
	@OneToMany(mappedBy="computer")
	private List<Pcservice> pcservices;

	public Computer() {
	}

	public Integer getComputerid() {
		return this.computerid;
	}

	public void setComputerid(Integer computerid) {
		this.computerid = computerid;
	}

	public String getManufacturername() {
		return this.manufacturername;
	}

	public void setManufacturername(String manufacturername) {
		this.manufacturername = manufacturername;
	}

	public String getModelname() {
		return this.modelname;
	}

	public void setModelname(String modelname) {
		this.modelname = modelname;
	}

	public Integer getSerialnumber() {
		return this.serialnumber;
	}

	public void setSerialnumber(Integer serialnumber) {
		this.serialnumber = serialnumber;
	}

	public String getTypeofpc() {
		return this.typeofpc;
	}

	public void setTypeofpc(String typeofpc) {
		this.typeofpc = typeofpc;
	}

	public List<Appointmentconfirmationandservicetracking> getAppointmentconfirmationandservicetrackings() {
		return this.appointmentconfirmationandservicetrackings;
	}

	public void setAppointmentconfirmationandservicetrackings(List<Appointmentconfirmationandservicetracking> appointmentconfirmationandservicetrackings) {
		this.appointmentconfirmationandservicetrackings = appointmentconfirmationandservicetrackings;
	}

	public Appointmentconfirmationandservicetracking addAppointmentconfirmationandservicetracking(Appointmentconfirmationandservicetracking appointmentconfirmationandservicetracking) {
		getAppointmentconfirmationandservicetrackings().add(appointmentconfirmationandservicetracking);
		appointmentconfirmationandservicetracking.setComputer(this);

		return appointmentconfirmationandservicetracking;
	}

	public Appointmentconfirmationandservicetracking removeAppointmentconfirmationandservicetracking(Appointmentconfirmationandservicetracking appointmentconfirmationandservicetracking) {
		getAppointmentconfirmationandservicetrackings().remove(appointmentconfirmationandservicetracking);
		appointmentconfirmationandservicetracking.setComputer(null);

		return appointmentconfirmationandservicetracking;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Pcservice> getPcservices() {
		return this.pcservices;
	}

	public void setPcservices(List<Pcservice> pcservices) {
		this.pcservices = pcservices;
	}

	public Pcservice addPcservice(Pcservice pcservice) {
		getPcservices().add(pcservice);
		pcservice.setComputer(this);

		return pcservice;
	}

	public Pcservice removePcservice(Pcservice pcservice) {
		getPcservices().remove(pcservice);
		pcservice.setComputer(null);

		return pcservice;
	}

}