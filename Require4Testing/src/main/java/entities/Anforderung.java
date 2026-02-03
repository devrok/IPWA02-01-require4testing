package entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Anforderung implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id = 0L;
	private String titel;
	private String beschreibung;
		
	public Anforderung() {
		
	}
	
	public Anforderung(Long id, String titel, String beschreibung) {
		this.setId(id);
		this.setTitel(titel);
		this.setBeschreibung(beschreibung);
	}

	public String getBeschreibung() {
		return beschreibung;
	}

	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Anforderung clone() {
		return new Anforderung(this.id, this.titel, this.beschreibung);
	}
}