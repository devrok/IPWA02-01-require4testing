package daos;

import java.util.Collection;
import java.util.List;

import entities.Anforderung;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@Named
@ApplicationScoped
public class AnforderungDAO {
	public Anforderung findById(Long id) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		
		Anforderung anforderung =  em.find(Anforderung.class, id);
		
		em.close();
		
		return anforderung;
	}
	
	public Collection<Anforderung> getAll() {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		
		List<Anforderung> dbItems =  em.createQuery("select a from Anforderung a", Anforderung.class).getResultList();
		
		em.close();
		
		return dbItems;
	}
	
	public void save(Anforderung anforderung) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		
		internalSave(anforderung, em);
		
		transaction.commit();
		em.close();
	}
	
	public void save(Collection<Anforderung> dbItems) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		
		for(Anforderung anforderung: dbItems) {
			internalSave(anforderung, em);
		}
		
		transaction.commit();
		em.close();
	}
	
	public void remove(Long id) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();
		
		Anforderung anforderung =  em.find(Anforderung.class, id);
		
		em.remove(anforderung);
		transaction.commit();
		em.close(); 
	}
	
	private void internalSave(Anforderung anforderung, EntityManager em) {
		if (anforderung.getId() > 0) {
			em.merge(anforderung);
		} 
		else {
			em.persist(anforderung);
		}
	}
}
