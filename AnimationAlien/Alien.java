package AnimationAlien;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Alien {
	private Pane AlienObject = new Pane();
	private Ellipse faceOutline = new Ellipse(Constants.FACE_WIDTH, Constants.FACE_HEIGHT);
	private Ellipse leftEye = new Ellipse(Constants.EYE_WIDTH, Constants.EYE_HEIGHT);
	private Ellipse rightEye = new Ellipse(Constants.EYE_WIDTH, Constants.EYE_HEIGHT);
	
	public Alien() {
		this.colorFace();
		this.setXLoc(Constants.APP_WIDTH / 2);
		this.setYLoc(Constants.APP_HEIGHT / 2);
		AlienObject.getChildren().addAll(faceOutline, leftEye, rightEye);
	}
	
	public void colorFace() {
		faceOutline.setFill(Color.GREEN);
		leftEye.setFill(Color.BLACK);
		rightEye.setFill(Color.BLACK);
	}
	
	public void setXLoc(double adj) {
		
		faceOutline.setCenterX(adj);
		double xEyeOffset = Constants.FACE_WIDTH / 2;
		leftEye.setCenterX(adj - xEyeOffset);
		rightEye.setCenterX(adj + xEyeOffset);
	}
	
	public void setYLoc(double adj) {
		faceOutline.setCenterY(adj);
		double yEyeOffset = Constants.FACE_HEIGHT / 4;
		leftEye.setCenterY(adj - yEyeOffset);
		rightEye.setCenterY(adj - yEyeOffset);
	}
	
	public double getXLoc() {
		return faceOutline.getCenterX();
	}
	
	public double getYLoc() {
		return faceOutline.getCenterY();
	}
	
	
	public Pane getAlien() {
		return AlienObject;
	}
}
