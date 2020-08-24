package projekat.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the typeofservice database table.
 * 
 */
@Entity
@NamedQuery(name="Typeofservice.findAll", query="SELECT t FROM Typeofservice t")
public class Typeofservice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer typeofserviceid;

	private String typeofservicename;

	private BigDecimal typeofserviceprice;

	//bi-directional many-to-one association to Pcservice
	@JsonIgnore
	@OneToMany(mappedBy="typeofservice")
	private List<Pcservice> pcservices;

	public Typeofservice() {
	}

	public Integer getTypeofserviceid() {
		return this.typeofserviceid;
	}

	public void setTypeofserviceid(Integer typeofserviceid) {
		this.typeofserviceid = typeofserviceid;
	}

	public String getTypeofservicename() {
		return this.typeofservicename;
	}

	public void setTypeofservicename(String typeofservicename) {
		this.typeofservicename = typeofservicename;
	}

	public BigDecimal getTypeofserviceprice() {
		return this.typeofserviceprice;
	}

	public void setTypeofserviceprice(BigDecimal typeofserviceprice) {
		this.typeofserviceprice = typeofserviceprice;
	}

	public List<Pcservice> getPcservices() {
		return this.pcservices;
	}

	public void setPcservices(List<Pcservice> pcservices) {
		this.pcservices = pcservices;
	}

	public Pcservice addPcservice(Pcservice pcservice) {
		getPcservices().add(pcservice);
		pcservice.setTypeofservice(this);

		return pcservice;
	}

	public Pcservice removePcservice(Pcservice pcservice) {
		getPcservices().remove(pcservice);
		pcservice.setTypeofservice(null);

		return pcservice;
	}

}