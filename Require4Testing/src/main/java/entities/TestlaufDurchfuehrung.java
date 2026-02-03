package entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class TestlaufDurchfuehrung {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = 0L;
	
	@ManyToOne(optional = false)
	private Testlauf testlauf;
	
	@ManyToOne(optional = false)
	private Benutzer testerin;
	private String kommentar;

	@OneToMany(mappedBy = "testlaufDurchfuehrung", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TestfallDurchfuehrung> testfallDurchfuehrungen = new ArrayList<TestfallDurchfuehrung>();
	
	public TestlaufDurchfuehrung() {

	}

	public TestlaufDurchfuehrung(Long id, Testlauf testlauf, Benutzer testerin, String kommentar, List<TestfallDurchfuehrung> testfallDurchfuehrungen ) {
		this.setId(id);
		this.setTestlauf(testlauf);
		this.setTesterin(testerin);
		this.setKommentar(kommentar);
		this.setTestfallDurchfuehrungen(testfallDurchfuehrungen);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Benutzer getTesterin() {
		return testerin;
	}

	public void setTesterin(Benutzer testerin) {
		this.testerin = testerin;
	}

	public Testlauf getTestlauf() {
		return testlauf;
	}

	public void setTestlauf(Testlauf testlauf) {
		this.testlauf = testlauf;
	}

	public TestlaufDurchfuehrung clone() {
		TestlaufDurchfuehrung clone = new TestlaufDurchfuehrung(this.id, this.testlauf, this.testerin, this.kommentar, this.testfallDurchfuehrungen);
		clone.setTestfallDurchfuehrungen(testfallDurchfuehrungen);
		return clone;
	}

	public String getKommentar() {
		return kommentar;
	}

	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;
	}

	public List<TestfallDurchfuehrung> getTestfallDurchfuehrungen() {
		return testfallDurchfuehrungen;
	}

	public void setTestfallDurchfuehrungen(List<TestfallDurchfuehrung> testfallDurchfuehrungen) {
		this.testfallDurchfuehrungen = testfallDurchfuehrungen;
	}
}
