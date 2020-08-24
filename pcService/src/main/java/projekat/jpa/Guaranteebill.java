package projekat.jpa;

import java.io.Serializable;
import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the guaranteebill database table.
 * 
 */
@Entity
@NamedQuery(name="Guaranteebill.findAll", query="SELECT g FROM Guaranteebill g")
public class Guaranteebill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer guaranteebillid;

	@Temporal(TemporalType.DATE)
	private Date guaranteeexpires;

	@Temporal(TemporalType.DATE)
	private Date servicefinisheddate;

	private BigDecimal total;

	//bi-directional many-to-one association to Pcservice
	@ManyToOne
	@JoinColumn(name="serviceid")
	private Pcservice pcservice;

	public Guaranteebill() {
	}

	public Integer getGuaranteebillid() {
		return this.guaranteebillid;
	}

	public void setGuaranteebillid(Integer guaranteebillid) {
		this.guaranteebillid = guaranteebillid;
	}

	public Date getGuaranteeexpires() {
		return this.guaranteeexpires;
	}

	public void setGuaranteeexpires(Date guaranteeexpires) {
		this.guaranteeexpires = guaranteeexpires;
	}

	public Date getServicefinisheddate() {
		return this.servicefinisheddate;
	}

	public void setServicefinisheddate(Date servicefinisheddate) {
		this.servicefinisheddate = servicefinisheddate;
	}

	public BigDecimal getTotal() {
		return this.total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Pcservice getPcservice() {
		return this.pcservice;
	}

	public void setPcservice(Pcservice pcservice) {
		this.pcservice = pcservice;
	}

}