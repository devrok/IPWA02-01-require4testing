package daos;

import java.util.Collection;
import java.util.List;

import entities.Rolle;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@Named
@ApplicationScoped
public class RolleDAO {
	
	public RolleDAO() {
		
	}
	
	public Rolle findById(Long id) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		
		Rolle rolle =  em.find(Rolle.class, id);
		
		em.close();
		
		return rolle;
	}
	
	public Collection<Rolle> getAll() {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		
		List<Rolle> dbItems =  em.createQuery("select r from Rolle r", Rolle.class).getResultList();
		
		em.close();
		
		return dbItems;
	}
	
	public void save(Rolle rolle) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		
		internalSave(rolle, em);
		
		transaction.commit();
		em.close();
	}
	
	public void save(Collection<Rolle> dbItems) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		
		for(Rolle rolle: dbItems) {
			internalSave(rolle, em);
		}
		
		transaction.commit();
		em.close();
	}
	
	private void internalSave(Rolle rolle, EntityManager em) {
		if (rolle.getId() > 0) {
			em.merge(rolle);
		} 
		else {
			em.persist(rolle);
		}
	}
}
