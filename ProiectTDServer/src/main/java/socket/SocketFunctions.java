package socket;

import DAO.UserDAO;
import model.User;

public class SocketFunctions {
	UserDAO userDAO = new UserDAO();

	public String processMessage(String message) {
		System.out.println(message.split(",")[2]);
		if (message.split(",")[2].equals("register"))
			return register(message.split(",")[0], message.split(",")[1]);
		else if (message.split(",")[2].equals("login"))
			return login(message.split(",")[0], message.split(",")[1]);
		else
			return null;
	}

	public String register(String username, String password) {
		userDAO.createUser(username, password);
		return "user registered";
	}
	
	public String login(String username, String password) {
		User user = userDAO.findUserByUsername(username);
		if(user == null)
			return "nu exista";
		else if(user.getPassword().equals(password))
			return "accepted";
		else 
			return "contul nu exista sau parola este gresita";
	}
}
