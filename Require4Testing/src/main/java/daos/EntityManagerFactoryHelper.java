package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerFactoryHelper {
	private final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("require4testingAppPersistenceUnit");

	public static EntityManager createNewEntityManager() {
		return emf.createEntityManager();
	}
}

