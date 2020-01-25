package com.presentation.UIcontrollers;

import java.io.IOException;

import com.presentation.UIcontrollers.services.UserControllerServices;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UserPageController {

	UserControllerServices userServices = new UserControllerServices();

	@FXML
	private ListView<String> invitations;

	@FXML
	private ToggleButton participation;

	@FXML
	private Button addItemButton;

	@FXML
	private TextArea invitationContent;

	@FXML 
	private Button closeButton;

	@FXML
	private void closeButtonAction(){
	    // get a handle to the stage
	    Stage stage = (Stage) closeButton.getScene().getWindow();
	    // do what you have to do
	    stage.close();
	}
	
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
