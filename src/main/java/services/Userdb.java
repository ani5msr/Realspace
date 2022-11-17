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
	public static String getGravatarURL(String email,
			Integer size){
			 StringBuilder url = new StringBuilder();
			 url.append("http://www.gravatar.com/avatar/");
			 url.append(MD5Util.md5Hex(email));
			 url.append("?s=" + size.toString());
			 return url.toString();
			 }
	public static void update(Ysuser ysUser) {
		 EntityManager em = Dbutil.getEntityManager("Realspace");
		 EntityTransaction trans = em.getTransaction();
		 try {
		 trans.begin();
		 em.merge(ysUser);
		 trans.commit();
		 } catch (Exception e) {
		 System.out.println(e);
		 trans.rollback();
		 } finally {
		 em.close();
		 }
	}
	public static void delete(Ysuser ysUser) {
		 EntityManager em = Dbutil.getEntityManager("Realspace");
		 EntityTransaction trans = em.getTransaction();
		 try {
		 trans.begin();
		 em.remove(em.merge(ysUser));
		 trans.commit();
		 } catch (Exception e) {
			 System.out.println(e);
			 trans.rollback();
			 } finally {
			 em.close();
			 }
	}
}
