package DAOt;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
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
		StatusUsers_event statusUsers_event = entitymanager
				.createQuery("Select a From StatusUsers_event a where a.idUser=:arg0 and a.idEvent=:arg1",
						StatusUsers_event.class)
				.setParameter("arg0", idUser).setParameter("arg1", idEvent).getSingleResult();

		// before update
		// System.out.println(user);
		statusUsers_event.setStatusUserParticipation(statusOfParticipation);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
	}

	public StatusUsers_event findStatusUsers_event(int idUser, int idEvent) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		StatusUsers_event statusUsers_event;
		try {
			statusUsers_event = entitymanager
					.createQuery("Select a From StatusUsers_event a where a.idUser=:arg0 and a.idEvent=:arg1",
							StatusUsers_event.class)
					.setParameter("arg0", Integer.toString(idUser)).setParameter("arg1", Integer.toString(idEvent))
					.getSingleResult();
		} catch (NoResultException noResultException) {
			entitymanager.close();
			emfactory.close();
			return null;
		}
		entitymanager.close();
		emfactory.close();
		return statusUsers_event;
	}

	public List<StatusUsers_event> findEventForUser(int idUser) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		List<StatusUsers_event> eventsForUser;
		try {
			eventsForUser = entitymanager
					.createQuery("Select a From StatusUsers_event a where a.idUser=:arg0", StatusUsers_event.class)
					.setParameter("arg0", Integer.toString(idUser)).getResultList();
		} catch (NoResultException noResultException) {
			entitymanager.close();
			emfactory.close();
			return null;
		}
		entitymanager.close();
		emfactory.close();
		return eventsForUser;
	}
}
