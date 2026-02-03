package daos;

import java.util.Collection;
import java.util.List;

import entities.Testfall;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@Named
@ApplicationScoped
public class TestfallDAO {
	public Testfall findById(Long id) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		
		Testfall testfall =  em.find(Testfall.class, id);
		
		em.close();
		
		return testfall;
	}
	
	public Collection<Testfall> getAll() {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		
		List<Testfall> dbItems =  em.createQuery("select tf from Testfall tf", Testfall.class).getResultList();
		
		em.close();
		
		return dbItems;
	}
	
	public void save(Testfall testfall) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		
		internalSave(testfall, em);
		
		transaction.commit();
		em.close();
	}
	
	public void save(Collection<Testfall> dbItems) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		
		for(Testfall testfall: dbItems) {
			internalSave(testfall, em);
		}
		
		transaction.commit();
		em.close();
	}
	
	public void remove(Long id) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		
		Testfall testfall =  em.find(Testfall.class, id);
		
		em.remove(testfall);
		transaction.commit();
		em.close(); 
	}
	
	private void internalSave(Testfall testfall, EntityManager em) {
		if (testfall.getId() > 0) {
			em.merge(testfall);
		} 
		else {
			em.persist(testfall);
		}
	}
}