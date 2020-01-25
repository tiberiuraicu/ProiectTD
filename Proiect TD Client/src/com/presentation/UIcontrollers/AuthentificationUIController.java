package com.presentation.UIcontrollers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.presentation.UIcontrollers.services.AuthentificationControllerServices;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AuthentificationUIController implements Initializable {
	

	@FXML
	private Button loginButton;

	@FXML
	private Button registerButton;

	@FXML
	private TextField loginUsername;

	@FXML
	private PasswordField loginPassword;

	@FXML
	private TextField registerUsername;

	@FXML
	private PasswordField registerPassword;

	@FXML
	private SplitPane authentificationPanel;

	@FXML
	private AnchorPane mainPanel;
	
	@FXML 
	private Button closeButton;

	
	@FXML
	private void closeButtonAction(){
	    // get a handle to the stage
	    Stage stage = (Stage) closeButton.getScene().getWindow();
	    // do what you have to do
	    stage.close();
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// TODO (don't really need to do anything here).
	}
	
	public void loginUser(ActionEvent event) throws ClassNotFoundException, IOException, InterruptedException {
		AuthentificationControllerServices authentificationServices = new AuthentificationControllerServices();
		authentificationServices.login(loginUsername.getText(), loginPassword.getText(), event);	
	}

	public void registerUser(ActionEvent event) throws ClassNotFoundException, IOException, InterruptedException {
		AuthentificationControllerServices authentificationServices = new AuthentificationControllerServices();
		authentificationServices.register(registerUsername.getText(), registerPassword.getText());
	}

	

}
