package socket;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import DAOt.EventDAO;
import DAOt.StatusUsers_eventDAO;
import DAOt.UserDAO;
import model.Event;
import model.StatusUsers_event;
import model.User;

public class SocketFunctions {
	UserDAO userDAO = new UserDAO();
	EventDAO eventDAO = new EventDAO();
	StatusUsers_eventDAO statusUsers_eventDAO = new StatusUsers_eventDAO();

	public String processMessage(String message) {
	
		if (message.contains("createEvent"))
			return createEvent(message);
		if (message.split(",")[1].equals("getAll"))
			return getAllUsersAndEvents();
		if (message.split(",")[1].equals("getAllEventsForUser"))
			return getAllEventsForUser(message.split(",")[2]);
		if (message.split(",")[2].equals("register"))
			return register(message.split(",")[0], message.split(",")[1]);
		else if (message.split(",")[2].equals("login"))
			return login(message.split(",")[0], message.split(",")[1]);

		else
			return null;
	}

	private String getAllEventsForUser(String username) {
		
		JSONArray eventArray = new JSONArray();
		JSONObject userEvents = new JSONObject();
		JSONObject userAsJson = new JSONObject();

		List<StatusUsers_event> eventsForUser = statusUsers_eventDAO
				.findEventForUser(userDAO.findUserByUsername(username).getIdUsers());

		for (StatusUsers_event statusUsers_event : eventsForUser)
			for (Event event : eventDAO.findAllEvents())
				if (statusUsers_event.getIdEvent().equals(Integer.toString(event.getIdevent())))
					eventArray.put(new JSONObject().put("eventId", event.getIdevent()).put("eventName", event.getName())
							.put("eventStatus", statusUsers_event.getStatusUserParticipation())
							.put("file", event.getFile()));

		User user = userDAO.findUserByUsername(username);
		
		userAsJson.put("userId", user.getIdUsers()).put("userName", user.getUsername());

		userEvents.put("events", eventArray);
		userEvents.put("user", userAsJson);

		return userEvents.toString();

	}

	public String register(String username, String password) {
		userDAO.createUser(username, password, "client");
		return "user registered";
	}

	public String login(String username, String password) {
		User user = userDAO.findUserByUsername(username);
		if (user == null)
			return "nu exista";
		else if (user.getPassword().equals(password))
			return "accepted," + user.getRole();
		else
			return "contul nu exista sau parola este gresita";
	}

	public String getAllUsersAndEvents() {

		JSONArray eventArray = new JSONArray();
		JSONArray usersArray = new JSONArray();
		JSONObject usersAndEvents = new JSONObject();

		for (Event event : eventDAO.findAllEvents())

			eventArray.put(new JSONObject().put("eventId", event.getIdevent()).put("eventName", event.getName()));

		for (User user : userDAO.findAllUsers())

			usersArray.put(new JSONObject().put("userId", user.getIdUsers()).put("userName", user.getUsername())
					.put("userRole", user.getRole()));

		usersAndEvents.put("events", eventArray);
		usersAndEvents.put("users", usersArray);
		return usersAndEvents.toString();
	}

	public String processInviteMessage(String inviteMessage) {

		JSONObject receivedObject = new JSONObject(inviteMessage);

		if (receivedObject.get("type").equals("invitation")) {

			JSONObject eventFromClient = (JSONObject) receivedObject.get("event");
			JSONObject userFromClient = (JSONObject) receivedObject.get("user");

			if (statusUsers_eventDAO.findStatusUsers_event(userFromClient.getInt("userId"),
					eventFromClient.getInt("eventId")) != null) {
				return "user already invited";
			} else {
				Event event = eventDAO.findEvent(eventFromClient.getInt("eventId"));

				JSONObject invitationObject = new JSONObject();
				invitationObject.put("user", userFromClient);
				eventFromClient.put("file", event.getFile());
				invitationObject.put("event", eventFromClient);
				statusUsers_eventDAO.createStatusUsers_event(userFromClient.get("userId").toString(),
						eventFromClient.get("eventId").toString(), "pending");

				return invitationObject.toString();
			}
		}
		if (receivedObject.get("type").equals("confirmSpotOnEvent"))
			return confirmSpotOnEvent(receivedObject.get("userId").toString(), receivedObject.get("eventId").toString(),
					"confirmed");
		return inviteMessage;

	}

	public String createEvent(String eventJson) {
		JSONObject eventToBeCreated = new JSONObject(eventJson);

		eventDAO.createEvent((String) eventToBeCreated.get("locationCode"), (String) eventToBeCreated.get("eventName"),
				eventToBeCreated.get("file").toString().getBytes());
		return "da";
	}

	private String confirmSpotOnEvent(String userId, String eventId, String statusOfConfirmation) {
		statusUsers_eventDAO.updateStatusUsers_event(userId, eventId, statusOfConfirmation);
		return null;
	}
}
