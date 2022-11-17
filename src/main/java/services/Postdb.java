package services;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import model.Yspost;
public class Postdb {
	public static void insert(Yspost ysPost) {
		 EntityManager em = Dbutil.getEntityManager
		("Realspace");
		 EntityTransaction trans = em.getTransaction();
		 try {
		 trans.begin();
		 em.persist(ysPost);
		 trans.commit();
		 } catch (Exception e) {
		 System.out.println(e.getMessage());
		 trans.rollback();
		 } finally {
		 em.close();
		 }
		 }
	public static void update(Yspost ysPost) {
		 EntityManager em = Dbutil.getEntityManager
		("Realspace");
		 EntityTransaction trans = em.getTransaction();
		 try {
			 trans.begin();
			 em.merge(ysPost);
			 trans.commit();
			 } catch (Exception e) {
			 trans.rollback();
			 } finally {
			 em.close();
			 }
	}
	public static void delete(Yspost ysPost) {
		 EntityManager em = Dbutil.getEntityManager
		("Realspace");
		 EntityTransaction trans = em.getTransaction();
		 try {
		 trans.begin();
		 em.remove(em.merge(ysPost));
		 trans.commit();
		 } catch (Exception e) {
		 System.out.println(e);
		 trans.rollback();
		 } finally {
		 em.close();
		 }
		 }
	public static List<Yspost> ysPost (){
		 EntityManager em = Dbutil.getEntityManager
		("Realspace");
		 String qString = "select b from Yspost b";
		 List<Yspost> posts = null;
		 try{TypedQuery<Yspost> query = em.createQuery(qString,Yspost.class);
		 posts = query.getResultList();
		 }catch (Exception e){
		 e.printStackTrace();
		 }
		 finally{
		 em.close();
		 }
		 return posts;
		 }
	public static List<Yspost> postsofUser(long userid)
	 {
	 EntityManager em = Dbutil.getEntityManager("Realspace");
	 List<Yspost> userposts = null;
	 String qString = "select b from Yspost b where b.ysuser.ysuserid = :userid";
	 try{
	 TypedQuery<Yspost> query = em.createQuery(qString,Yspost.class);
	 query.setParameter("userid", userid);
	 userposts = query.getResultList();
	 }catch (Exception e){
	 e.printStackTrace();
	 }
	 finally{
	 em.close();
	 }
	 return userposts;
	 }
	public static List<Yspost> postsofUser(String useremail)
	 {
	 EntityManager em = Dbutil.getEntityManager("Realspace");
	 List<Yspost> userposts = null;
	 String qString = "select b from Yspost b " + "where b.ysuser.useremail = :useremail";
	 try{
	 TypedQuery<Yspost> query = em.createQuery(qString,Yspost.class);
	 query.setParameter("useremail",useremail);
	 userposts = query.getResultList();
	 }catch (Exception e){
	 e.printStackTrace();
	 }
	 finally{
	 em.close();
	 }
	 return userposts;
	 }
	
}
