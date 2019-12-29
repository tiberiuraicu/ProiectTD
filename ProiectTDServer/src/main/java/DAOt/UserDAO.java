package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.User;

public class UserDAO {

	public void createUser(String username, String password) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);

		entitymanager.persist(user);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
	}

	public void deleteUser(String username) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();

		User user = entitymanager.find(User.class, username);
		entitymanager.remove(user);
		entitymanager.getTransaction().commit();
		entitymanager.close();
		emfactory.close();
	}

	public User findUser(String username) {

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager entitymanager = emfactory.createEntityManager();
		User user = entitymanager.find(User.class, username);
		return user;

	}

	public User findUserByUsername(String username) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");
		EntityManager entitymanager = emfactory.createEntityManager();
		try {
			User user = entitymanager.createQuery("Select a From User a where a.username=:arg0", User.class)
					.setParameter("arg0", username).getSingleResult();
			return user;
		} catch (Exception e) {
			return null;
		}

	}

	public void updateUser(String username, String password) {
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Eclipselink_JPA");

		EntityManager entitymanager = emfactory.createEntityManager();
		entitymanager.getTransaction().begin();
		User user = entitymanager.find(User.class, username);

		// before update
		System.out.println(user);
		user.setPassword(password);
		entitymanager.getTransaction().commit();

		entitymanager.close();
		emfactory.close();
	}
}
