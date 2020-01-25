package com.presentation.UIcontrollers.services;

import java.io.IOException;

import com.presentation.UIcontrollers.UserPageController;

import application.socket.AuthentificationSocketServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AuthentificationControllerServices {
	double xOffset = 0;
	double yOffset = 0;
	String userPagePath = "/com/presentation/scenes/UserPage.fxml";
	String adminPagePath = "/com/presentation/scenes/AdminPage.fxml";

	public void login(String loginUsername, String loginPassword, ActionEvent event)
			throws ClassNotFoundException, IOException, InterruptedException {
		AuthentificationSocketServices authentificationSocket = new AuthentificationSocketServices();
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
		AuthentificationSocketServices authentificationSocket = new AuthentificationSocketServices();
		authentificationSocket.registerUser(registerUsername, registerPassword);
	}

	private void loadNewPage(ActionEvent event, String loginUsername, String pagePath) {

		Parent root;

		try {
			root = FXMLLoader.load(getClass().getResource(pagePath));

			FXMLLoader loader = new FXMLLoader(getClass().getResource(pagePath));
			Parent scene = loader.load();
			if (pagePath.contains("UserPage")) {
				UserPageController controller = loader.<UserPageController>getController();
				controller.setUsername(loginUsername);

			}
			Stage stage = new Stage();
			scene.getStylesheets().add(getClass().getResource("/applicationMainPoint/application.css").toExternalForm());

			scene.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					xOffset = event.getSceneX();
					yOffset = event.getSceneY();
				}
			});

			scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					stage.setX(event.getScreenX() - xOffset);
					stage.setY(event.getScreenY() - yOffset);
				}
			});
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setTitle("User page. Logged user : " + loginUsername);
			stage.setScene(new Scene(scene));
			stage.show();

			// Hide this current window (if this is what you want)
			((Node) (event.getSource())).getScene().getWindow().hide();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
