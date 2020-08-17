package TicTacToe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
	@Override
	public void start(Stage stage) {
		PaneOrganizer organizer = new PaneOrganizer();
		Scene scene = new Scene(organizer.getRoot(), Constants.APP_WIDTH, Constants.APP_HEIGHT);
		X x = new X();
		scene.setOnMouseClicked(x.new MouseClicker());
		stage.setScene(scene);
		stage.setTitle("TicTacToe");
		stage.show();;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
