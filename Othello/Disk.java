package Othello;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Disk {
	private Ellipse diskBody;
	
	public Disk(Color color, int diskSize, int x, int y) {
		setupInstances(color, diskSize, x, y);
	}
	
	public Disk (Disk savedDisk) {
		double diskXLoc = savedDisk.getDiskBody().getCenterX();
		double diskYLoc = savedDisk.getDiskBody().getCenterY();
		double radius = savedDisk.getDiskBody().getRadiusX();
		this.diskBody = new Ellipse(diskXLoc, diskYLoc, radius, radius);
		this.diskBody.setFill(savedDisk.getDiskBody().getFill());
	}
	
	//setup methods
	private void setupInstances(Color color, int diskSize, int x, int y) {
		int xLocPane = (x * Constants.SQUARE_HEIGHT) + (Constants.SQUARE_HEIGHT / 2);
		int yLocPane = (y * Constants.SQUARE_HEIGHT) + (Constants.SQUARE_HEIGHT / 2);
		diskBody = new Ellipse(xLocPane , yLocPane, diskSize, diskSize);
		diskBody.setFill(color);
	}
	
	
	//set methods
	public void setDiskColor(Color color) {
		diskBody.setFill(color);
	}
	
	//get methods
	public Ellipse getDiskBody() {
		return diskBody;
	}
}
