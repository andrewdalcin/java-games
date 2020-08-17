package Tetris;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class App extends Application {
	
	@Override
	public void start(Stage stage) {
		PaneOrganizer organizer = new PaneOrganizer();
		Scene scene = new Scene(organizer.getRoot(), Constants.APP_WIDTH, Constants.APP_HEIGHT);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Tetris");
		stage.show();
		organizer.getRoot().requestFocus();
		organizer.getRoot().setFocusTraversable(true);
	}
	
	public static void main(String []args) {
		launch(args);
	}
}