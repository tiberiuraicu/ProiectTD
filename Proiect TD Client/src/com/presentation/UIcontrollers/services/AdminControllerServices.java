package com.presentation.UIcontrollers.services;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

import application.socket.AdminSocketServices;
import application.socket.AuthentificationSocketServices;
import application.socket.InvitationsSocketServices;
import javafx.scene.control.ListView;

public class AdminControllerServices {

	InvitationsSocketServices invitationsSocket = new InvitationsSocketServices();

	AdminSocketServices adminSocketServices = new AdminSocketServices();

	public void getAndShowAllUsersAndEvents(ListView<String> eventsList, ListView<String> usersList)
			throws ClassNotFoundException, IOException, InterruptedException {

		String allUsersAndEventsAsString = adminSocketServices.getAllUsersAndEvents();
		JSONObject json = new JSONObject(allUsersAndEventsAsString);
		JSONArray events = (JSONArray) json.get("events");
		JSONArray users = (JSONArray) json.get("users");
		if (eventsList.getItems().size() == 0 && usersList.getItems().size() == 0) {
			for (int i = 0; i < events.length(); i++) {
				JSONObject event = (JSONObject) events.get(i);
				eventsList.getItems().add(eventsList.getItems().size(), (String) event.get("eventName"));
			}

			for (int i = 0; i < users.length(); i++) {
				JSONObject user = (JSONObject) users.get(i);
				if (user.get("userRole").equals("user"))
					usersList.getItems().add(usersList.getItems().size(), (String) user.get("userName"));
			}
		}
	}

	public void createEvent(String eventName, String file, String locationCode)
			throws ClassNotFoundException, IOException, InterruptedException {

		JSONObject eventObject = new JSONObject();
		eventObject.put("type", "createEvent");
		eventObject.put("eventName", eventName);
		eventObject.put("locationCode", locationCode);
		eventObject.put("file", file);
		adminSocketServices.createEvent(eventObject.toString());
	}

	public void inviteUser(ListView<String> eventsList, ListView<String> usersList)
			throws ClassNotFoundException, IOException, InterruptedException {

		String allUsersAndEventsAsString = adminSocketServices.getAllUsersAndEvents();

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
