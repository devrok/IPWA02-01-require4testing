package daos;

import java.util.Collection;
import java.util.List;

import entities.Testlauf;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@Named
@ApplicationScoped
public class TestlaufDAO {
	public Testlauf findById(Long id) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		
		Testlauf testlauf =  em.find(Testlauf.class, id);
		
		em.close();
		
		return testlauf;
	}
	
	public Collection<Testlauf> getAll() {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		
		List<Testlauf> dbItems =  em.createQuery("select tl from Testlauf tl", Testlauf.class).getResultList();
		
		em.close();
		
		return dbItems;
	}
	
	public void save(Testlauf testlauf) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		
		internalSave(testlauf, em);
		
		transaction.commit();
		em.close();
	}
	
	public void save(Collection<Testlauf> dbItems) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		
		for(Testlauf testlauf: dbItems) {
			internalSave(testlauf, em);
		}
		
		transaction.commit();
		em.close();
	}
	
	public void remove(Long id) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		
		Testlauf testlauf =  em.find(Testlauf.class, id);
		
		em.remove(testlauf);
		transaction.commit();
		em.close(); 
	}
	
	private void internalSave(Testlauf testlauf, EntityManager em) {
		if (testlauf.getId() > 0) {
			em.merge(testlauf);
		} 
		else {
			em.persist(testlauf);
		}
	}
}
