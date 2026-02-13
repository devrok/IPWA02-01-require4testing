package entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Testfall implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id=0L;
	private String titel;
	private String beschreibung;
	
	@ManyToOne(optional = true)
	private Anforderung anforderung;
	
	public Testfall() {
		
	}	
	
	public Testfall(Long id, String titel, String beschreibung) {
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

	public Anforderung getAnforderung() {
		return anforderung;
	}

	public void setAnforderung(Anforderung anforderung) {
		this.anforderung = anforderung;
	}
	
	/*
	 * WICHTIG FÜR PICKLIST!!
	 */
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (!(o instanceof Testfall)) return false;
	    Testfall other = (Testfall) o;
	    return id != null && id.equals(other.getId());
	}
	
	@Override
	public int hashCode() {
	    return getClass().hashCode();
	}
}