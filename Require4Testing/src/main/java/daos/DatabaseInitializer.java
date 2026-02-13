
package daos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import entities.Anforderung;
import entities.Rolle;
import entities.Benutzer;
import entities.Testfall;
import entities.TestfallDurchfuehrung;
import entities.Testlauf;
import entities.TestlaufDurchfuehrung;
import enums.TestfallErgebnisStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@ApplicationScoped
public class DatabaseInitializer {

	@Inject
	private RolleDAO rolleDAO;

	@Inject
	private BenutzerDAO benutzerDAO;

	@Inject
	private AnforderungDAO anforderungDAO;

	@Inject
	private TestfallDAO testfallDAO;

	@Inject
	private TestlaufDAO testlaufDAO;

	@Inject
	private TestlaufDurchfuehrungDAO testlaufDurchfuehrungDAO;

	@Inject
	private TestfallDurchfuehrungDAO testfallDurchfuehrungDAO;

	public DatabaseInitializer() {
	}

	public void initRollen() {
		try {

			Collection<Rolle> dbItems = new ArrayList<Rolle>(rolleDAO.getAll());

			if (dbItems.isEmpty()) {

				dbItems.add(new Rolle(1L, "Requirements Engineer"));
				dbItems.add(new Rolle(2L, "Testmanager:in"));
				dbItems.add(new Rolle(3L, "Testfallersteller:in"));
				dbItems.add(new Rolle(4L, "Tester:in"));

				rolleDAO.save(dbItems);
			}
			System.out.println("Erledigt: 'initRollen'");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Fehler: 'initRollen' - " + e.getMessage());
		}
	}

	public void initBenutzer() {
		try {

			Collection<Benutzer> dbItems = new ArrayList<Benutzer>(benutzerDAO.getAll());

			if (dbItems.isEmpty()) {

				// create Requirements Engineer
				createAndAddBenutzer(dbItems, 1L, "req", "12345678", 1L);

				// create Testmanager:in
				createAndAddBenutzer(dbItems, 2L, "mng", "12345678", 2L);

				// create Testfallersteller:in
				createAndAddBenutzer(dbItems, 3L, "crt", "12345678", 3L);

				// create Tester:in
				createAndAddBenutzer(dbItems, 4L, "tst1", "12345678", 4L);
				createAndAddBenutzer(dbItems, 5L, "tst2", "12345678", 4L);
				createAndAddBenutzer(dbItems, 6L, "tst3", "12345678", 4L);
				createAndAddBenutzer(dbItems, 7L, "tst4", "12345678", 4L);

				benutzerDAO.save(dbItems);
			}
			System.out.println("Erledigt: 'initBenutzer'");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Fehler: 'initBenutzer' - " + e.getMessage());
		}
	}

	private void createAndAddBenutzer(Collection<Benutzer> dbItems, Long benutzerId, String name, String hash,
			Long rolleId) throws Exception {
		Rolle rolle = rolleDAO.findById(rolleId);

		if (rolle != null) {
			dbItems.add(new Benutzer(benutzerId, name, hash, rolle));
		} else {
			throw new Exception("Rolle mit Id '" + rolleId + "' nicht gefunden!");
		}
	}

	public void initAnforderungen() {
		try {

			Collection<Anforderung> dbItems = new ArrayList<Anforderung>(anforderungDAO.getAll());

			if (dbItems.isEmpty()) {
				dbItems.add(new Anforderung(1L, "Titel Anforderung 01",
						"Dies ist eine lange Beschreibung von Anforderung 01, die einfach nicht enden möchte."));
				dbItems.add(new Anforderung(2L, "Titel Anforderung 02",
						"Dies ist eine lange Beschreibung von Anforderung 02, die einfach nicht enden möchte."));
				dbItems.add(new Anforderung(3L, "Titel Anforderung 03",
						"Dies ist eine lange Beschreibung von Anforderung 03, die einfach nicht enden möchte."));
				dbItems.add(new Anforderung(4L, "Titel Anforderung 04",
						"Dies ist eine lange Beschreibung von Anforderung 04, die einfach nicht enden möchte."));

				anforderungDAO.save(dbItems);
			}

			System.out.println("Erledigt: 'initAnforderungn'");
		} catch (Exception e) {
			System.out.println("Fehler: 'initAnforderungn' - " + e.getMessage());
		}

	}

	public void initTestlaeufe() {
		try {

			Collection<Testlauf> dbItems = new ArrayList<Testlauf>(testlaufDAO.getAll());

			if (dbItems.isEmpty()) {
				dbItems.add(new Testlauf(1L, "Titel Testlauf 01",
						"Dies ist eine lange Beschreibung von Testlauf 01, die einfach nicht enden möchte."));
				dbItems.add(new Testlauf(2L, "Titel Testlauf 02",
						"Dies ist eine lange Beschreibung von Testlauf 02, die einfach nicht enden möchte."));
				dbItems.add(new Testlauf(3L, "Titel Testlauf 03",
						"Dies ist eine lange Beschreibung von Testlauf 03, die einfach nicht enden möchte."));
				dbItems.add(new Testlauf(4L, "Titel Testlauf 04",
						"Dies ist eine lange Beschreibung von Testlauf 04, die einfach nicht enden möchte."));

				testlaufDAO.save(dbItems);
			}

			System.out.println("Erledigt: 'initTestlaeufe'");
		} catch (Exception e) {
			System.out.println("Fehler: 'initTestlaeufe' - " + e.getMessage());
		}
	}

