package services;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import services.Dbutil;
import model.Ysuser;
public class Userdb {
	public static Ysuser getUser(long userID)
	 {
	 EntityManager em = Dbutil.getEntityManager
	("Realspace");
	 Ysuser user = em.find(Ysuser.class, userID);
	 return user;
	 }
	public static void insert(Ysuser ysUser) {
		 EntityManager em = Dbutil.getEntityManager
		("Realspace");
		 EntityTransaction trans = em.getTransaction();
		 try {
		 trans.begin();
		 em.persist(ysUser);
		 trans.commit();
		 } catch (Exception e) {
		 e.printStackTrace();
		 trans.rollback();
		 } finally {
		 em.close();
		 }
	}
	
}
