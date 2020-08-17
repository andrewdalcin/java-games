package Pong;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class App extends Application {
	
	@Override
	public void start(Stage stage) {
		PaneOrganizer organizer = new PaneOrganizer();
		Scene scene = new Scene(organizer.getRoot(), Constants.APP_WIDTH, Constants.APP_HEIGHT);
		System.out.println(scene.getFocusOwner());
		stage.setScene(scene);
		stage.setTitle("Pong");
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}
