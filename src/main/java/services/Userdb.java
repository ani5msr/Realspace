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
	public static Ysuser getUserByEmail(String email)
	 {
	 EntityManager em = Dbutil.getEntityManager("Realspace");
	 String qString = "Select u from Ysuser u "+ "where u.useremail=:useremail";
	 TypedQuery<Ysuser> q = em.createQuery(qString,Ysuser.class);
	 q.setParameter("useremail", email);
	 Ysuser user = null;
	 try {
		 System.out.println("Getting single user");
		 user = q.getSingleResult();
		 System.out.println(user.getUsername());
		 }catch (NoResultException e){
		 System.out.println(e);
		 }finally{
		 em.close();
		 }
		 return user;
	 }
	public static boolean isValidUser(String userEmail,
			String userPassword)
			 {EntityManager em = Dbutil.getEntityManager("Realspace");
			  String qString = "Select count(b.ysuserid) from Ysuser b " + "where b.useremail = :useremail and b.userpassword = :userpass";
			  TypedQuery<Long> q = em.createQuery(qString,Long.class);
			  boolean result = false;
			  q.setParameter("useremail", userEmail);
			  q.setParameter("userpass", userPassword);
			  try{
			  long userId = q.getSingleResult();
			  if (userId > 0)
			  {
			  result = true;
			  }
			  }catch (Exception e){
			  result = false;
			  }
			  finally{
			  em.close();
			  }
			  return result;
			  }
}
