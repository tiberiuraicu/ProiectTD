package DAOt;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.StatusUsers_event;
import model.User;

public class StatusUsers_eventDAO {

	public void createStatusUsers_event(String idUser, String idEvent, String statusOfParticipation) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		StatusUsers_event statusUsers_event = new StatusUsers_event();
		statusUsers_event.setIdUser(idUser);
		statusUsers_event.setIdEvent(idEvent);
		statusUsers_event.setStatusUserParticipation(statusOfParticipation);
		
		entitymanager.persist(statusUsers_event);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();		
	}
	public void updateStatusUsers_event(String idUser, String idEvent, String statusOfParticipation) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		StatusUsers_event statusUsers_event = entitymanager.createQuery("Select a From StatusUsers_event a where a.idUser=:arg0 and a.idEvent=:arg1", StatusUsers_event.class)
				.setParameter("arg0", idUser).setParameter("arg1", idEvent).getSingleResult();

		// before update
		//System.out.println(user);
		statusUsers_event.setStatusUserParticipation(statusOfParticipation);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
	}
}
