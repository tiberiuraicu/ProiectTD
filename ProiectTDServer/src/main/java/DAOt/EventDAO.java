package DAOt;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Event;
import model.User;

public class EventDAO {

	public void createEvent( String codLocatie, String name, byte[] file) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Event event = new Event();
	
		event.setCodLocatie(codLocatie);;
		event.setName(name);;
		event.setFile(file);

		entitymanager.persist(event);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
	}

	public void deleteEvent(String id) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		Event event = entitymanager.find(Event.class, id);
		entitymanager.remove(event);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public Event findEvent(int id) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager entitymanager = emfactory.createEntityManager();
		Event event = entitymanager.find(Event.class, id);
		return event;

	}

	public void updateEvent(String id, String codLocatie) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		Event event = entitymanager.find(Event.class, id);

		// before update
		System.out.println(event);
		event.setCodLocatie(codLocatie);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
	}
	
	public List<Event> findAllEvents(){
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		
		List<Event> users = (List<Event>)entitymanager.createQuery("SELECT c FROM Event c")
				 .getResultList(); 
		return users;
		
	}
}
