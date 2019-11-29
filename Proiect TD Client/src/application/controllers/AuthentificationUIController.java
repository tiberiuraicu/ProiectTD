package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.socket.SocketClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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

	@FXML
	private SplitPane authentificationPanel;
	
	@FXML
	private AnchorPane mainPanel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// TODO (don't really need to do anything here).

	}

	// When user click on myButton
	// this method will be called.
	public void loginUser(ActionEvent event) throws ClassNotFoundException, IOException, InterruptedException {

		socketClient.loginUser(loginUsername.getText(), loginPassword.getText());

		mainPanel.getChildren().clear();
		mainPanel.getChildren().addAll(loadFXML("/application/scenes/UserPage.fxml"));
	}

	public void registerUser(ActionEvent event) throws ClassNotFoundException, IOException, InterruptedException {

		socketClient.registerUser(registerUsername.getText(), registerPassword.getText());

	}
	private Parent loadFXML(String name) {
        try {
            return FXMLLoader.load(getClass().getResource(name));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
