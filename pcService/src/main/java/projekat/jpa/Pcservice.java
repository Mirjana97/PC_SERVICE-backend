package projekat.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the pcservice database table.
 * 
 */
@Entity
@NamedQuery(name="Pcservice.findAll", query="SELECT p FROM Pcservice p")
public class Pcservice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer serviceid;

	private Boolean isfinishedservice;

	//bi-directional many-to-one association to Guaranteebill
	@JsonIgnore
	@OneToMany(mappedBy="pcservice")
	private List<Guaranteebill> guaranteebills;

	//bi-directional many-to-one association to Computer
	@ManyToOne
	@JoinColumn(name="computerid")
	private Computer computer;

	//bi-directional many-to-one association to Employee
	@ManyToOne
	@JoinColumn(name="employeeid")
	private Employee employee;

	//bi-directional many-to-one association to Typeofservice
	@ManyToOne
	@JoinColumn(name="typeofserviceid")
	private Typeofservice typeofservice;

	public Pcservice() {
	}

	public Integer getServiceid() {
		return this.serviceid;
	}

	public void setServiceid(Integer serviceid) {
		this.serviceid = serviceid;
	}

	public Boolean getIsfinishedservice() {
		return this.isfinishedservice;
	}

	public void setIsfinishedservice(Boolean isfinishedservice) {
		this.isfinishedservice = isfinishedservice;
	}

	public List<Guaranteebill> getGuaranteebills() {
		return this.guaranteebills;
	}

	public void setGuaranteebills(List<Guaranteebill> guaranteebills) {
		this.guaranteebills = guaranteebills;
	}

	public Guaranteebill addGuaranteebill(Guaranteebill guaranteebill) {
		getGuaranteebills().add(guaranteebill);
		guaranteebill.setPcservice(this);

		return guaranteebill;
	}

	public Guaranteebill removeGuaranteebill(Guaranteebill guaranteebill) {
		getGuaranteebills().remove(guaranteebill);
		guaranteebill.setPcservice(null);

		return guaranteebill;
	}

	public Computer getComputer() {
		return this.computer;
	}

	public void setComputer(Computer computer) {
		this.computer = computer;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Typeofservice getTypeofservice() {
		return this.typeofservice;
	}

	public void setTypeofservice(Typeofservice typeofservice) {
		this.typeofservice = typeofservice;
	}

}