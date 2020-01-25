package com.presentation.UIcontrollers.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

import application.socket.InvitationsSocketServices;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class UserControllerServices {

	Map<String, String> invitationsContent = new HashMap<String, String>();
	Map<JSONObject, JSONObject> invitationsObjects = new HashMap<JSONObject, JSONObject>();

	public void waitForInvitations(ListView<String> invitations, String loggedInUsername)
			throws ClassNotFoundException, IOException {

		Thread getMessages = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					InvitationsSocketServices invitationsSocket = new InvitationsSocketServices();

					do {

						String invitation = invitationsSocket.waitForInvitations();

						if (invitation != null) {
							JSONObject invitationObject = new JSONObject(invitation);
							JSONObject userToBeInvited = (JSONObject) invitationObject.get("user");
							JSONObject eventToBeInvitedTo = (JSONObject) invitationObject.get("event");

							if (userToBeInvited.get("userName").equals(loggedInUsername)) {
								invitations.getItems().add(invitations.getItems().size(),
										(String) eventToBeInvitedTo.get("eventName"));
								String fileAsString = eventToBeInvitedTo.get("file").toString();

								String[] byteValues = fileAsString.substring(1, fileAsString.length() - 1).split(",");
								byte[] bytes = new byte[byteValues.length];

								for (int i = 0, len = bytes.length; i < len; i++) {
									bytes[i] = Byte.parseByte(byteValues[i].trim());
								}

								String str = new String(bytes);

								invitationsContent.put((String) eventToBeInvitedTo.get("eventName"), str);
								invitationsObjects.put(userToBeInvited, eventToBeInvitedTo);
							}
						}

						break;

					} while (true);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		getMessages.start();
	}

	public void showInvitationContent(ListView<String> invitations, TextArea invitationContent) {

		Iterator it = invitationsContent.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if (invitations.getSelectionModel().getSelectedItem().equals(pair.getKey())) {
				invitationContent.setText((String) pair.getValue());
			}

		}
	}

	public void confirmSpotOnEvent(String username, String selectedEvent) throws ClassNotFoundException, IOException {
		InvitationsSocketServices invitationsSocket = new InvitationsSocketServices();
		JSONObject confirmSpotOnEventObject = new JSONObject();
		Iterator it = invitationsObjects.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			JSONObject user = (JSONObject) pair.getKey();
			JSONObject event = (JSONObject) pair.getValue();

			if (username.equals(user.get("userName"))&& selectedEvent.equals(event.get("eventName"))) {
				
				confirmSpotOnEventObject.put("type", "confirmSpotOnEvent");
				confirmSpotOnEventObject.put("eventId", event.get("eventId"));
				confirmSpotOnEventObject.put("userId", user.get("userId"));

			}
		}
		invitationsSocket.confirmSpotOnEvent(confirmSpotOnEventObject.toString());
	}

}
