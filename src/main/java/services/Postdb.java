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
}
