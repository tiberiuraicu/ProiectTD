package application.controllers.services;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import application.socket.AuthentificationSocket;
import application.socket.InvitationsSocket;
import javafx.scene.control.ListView;

public class AdminServices {
	InvitationsSocket invitationsSocket = new InvitationsSocket();
	AuthentificationSocket authentificationSocket = new AuthentificationSocket();

	public void getAndShowAllUsersAndEvents(ListView<String> eventsList, ListView<String> usersList)
			throws ClassNotFoundException, IOException, InterruptedException {

		String allUsersAndEventsAsString = authentificationSocket.getAllUsersAndEvents();

		JSONObject json = new JSONObject(allUsersAndEventsAsString);
		JSONArray events = (JSONArray) json.get("events");
		JSONArray users = (JSONArray) json.get("users");

		for (int i = 0; i < events.length(); i++) {
			JSONObject event = (JSONObject) events.get(i);
			eventsList.getItems().add(eventsList.getItems().size(), (String) event.get("eventName"));
		}

		for (int i = 0; i < users.length(); i++) {
			JSONObject user = (JSONObject) users.get(i);
			usersList.getItems().add(usersList.getItems().size(), (String) user.get("userName"));
		}
	}

	public void createEvent(String eventName, String file,String locationCode) throws ClassNotFoundException, IOException, InterruptedException {

		JSONObject eventObject = new JSONObject();
		eventObject.put("type", "createEvent");
		eventObject.put("eventName", eventName);
		eventObject.put("locationCode", locationCode);
		eventObject.put("file", file);
		authentificationSocket.createEvent(eventObject.toString());
	}

	public void inviteUser(ListView<String> eventsList, ListView<String> usersList)
			throws ClassNotFoundException, IOException, InterruptedException {

		String allUsersAndEventsAsString = authentificationSocket.getAllUsersAndEvents();

		JSONObject json = new JSONObject(allUsersAndEventsAsString);
		JSONArray events = (JSONArray) json.get("events");
		JSONArray users = (JSONArray) json.get("users");

		String selectedEvent = eventsList.getSelectionModel().getSelectedItem();
		String selectedUser = usersList.getSelectionModel().getSelectedItem();

		JSONObject invitation = new JSONObject();
		invitation.put("type", "invitation");

		for (int i = 0; i < events.length(); i++) {
			JSONObject event = (JSONObject) events.get(i);
			if (selectedEvent.equals(event.get("eventName"))) {
				invitation.put("event", event);
				break;
			}
		}

		for (int i = 0; i < users.length(); i++) {
			JSONObject user = (JSONObject) users.get(i);
			if (selectedUser.equals(user.get("userName"))) {
				invitation.put("user", user);
				break;
			}
		}

		invitationsSocket.inviteUser(invitation.toString());
	}
}
