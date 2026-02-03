package entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rolle implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private Long id;
	private String bezeichnung;

	public Rolle() {
		
	}
	
	public Rolle(Long id, String bezeichnung) {
		this.setId(id);
		this.setBezeichnung(bezeichnung);
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
 