package application.controllers;

import java.io.IOException;
import application.controllers.services.UserServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

public class UserPageController {

	UserServices userServices = new UserServices();

	@FXML
	private ListView<String> invitations;

	@FXML
	private ToggleButton participation;

	@FXML
	private Button addItemButton;

	@FXML
	private TextArea invitationContent;

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

//	public UserPageController() throws ClassNotFoundException, IOException {
//		userServices.waitForInvitations(invitations);
//	}

//	@FXML
//	public void addItem() throws ClassNotFoundException, IOException {
//		userServices.addItem(invitations);
//	}

	@FXML
	public void showInvitationContent(MouseEvent mouseEvent) {

		userServices.showInvitationContent(invitations, invitationContent);
	}

	@FXML
	public void waitForInvitations() throws ClassNotFoundException, IOException{
		userServices.waitForInvitations(invitations, getUsername());
	}
	
	@FXML
	public void reserveASpot() throws ClassNotFoundException, IOException {
		 userServices.confirmSpotOnEvent(username,invitations.getSelectionModel().getSelectedItem());
	}

}
