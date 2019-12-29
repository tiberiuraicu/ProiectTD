package application.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import application.controllers.services.AdminServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class AdminPageController {

	@FXML
	private Button invite;

	@FXML
	private ListView<String> eventsList;

	@FXML
	private ListView<String> usersList;

	@FXML
	private TextField eventName;

	@FXML
	private TextField locationCode;

	String fileContent="";

	public void showAllUsersAndEvents() throws ClassNotFoundException, IOException, InterruptedException {
		AdminServices adminServices = new AdminServices();
		adminServices.getAndShowAllUsersAndEvents(eventsList, usersList);
	}

	public void inviteUser() throws ClassNotFoundException, IOException, InterruptedException {
		AdminServices adminServices = new AdminServices();
		adminServices.inviteUser(eventsList, usersList);
	}

	public void chooseFile() throws IOException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		File file = fileChooser.showOpenDialog(null);

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null)
				fileContent = fileContent + line + "\r\n";

		} catch (IOException e) {
			e.printStackTrace();
		}
		// bFile = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
	}

	public void createEvent() throws ClassNotFoundException, IOException, InterruptedException {
		AdminServices adminServices = new AdminServices();
		adminServices.createEvent(eventName.getText(), fileContent, locationCode.getText());
	}

}
