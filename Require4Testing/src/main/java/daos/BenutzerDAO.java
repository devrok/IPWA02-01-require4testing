package daos;

import java.util.Collection;
import java.util.List;

import entities.Benutzer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

@Named
@ApplicationScoped
public class BenutzerDAO {
	public Benutzer findById(Long id) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		
		Benutzer benutzer =  em.find(Benutzer.class, id);
		
		em.close();
		
		return benutzer;
	}
	
	public Collection<Benutzer> getAll() {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		
		List<Benutzer> dbItems =  em.createQuery("select b from Benutzer b", Benutzer.class).getResultList();
		
		em.close();
		
		return dbItems;
	}
	
	public Collection<Benutzer> findByRollenName(String rollenName) {
	    EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();

	    List<Benutzer> result = em.createQuery(
	        "SELECT b FROM Benutzer b WHERE b.rolle.bezeichnung = :rollenName",
	        Benutzer.class
	    )
	    .setParameter("rollenName", rollenName)
	    .getResultList();

	    em.close();
	    return result;
	}
	
	public void save(Benutzer benutzer) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		
		internalSave(benutzer, em);
		
		transaction.commit();
		em.close();
	}
	
	public void save(Collection<Benutzer> dbItems) {
		EntityManager em = EntityManagerFactoryHelper.createNewEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		
		for(Benutzer benutzer: dbItems) {
			internalSave(benutzer, em);
		}
		
		transaction.commit();
		em.close();
	}
	
	private void internalSave(Benutzer benutzer, EntityManager em) {
		if (benutzer.getId() > 0) {
			em.merge(benutzer);
		} 
		else {
			em.persist(benutzer);
		}
	}
}
