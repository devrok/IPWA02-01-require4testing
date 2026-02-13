package daos;

import java.util.Collection;
import java.util.List;

import entities.TestlaufDurchfuehrung;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@Named
@ApplicationScoped
public class TestlaufDurchfuehrungDAO {
	public TestlaufDurchfuehrung findById(Long id) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		
		TestlaufDurchfuehrung testlaufDurchfuehrung = em.createQuery(
		        """
		        SELECT tld
		        FROM TestlaufDurchfuehrung tld
		        LEFT JOIN FETCH tld.testfallDurchfuehrungen
		        WHERE tld.id = :id
		        """,
		        TestlaufDurchfuehrung.class
		    )
		    .setParameter("id", id)
		    .getSingleResult();
		
		em.close();
			
		return testlaufDurchfuehrung;
	}

	public Collection<TestlaufDurchfuehrung> getAll() {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();

		List<TestlaufDurchfuehrung> dbItems = em.createQuery(
				"""
				SELECT distinct tld
				FROM TestlaufDurchfuehrung tld
				LEFT JOIN FETCH tld.testfallDurchfuehrungen
				""", 
				TestlaufDurchfuehrung.class)
				.getResultList();

		em.close();

		System.out.println("Debug: 'TestlaufDurchfuehrungDAO.getAll()' - Count: " + dbItems.size());
		
		return dbItems;
	}

	public void save(TestlaufDurchfuehrung testlaufDurchfuehrung) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();

		internalSave(testlaufDurchfuehrung, em);

		transaction.commit();
		em.close();
	}

	public void save(Collection<TestlaufDurchfuehrung> dbItems) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();

		for (TestlaufDurchfuehrung testlaufDurchfuehrung : dbItems) {
			internalSave(testlaufDurchfuehrung, em);
		}

		transaction.commit();
		em.close();
	}

	public void remove(Long id) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();

		TestlaufDurchfuehrung testlaufDurchfuehrung = em.find(TestlaufDurchfuehrung.class, id);

		em.remove(testlaufDurchfuehrung);
		transaction.commit();
		em.close();
	}

	private void internalSave(TestlaufDurchfuehrung testlaufDurchfuehrung, EntityManager em) {
		if (testlaufDurchfuehrung.getId() > 0) {
			em.merge(testlaufDurchfuehrung);
		} else {
			em.persist(testlaufDurchfuehrung);
		}
	}
}
