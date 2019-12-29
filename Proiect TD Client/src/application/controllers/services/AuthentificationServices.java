package application.controllers.services;

import java.io.IOException;

import application.controllers.UserPageController;
import application.socket.AuthentificationSocket;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AuthentificationServices {

	String userPagePath = "/application/scenes/UserPage.fxml";
	String adminPagePath = "/application/scenes/AdminPage.fxml";

	public void login(String loginUsername, String loginPassword, ActionEvent event)
			throws ClassNotFoundException, IOException, InterruptedException {
		AuthentificationSocket authentificationSocket = new AuthentificationSocket();
		String loginMessage[] = authentificationSocket.loginUser(loginUsername, loginPassword).split(",");
		String loginResult = loginMessage[0];
		String userStatus = loginMessage[1];

		if (loginResult.equals("accepted")) {
			if (userStatus.equals("user"))
				loadNewPage(event, loginUsername, userPagePath);
			if (userStatus.equals("admin"))
				loadNewPage(event, loginUsername, adminPagePath);
		}
	}

	public void register(String registerUsername, String registerPassword)
			throws ClassNotFoundException, IOException, InterruptedException {
		AuthentificationSocket authentificationSocket = new AuthentificationSocket();
		authentificationSocket.registerUser(registerUsername, registerPassword);
	}

	private void loadNewPage(ActionEvent event, String loginUsername, String pagePath) {

		Parent root;

		try {
			root = FXMLLoader.load(getClass().getResource(pagePath));

			FXMLLoader loader = new FXMLLoader(getClass().getResource(pagePath));
			Parent parent = loader.load();
			if (pagePath.contains("UserPage")) {
				UserPageController controller = loader.<UserPageController>getController();
				controller.setUsername(loginUsername);

			}
			Stage stage = new Stage();
			stage.setTitle("User page. Logged user : " + loginUsername);
			stage.setScene(new Scene(parent, 750, 450));
			stage.show();

			// Hide this current window (if this is what you want)
			((Node) (event.getSource())).getScene().getWindow().hide();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
