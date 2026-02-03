package daos;

import java.util.Collection;
import java.util.List;

import entities.TestfallDurchfuehrung;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@Named
@ApplicationScoped
public class TestfallDurchfuehrungDAO {
	public TestfallDurchfuehrung findById(Long id) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		
		TestfallDurchfuehrung testfallDurchfuehrung =  em.find(TestfallDurchfuehrung.class, id);
		
		em.close();
		
		return testfallDurchfuehrung;
	}
	
	public Collection<TestfallDurchfuehrung> getAll() {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		
		List<TestfallDurchfuehrung> dbItems =  em.createQuery("select tfd from TestfallDurchfuehrung tfd", TestfallDurchfuehrung.class).getResultList();
		
		em.close();
		
		return dbItems;
	}
	
	public Collection<TestfallDurchfuehrung> getAllByTester(Long testerId) {
		
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		
	    List<TestfallDurchfuehrung> dbItems = em.createQuery(
	                """
	                SELECT tfd
	                FROM TestfallDurchfuehrung tfd
	                WHERE tfd.testlaufDurchfuehrung.testerin.id = :testerId
	                """,
	                TestfallDurchfuehrung.class
	            )
	            .setParameter("testerId", testerId)
	            .getResultList();
		
		em.close();
		
		return dbItems;
	}
	
	public void save(TestfallDurchfuehrung testfallDurchfuehrung) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		
		internalSave(testfallDurchfuehrung, em);
		
		transaction.commit();
		em.close();
	}
	
	public void save(Collection<TestfallDurchfuehrung> dbItems) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		
		for(TestfallDurchfuehrung testfallDurchfuehrung: dbItems) {
			internalSave(testfallDurchfuehrung, em);
		}
		
		transaction.commit();
		em.close();
	}
	
	public void remove(Long id) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		
		TestfallDurchfuehrung testfallDurchfuehrung =  em.find(TestfallDurchfuehrung.class, id);
		
		em.remove(testfallDurchfuehrung);
		transaction.commit();
		em.close(); 
	}
	
	private void internalSave(TestfallDurchfuehrung testfallDurchfuehrung, EntityManager em) {
		if (testfallDurchfuehrung.getId() > 0) {
			em.merge(testfallDurchfuehrung);
		} 
		else {
			em.persist(testfallDurchfuehrung);
		}
	}
}