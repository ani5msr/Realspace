package services;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
public class Dbutil {
	public static EntityManager getEntityManager(String s) {		
	 return Persistence.createEntityManagerFactory(s).createEntityManager(); 		
	}
                                                                        
}
