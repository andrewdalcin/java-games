package AnimationAlien;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
	public void start(Stage stage) {
		PaneOrganizer organizer = new PaneOrganizer();
		Scene scene = new Scene(organizer.getRoot(), Constants.APP_WIDTH, Constants.APP_HEIGHT);
		stage.setScene(scene);
		stage.setTitle("Moving Alien!");
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
