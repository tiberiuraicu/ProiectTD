package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.controllers.services.AuthentificationServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// TODO (don't really need to do anything here).
	}
	
	public void loginUser(ActionEvent event) throws ClassNotFoundException, IOException, InterruptedException {
		AuthentificationServices authentificationServices = new AuthentificationServices();
		authentificationServices.login(loginUsername.getText(), loginPassword.getText(), event);	
	}

	public void registerUser(ActionEvent event) throws ClassNotFoundException, IOException, InterruptedException {
		AuthentificationServices authentificationServices = new AuthentificationServices();
		authentificationServices.register(registerUsername.getText(), registerPassword.getText());
	}

	

}
