package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.socket.SocketClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthentificationUIController implements Initializable {
	SocketClient socketClient = new SocketClient();
	
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// TODO (don't really need to do anything here).

	}

	// When user click on myButton
	// this method will be called.
	public void loginUser(ActionEvent event) throws ClassNotFoundException, IOException, InterruptedException {

		
		socketClient.login(loginUsername.getText(), loginPassword.getText());

	}
	
	public void registerUser(ActionEvent event) throws ClassNotFoundException, IOException, InterruptedException {

		socketClient.login(registerUsername.getText(), registerPassword.getText());

	}

}
