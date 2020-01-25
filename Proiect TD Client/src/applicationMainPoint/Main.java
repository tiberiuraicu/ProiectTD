package applicationMainPoint;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;

public class Main extends Application {
	
	double windowXOffset = 0;
	double windowYOffset = 0;
	
	String AUTHENTIFICATION_SCENE_URL = "/com/presentation/scenes/Authentification.fxml";
	String STYLE_FILE_URL = "application.css";

	
	public static void main(String[] args) {

		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {

		showAuthentificationPage(primaryStage);
	}

	public void showAuthentificationPage(Stage primaryStage) {

		try {
			
			//remove window upper border
			primaryStage.initStyle(StageStyle.UNDECORATED);
			
			// Read file fxml and draw interface.
			Parent root = FXMLLoader.load(getClass().getResource(AUTHENTIFICATION_SCENE_URL));
			Scene scene = new Scene(root);
			
			//read the style file
			scene.getStylesheets().add(getClass().getResource(STYLE_FILE_URL).toExternalForm());
			
			//make page draggable
			makePageDraggable(root, primaryStage);
			
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void makePageDraggable(Parent root, Stage primaryStage){

		root.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				windowXOffset = event.getSceneX();
				windowYOffset = event.getSceneY();
			}
		});

		root.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				primaryStage.setX(event.getScreenX() - windowXOffset);
				primaryStage.setY(event.getScreenY() - windowYOffset);
			}
		});
	}
	
}
