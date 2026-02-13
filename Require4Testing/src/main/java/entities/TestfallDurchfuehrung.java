package entities;

import enums.TestfallErgebnisStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class TestfallDurchfuehrung {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = 0L;
	
	@ManyToOne(optional = false)
	private TestlaufDurchfuehrung testlaufDurchfuehrung;
	
	@ManyToOne(optional = false)
	private Testfall testfall;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TestfallErgebnisStatus ergebnis = TestfallErgebnisStatus.NICHT_AUSGEFUEHRT;
	
	private String kommentar;
	
	public TestfallDurchfuehrung() {
		
	}
	
	public TestfallDurchfuehrung(Long id, TestlaufDurchfuehrung testlaufDurchfuehrung, Testfall testfall, TestfallErgebnisStatus ergebnis, String kommentar) {
		this.setId(id);
		this.setTestlaufDurchfuehrung(testlaufDurchfuehrung);
		this.setTestfall(testfall);
		this.setErgebnis(ergebnis);
		this.setKommentar(kommentar);
	}

	public String getKommentar() {
		return kommentar;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	public TestfallErgebnisStatus getErgebnis() {
		return ergebnis;
	}

	public void setErgebnis(TestfallErgebnisStatus ergebnis) {
		this.ergebnis = ergebnis;
	}

	public Testfall getTestfall() {
		return testfall;
	}

	public void setTestfall(Testfall testfall) {
		this.testfall = testfall;
	}

	public TestlaufDurchfuehrung getTestlaufDurchfuehrung() {
		return testlaufDurchfuehrung;
	}

	public void setTestlaufDurchfuehrung(TestlaufDurchfuehrung testlaufDurchfuehrung) {
		this.testlaufDurchfuehrung = testlaufDurchfuehrung;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