	public void initTestfaelle() {
		try {

			Collection<Testfall> dbItems = new ArrayList<Testfall>(testfallDAO.getAll());

			if (dbItems.isEmpty()) {
				dbItems.add(new Testfall(1L, "Titel Testfall 01", "Dies ist eine Beschreibung von Testfall 01."));
				dbItems.add(new Testfall(2L, "Titel Testfall 02", "Dies ist eine Beschreibung von Testfall 02."));
				dbItems.add(new Testfall(3L, "Titel Testfall 03", "Dies ist eine Beschreibung von Testfall 03."));
				dbItems.add(new Testfall(4L, "Titel Testfall 04", "Dies ist eine Beschreibung von Testfall 04."));
				dbItems.add(new Testfall(5L, "Titel Testfall 05", "Dies ist eine Beschreibung von Testfall 05."));
				dbItems.add(new Testfall(6L, "Titel Testfall 06", "Dies ist eine Beschreibung von Testfall 06."));
				dbItems.add(new Testfall(7L, "Titel Testfall 07", "Dies ist eine Beschreibung von Testfall 07."));
				dbItems.add(new Testfall(8L, "Titel Testfall 08", "Dies ist eine Beschreibung von Testfall 08."));

				testfallDAO.save(dbItems);
			}

			System.out.println("Erledigt: 'initTestfaelle'");
		} catch (Exception e) {
			System.out.println("Fehler: 'initTestfaelle' - " + e.getMessage());
		}
	}

	public void initTestlaufDurchfuehrungen() {
		try {
			Collection<TestlaufDurchfuehrung> dbItems = new ArrayList<TestlaufDurchfuehrung>(
					testlaufDurchfuehrungDAO.getAll());

			if (dbItems.isEmpty()) {

				dbItems.add(new TestlaufDurchfuehrung(1L,
						new Testlauf(1L, "Titel Testlauf 01",
								"Dies ist eine lange Beschreibung von Testlauf 01, die einfach nicht enden möchte."),
						new Benutzer(4L, "tst1", "12345678", new Rolle(4L, "Tester:in")), "",
						new ArrayList<TestfallDurchfuehrung>()));

				dbItems.add(new TestlaufDurchfuehrung(2L,
						new Testlauf(2L, "Titel Testlauf 02",
								"Dies ist eine lange Beschreibung von Testlauf 02, die einfach nicht enden möchte."),
						new Benutzer(5L, "tst2", "12345678", new Rolle(4L, "Tester:in")), "",
						new ArrayList<TestfallDurchfuehrung>()));

				testlaufDurchfuehrungDAO.save(dbItems);
			}

			System.out.println("Erledigt: 'initTestlaufDurchfuehrungen'");
		} catch (Exception e) {
			System.out.println("Fehler: 'initTestlaufDurchfuehrungen' - " + e.getMessage());
		}
	}

	public void initTestfallDurchfuehrungen() {
		try {

			Collection<TestlaufDurchfuehrung> dbItems = new ArrayList<TestlaufDurchfuehrung>(
					testlaufDurchfuehrungDAO.getAll());

			for (TestlaufDurchfuehrung testlaufDurchfuehrung : dbItems) {

				if (testlaufDurchfuehrung.getTestfallDurchfuehrungen().isEmpty()) {

					System.out.println("'initTestfallDurchfuehrungen' - testlaufDurchfuehrung: "
							+ testlaufDurchfuehrung.getTestlauf().getTitel());

					testlaufDurchfuehrung.getTestfallDurchfuehrungen().add(createTestfallDurchfuehrung(
							testlaufDurchfuehrung,
							new Testfall(1L, "Titel Testfall 01", "Dies ist eine Beschreibung von Testfall 01.")));
					testlaufDurchfuehrung.getTestfallDurchfuehrungen().add(createTestfallDurchfuehrung(
							testlaufDurchfuehrung,
							new Testfall(2L, "Titel Testfall 02", "Dies ist eine Beschreibung von Testfall 02.")));
					testlaufDurchfuehrung.getTestfallDurchfuehrungen().add(createTestfallDurchfuehrung(
							testlaufDurchfuehrung,
							new Testfall(3L, "Titel Testfall 03", "Dies ist eine Beschreibung von Testfall 03.")));
				}
			}

			testlaufDurchfuehrungDAO.save(dbItems);


			System.out.println("Erledigt: 'initTestfallDurchfuehrungen'");
		} catch (Exception e) {
			System.out.println("Fehler: 'initTestfallDurchfuehrungen' - " + e.getMessage());
		}
	}

	private TestfallDurchfuehrung createTestfallDurchfuehrung(TestlaufDurchfuehrung testlaufDurchfuehrung,
			Testfall testfall) {

		return new TestfallDurchfuehrung(0L, testlaufDurchfuehrung, testfall, TestfallErgebnisStatus.NICHT_AUSGEFUEHRT,
				"mein Kommentar");
	}
}
