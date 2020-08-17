package AnimationAlien;

import javafx.animation.Animation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class PaneOrganizer {
	
	private BorderPane root;
	Alien alien = new Alien();
	
	public PaneOrganizer() {
		root = new BorderPane();
		Pane alienPane = alien.getAlien();
		root.setCenter(alienPane);
		//this.setUpButtons();
		this.setUpTimeline();
	}
	
	public void setUpTimeline() {
		KeyFrame kf = new KeyFrame(Duration.seconds(.05), new TimeHandler());
		Timeline timeline = new Timeline(kf);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
	
	public void setUpButtons() {
		Button b1 = new Button("left");
		Button b2 = new Button("right");
		HBox btnBox = new HBox();
		b1.setOnAction(new MoveHandler(true));
		b2.setOnAction(new MoveHandler(false));
		btnBox.getChildren().addAll(b1, b2);
		btnBox.setSpacing(30);
		btnBox.setAlignment(Pos.CENTER);
		root.setBottom(btnBox);
	}
	
	
	private class TimeHandler implements EventHandler<ActionEvent> {
		private double distance;
		
		private TimeHandler() {
			distance = Constants.ALIEN_MOVEMENT;
		}
		
		@Override
		public void handle(ActionEvent evt) {
			alien.setXLoc(alien.getXLoc() + distance);
		}
	}
	
	private class MoveHandler implements EventHandler<ActionEvent> {
		private double distance;
		
		private MoveHandler(boolean isLeft) {
			distance = Constants.ALIEN_MOVEMENT;
			if (isLeft) {
				distance *= -1;
			}
		}
		
		@Override
		public void handle(ActionEvent evt) {
			alien.setXLoc(alien.getXLoc() + distance);
		}
	}
	
	
	public Pane getRoot() {
		return root;
	}
	
}
