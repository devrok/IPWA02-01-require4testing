package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.primefaces.model.DualListModel;

import daos.TestlaufDurchfuehrungDAO;
import daos.BenutzerDAO;
import daos.TestfallDAO;
import daos.TestlaufDAO;
import entities.Benutzer;
import entities.Testfall;
import entities.TestfallDurchfuehrung;
import entities.Testlauf;
import entities.TestlaufDurchfuehrung;
import enums.TestfallErgebnisStatus;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ViewScoped
public class TestlaufDurchfuehrungEditController implements Serializable {
	@Inject
	private TestlaufDurchfuehrungDAO itemDAO;

	@Inject
	private BenutzerDAO benutzerDAO;
	
	@Inject
	private TestlaufDAO testlaufDAO;
	
	@Inject
	private TestfallDAO testfallDAO;

	private Long id;
	private TestlaufDurchfuehrung item;
	private Long testlaufId;
	private Collection<Testlauf> testlaeufe;
	private Long testerinId;
	private Collection<Benutzer> testerinnen;
	
	private DualListModel<Testfall> testfallModel;

	public TestlaufDurchfuehrungEditController() {

	}

	@PostConstruct
	public void load() {
		initializeItem();
		initializeTester();
		initializeTestlaeufe();
		initializeTestfaelle();
	}

	private void initializeItem() {
		if (this.id != null) {

			TestlaufDurchfuehrung origin = itemDAO.findById(this.id);
			
			this.item = origin != null ? origin : new TestlaufDurchfuehrung();

			// TODO: bei ALLEN Controllern OHNE clone() arbeiten?
			
//			if (origin == null) {
//				this.item = new TestlaufDurchfuehrung();
//			} else {
//
//				this.item = origin.clone();
//			}

		} else {
			this.item = new TestlaufDurchfuehrung();
		}
	}
	
	private void initializeTestlaeufe() {
		setTestlaeufe(testlaufDAO.getAll());
	}

	private void initializeTester() {
		testerinnen = benutzerDAO.findByRollenName("Tester:in");
	}

	private void initializeTestfaelle() {
	    List<Testfall> source = new ArrayList<>(testfallDAO.getAll());
	    List<Testfall> target = new ArrayList<>();

	    if (item.getTestfallDurchfuehrungen() != null) {
	        for (TestfallDurchfuehrung tfd : item.getTestfallDurchfuehrungen()) {
	            target.add(tfd.getTestfall());
	        }
	        source.removeAll(target);
	    }

	    setTestfallModel(new DualListModel<>(source, target));
	}

	public TestlaufDurchfuehrung getItem() {
		return item;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

//	public String save() {
//		String testerin = item.getTesterin() == null ? "none" : item.getTesterin().getName();
//		System.out.println("Save TestlaufDurchfuehrung. Testerin: " + testerin);
//		
//		String testlauf = item.getTestlauf() == null ? "none" : item.getTestlauf().getTitel();
//		System.out.println("Save TestlaufDurchfuehrung. Testlauf: " + testlauf);
//		
//		// TODO: ACHTUNG - Testfalldurchfuehrungen mit Ergebnis != NICHT_AUSGEFUEHRT sollten bestehen bleiben!!
//	    // Alte Zuordnungen verwerfen
//	    item.getTestfallDurchfuehrungen().clear();
//
//	    // Neue TestfallDurchführungen erzeugen
//	    for (Testfall testfall : getTestfallModel().getTarget()) {
//	        TestfallDurchfuehrung tfd = new TestfallDurchfuehrung();
//	        tfd.setTestlaufDurchfuehrung(item);
//	        tfd.setTestfall(testfall);
//	        tfd.setErgebnis(TestfallErgebnisStatus.NICHT_AUSGEFUEHRT);
//	        item.getTestfallDurchfuehrungen().add(tfd);
//	    }
//		
//		itemDAO.save(item);
//		return "testlaufdurchfuehrungliste";
//	}
	
	public String save() {

	    List<Testfall> selectedTestfaelle = testfallModel.getTarget();

	    // Bestehende TestfallDurchführungen nach Testfall-ID mappen
	    Map<Long, TestfallDurchfuehrung> existing = item.getTestfallDurchfuehrungen()
	        .stream()
	        .collect(Collectors.toMap(
	            tfd -> tfd.getTestfall().getId(),
	            tfd -> tfd
	        ));

	    // Neue Testfälle hinzufügen
	    for (Testfall testfall : selectedTestfaelle) {

	        if (!existing.containsKey(testfall.getId())) {

	            TestfallDurchfuehrung tfd = new TestfallDurchfuehrung();
	            tfd.setTestlaufDurchfuehrung(item);
	            tfd.setTestfall(testfall);
	            tfd.setErgebnis(TestfallErgebnisStatus.NICHT_AUSGEFUEHRT);

	            item.getTestfallDurchfuehrungen().add(tfd);
	        }
	    }

	    // Entfernte Testfälle selektiv löschen
	    Iterator<TestfallDurchfuehrung> it = item.getTestfallDurchfuehrungen().iterator();

	    while (it.hasNext()) {
	        TestfallDurchfuehrung tfd = it.next();

	        boolean stillSelected = selectedTestfaelle.stream()
	            .anyMatch(tf -> tf.getId().equals(tfd.getTestfall().getId()));

	        if (!stillSelected) {

	            if (tfd.getErgebnis() == TestfallErgebnisStatus.NICHT_AUSGEFUEHRT) {
	                it.remove(); // orphanRemoval greift
	            }
	            // else: bewusst behalten
	        }
	    }

	    itemDAO.save(item);
	    return "testlaufdurchfuehrungliste";
	}

	public String cancel() {
		return "testlaufdurchfuehrungliste";
	}

	public Collection<Benutzer> getTesterinnen() {
		return testerinnen;
	}

	public Long getTesterinId() {
		if (item.getTesterin() == null) {
			testerinId = null;
		} else {
			testerinId = item.getTesterin().getId();
		}

		return testerinId;
	}

	public void setTesterinId(Long testerinId) {
		this.testerinId = testerinId;
		if (testerinId == null) {
			this.item.setTesterin(null);
		} else {
			this.item.setTesterin(this.benutzerDAO.findById(testerinId));
		}
	}

	public Collection<Testlauf> getTestlaeufe() {
		return testlaeufe;
	}

	public void setTestlaeufe(Collection<Testlauf> testlaeufe) {
		this.testlaeufe = testlaeufe;
	}

	public Long getTestlaufId() {
		if (item.getTestlauf() == null) {
			testlaufId = null;
		} else {
			testlaufId = item.getTestlauf().getId();
		}

		return testlaufId;
	}

	public void setTestlaufId(Long testlaufId) {
		this.testlaufId = testlaufId;
		if (testlaufId == null) {
			this.item.setTestlauf(null);
		} else {
			this.item.setTestlauf(this.testlaufDAO.findById(testlaufId));
		}
	}

	public DualListModel<Testfall> getTestfallModel() {
		return testfallModel;
	}

	public void setTestfallModel(DualListModel<Testfall> testfallModel) {
		this.testfallModel = testfallModel;
	}
}