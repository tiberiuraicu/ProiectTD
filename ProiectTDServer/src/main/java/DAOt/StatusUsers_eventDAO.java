package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.StatusUsers_event;
import model.User;

public class StatusUsers_eventDAO {

	public void createStatusUsers_event( String id_user, String id_event,String statusOfParticipation) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		StatusUsers_event statusUsers_event = new StatusUsers_event();
	
		statusUsers_event.setIdUser(id_user);
		statusUsers_event.setIdEvent(id_event);
		statusUsers_event.setStatusUserParticipation(statusOfParticipation);

		entitymanager.persist(statusUsers_event);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
	}

	public void deleteStatusUsers_event(int id) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		StatusUsers_event statusUsers_event = entitymanager.find(StatusUsers_event.class, id);
		entitymanager.remove(statusUsers_event);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public StatusUsers_event findStatusUsers_event(int id) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager entitymanager = emfactory.createEntityManager();
		StatusUsers_event statusUsers_event = entitymanager.find(StatusUsers_event.class, id);
		return statusUsers_event;

	}

	public void updateStatusUsers_event(int id, String statusUserParticipation) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		StatusUsers_event statusUsers_event = entitymanager.find(StatusUsers_event.class, id);

		// before update
		System.out.println(statusUsers_event);
		statusUsers_event.setStatusUserParticipation(statusUserParticipation);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
	}
}
