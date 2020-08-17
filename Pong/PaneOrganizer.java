package Pong;

import Border.QuadBorder;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PaneOrganizer {
	private Pane root;
	private Rectangle rec1;
	private Rectangle rec2;
	private Ellipse ball;
	private Borders gameBorders;
	private Timeline timelineRec1;
	private boolean isRec2Up;
	private QuadBorder appBorder;
	private Button b;
	private double speed = Constants.REC2_SPEED;
	private Timeline timelineRec2;
	
	public PaneOrganizer() {
		isRec2Up = false;
		root = new Pane();
		this.setUpRectangles();
		this.setUpBall();
		//this.setUpButtons();
		this.setUpRec2Timeline();
		gameBorders = new Borders(rec1, rec2);
		root.getChildren().addAll(rec1, rec2, ball);
	}
	
	public Pane getRoot() {
		return root;
	}
	
	public Rectangle getRec1() {
		return rec1;
	}
	
	public Rectangle getRec2() {
		return rec2;
	}
	
	public void setUpRectangles() {
		rec1 = new Rectangle(Constants.REC_WIDTH, Constants.REC_HEIGHT);
		rec1.setFocusTraversable(true);
		rec1.requestFocus();
		rec1.setOnKeyPressed(new KeyHandlerPress());
		rec1.setOnKeyReleased(new KeyHandlerRelease());
		rec1.setFill(Color.BLUE);
		rec1.setX(Constants.APP_WIDTH / 8);
		rec1.setY(Constants.APP_HEIGHT / 2);
		rec2 = new Rectangle(Constants.REC_WIDTH, Constants.REC_HEIGHT);
		rec2.setFill(Color.BLUE);
		rec2.setX((6.8 * Constants.APP_WIDTH) / 8.0);
		rec2.setY(Constants.APP_HEIGHT / 2);
	}
	
	public void setUpBall() {
		ball = new Ellipse(Constants.X_RAD, Constants.Y_RAD);
		ball.setStroke(Color.RED);
		ball.setFill(Color.RED);
		ball.setCenterX(Constants.APP_WIDTH / 2);
		ball.setCenterY(Constants.APP_HEIGHT / 2 + rec1.getHeight() / 2);
	}
	
	public void setUpButtons() {
		b = new Button("block speed");
		b.setTranslateX(Constants.APP_WIDTH / 2);
		b.setFocusTraversable(true);
		b.setOnMouseClicked(new MouseHandler());
	}
	
	public void setUpRec1Timeline(boolean isUp) {
		KeyFrame kf = new KeyFrame(Duration.seconds(.05), new TimeHandler(isUp));
		timelineRec1 = new Timeline(kf);
		timelineRec1.setCycleCount(Animation.INDEFINITE);
		timelineRec1.play();
	}
	
	public void setUpRec2Timeline() {
		KeyFrame kf = new KeyFrame(Duration.seconds(speed), new TimeHandlerRec2(isRec2Up));
		timelineRec2 = new Timeline(kf);
		timelineRec2.setCycleCount(Animation.INDEFINITE);
		timelineRec2.play();
	}
	
	private class MouseHandler implements EventHandler<MouseEvent> {
		
		private MouseHandler() {}
		
		@Override
		public void handle(MouseEvent evt) {
			System.out.println("this");
			timelineRec2.stop();
			speed += .01;
			setUpRec2Timeline();
		}
	}
	
	private class KeyHandlerPress implements EventHandler<KeyEvent> {
		
		private KeyHandlerPress() {}
		
		@Override
		public void handle(KeyEvent evt) {
			System.out.println("this");
			if (evt.getCode() == KeyCode.UP) {
				setUpRec1Timeline(true);
			} else if (evt.getCode() == KeyCode.DOWN) {
				setUpRec1Timeline(false);
			}
		}
		
		
	}
	
	private class KeyHandlerRelease implements EventHandler<KeyEvent> {
		
		private KeyHandlerRelease() {}
		
		@Override
		public void handle(KeyEvent evt) {
			System.out.println("that");
			timelineRec1.stop();
		}
	}
	
	private class TimeHandlerRec2 implements EventHandler<ActionEvent> {
		private double distance;
		
		private TimeHandlerRec2(boolean isRec2Up) {
			distance = Constants.REC_MOVEMENT;
		}
		
		@Override
		public void handle(ActionEvent evt) {
			if (isRec2Up) {
				distance *= -1;
				isRec2Up = !isRec2Up;
			}
			//determines movement of second rectangle
			if (isRecMove(rec2, distance)) {
				rec2.setY(rec2.getY() + distance);
			} else {
				//determines newDistance based on close to top or bottom
				double newDistance = Math.min(rec2.getY(), Constants.APP_HEIGHT - (rec2.getY() + rec2.getHeight()));
				if (distance < 0) {
					newDistance *= -1;
				}
				System.out.println(newDistance);
				rec2.setY(rec2.getY() + newDistance);
				isRec2Up = !isRec2Up;
			}
		}
	}
	
	private class TimeHandler implements EventHandler<ActionEvent> {
		private double distance;
		
		private TimeHandler(boolean isUp) {
			distance = Constants.REC_MOVEMENT;
			if (isUp) {
				distance *= -1;
			}
		}
		
		@Override
		public void handle(ActionEvent evt) {
			//determines movement of first rectangle
			if (isRecMove(rec1, distance)) {
				rec1.setY(rec1.getY() + distance);
			} else {
				//determines newDistance based on close to top or bottom
				double newDistance = Math.min(rec1.getY(), Constants.APP_HEIGHT - (rec1.getY() + rec1.getHeight()));
				if (distance < 0) {
					newDistance *= -1;
				}
				System.out.println(rec1.getY() + rec1.getHeight() + " " + newDistance);
				rec1.setY(rec1.getY() + newDistance);
			}
		}
		
	}
	
	public boolean isRecMove(Rectangle rec, double distance) {
		appBorder = gameBorders.appBorder;
		double appYTop = appBorder.topBorder.start.y;
		double appYBottom = appBorder.bottomBorder.start.y;
		double nextYPos = rec.getY();
		if (rec.getY() + rec.getHeight() > Constants.APP_HEIGHT / 2) {
			nextYPos += rec.getHeight();
		} 
		if (nextYPos > appYBottom || nextYPos < appYTop) {
			return false;
		}
		return true;
	}
	
}
