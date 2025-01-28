package demo.domain.entity;

import java.time.LocalDateTime;

import demo.domain.enums.ServiceType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "historic")
public class HistoricEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTransaction;
	private Integer idUser;
	private String nameUser;
	private Integer balance;
	private ServiceType serviceType;
	private Integer valueTransaction;
	private Integer toId;
	private String nameTo;
	private LocalDateTime date;
	
	    public String toStringDeposit() {
	        return "Id Transaction: " + idTransaction +
	                " | Id User: " + idUser +
	                " | Name: " + nameUser +
	                " | Balance: " + balance +
	                " | Service: " + serviceType.name() +
	                " | Value: " + valueTransaction +
	                " | To Id: " + toId +
	                " | Receptor: " + nameTo +
	                " | Date: " + date;
	    }
	    
	    public String privateService() {
	    	return "Id Transaction: " + idTransaction +
	                " | Id User: " + idUser +
	                " | Name: " + nameUser +
	                " | Balance: " + balance +
	                " | Service: " + serviceType.name() +
	                " | Value: " + valueTransaction +
	                " | Date: " + date;
	    }
}
