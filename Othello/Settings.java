package Othello;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Settings {
	private Game game;
	private VBox vBoxMain;
	private Player player1;
	private Player player2;
	private RadioButton human1;
	private RadioButton human2;
	private RadioButton easy1;
	private RadioButton easy2;
	private RadioButton med1;
	private RadioButton med2;
	private RadioButton hard1;
	private RadioButton hard2;
	private CheckBox deterministic1;
	private CheckBox deterministic2;
	private Button apply;
	private Button reset;
	private Button quit;
	
	public Settings(Game game) {
		setupInstances(game);
		setupLayout();
		applyHandlers();
		setupPlayers();
	}
	
	private void setupInstances(Game game) {
		this.game = game;
	}
	
	private void setupLayout() {
		vBoxMain = new VBox();
		HBox hBoxTop = new HBox();
		HBox hBoxMid = new HBox();
		HBox hBoxBot = new HBox();
		hBoxTop.setPrefHeight(Constants.APP_HEIGHT / 3);
		hBoxMid.setPrefHeight(Constants.APP_HEIGHT / 3);
		hBoxBot.setPrefHeight(Constants.APP_HEIGHT / 3);
		setupHBoxBotBtnPane(hBoxBot);
		setupHBoxMidBtnPane(hBoxMid);
		setupHBoxTopPane(hBoxTop);
		vBoxMain.getChildren().addAll(hBoxTop, hBoxMid, hBoxBot);
		vBoxMain.setAlignment(Pos.CENTER);
	}
	
	private void setupHBoxTopPane(HBox hBoxTop) {
		VBox vBoxTop = new VBox();
		Label instructionLabel1 = new Label("Select options");
		Label instructionLabel2 = new Label("Then press Apply Settings");
		Label instructionLabel3 = new Label("Gray Squares are legal moves");
		instructionLabel1.setFont(new Font("Garamond", 20));
		instructionLabel2.setFont(new Font("Garamond", 20));
		instructionLabel3.setFont(new Font("Garamond", 20));
		VBox vBoxInstructionLabel = new VBox();
		vBoxInstructionLabel.getChildren().addAll(instructionLabel1, instructionLabel2, instructionLabel3);
		vBoxInstructionLabel.setAlignment(Pos.CENTER);
		Label countLabel = new Label("White: " + game.getBoard().getPiecesCount()[0] + ", Black: " + game.getBoard().getPiecesCount()[1]);
		game.setCountLabel(countLabel);
		countLabel.setFont(new Font("Garamond", 20));
		vBoxTop.getChildren().addAll(vBoxInstructionLabel, countLabel);
		vBoxTop.setAlignment(Pos.CENTER);
		vBoxTop.setSpacing(100);
		hBoxTop.getChildren().add(vBoxTop);
		hBoxTop.setAlignment(Pos.CENTER);
	}
	
	private void setupHBoxMidBtnPane(HBox hBoxMid) {
		VBox vBoxLeft = new VBox();
		VBox vBoxRight = new VBox();
		hBoxMid.getChildren().addAll(vBoxLeft, vBoxRight);
		Label white = new Label("White");
		white.setAlignment(Pos.CENTER);
		Label black = new Label("Black");
		white.setFont(new Font("Garamond", 20));
		black.setFont(new Font("Garamond", 20));
		human1 = new RadioButton("Human 1");
		human2 = new RadioButton("Human 2");
		easy1 = new RadioButton("Computer: Easy");
		easy2 = new RadioButton("Computer: Easy");
		med1 = new RadioButton("Computer: Medium");
		med2 = new RadioButton("Computer: Medium");
		hard1 = new RadioButton("Computer: Hard");
		hard2 = new RadioButton("Computer: Hard");
		deterministic1 = new CheckBox("Deterministic");
		deterministic2 = new CheckBox("Deterministic");
		human1.setFont(new Font("Garamond", 16));
		human2.setFont(new Font("Garamond", 16));
		easy1.setFont(new Font("Garamond", 16));
		easy2.setFont(new Font("Garamond", 16));
		med1.setFont(new Font("Garamond", 15));
		med2.setFont(new Font("Garamond", 15));
		hard1.setFont(new Font("Garamond", 16));
		hard2.setFont(new Font("Garamond", 16));
		deterministic1.setFont(new Font("Garamond", 16));
		deterministic2.setFont(new Font("Garamond", 16));
		ToggleGroup toggleListLeft = new ToggleGroup();
		ToggleGroup toggleListRight = new ToggleGroup();
		VBox vBoxLeftTop = new VBox();
		VBox vBoxLeftBottom = new VBox();
		VBox vBoxRightTop = new VBox();
		VBox vBoxRightBottom = new VBox();
		vBoxLeftTop.getChildren().add(white);
		vBoxLeftTop.setAlignment(Pos.CENTER);
		vBoxLeftBottom.setSpacing(10);
		human1.setToggleGroup(toggleListLeft);
		easy1.setToggleGroup(toggleListLeft);
		med1.setToggleGroup(toggleListLeft);
		hard1.setToggleGroup(toggleListLeft);
		human2.setToggleGroup(toggleListRight);
		easy2.setToggleGroup(toggleListRight);
		med2.setToggleGroup(toggleListRight);
		hard2.setToggleGroup(toggleListRight);
		vBoxLeftBottom.getChildren().addAll(human1, easy1, med1, hard1, deterministic1);
		vBoxLeft.getChildren().addAll(vBoxLeftTop, vBoxLeftBottom);
		vBoxRightTop.getChildren().add(black);
		vBoxRightTop.setAlignment(Pos.TOP_CENTER);
		vBoxRightBottom.setSpacing(10);
		vBoxRightBottom.getChildren().addAll(human2, easy2, med2, hard2, deterministic2);
		vBoxRight.getChildren().addAll(vBoxRightTop, vBoxRightBottom);
		hBoxMid.setSpacing(10);
		vBoxLeft.setSpacing(10);
		vBoxRight.setSpacing(10);
		
	}
	
	private void setupHBoxBotBtnPane(HBox hBoxBot) {
		hBoxBot.setAlignment(Pos.CENTER);
		apply = new Button("Apply Settings");
		//reset = new Button("Reset");
		quit = new Button("Quit");
		apply.setFont(new Font("Garamond", 20));
		//reset.setFont(new Font("Garamond", 20));
		quit.setFont(new Font("Garamond", 20));
		VBox vBox = new VBox();
		hBoxBot.getChildren().add(vBox);
		vBox.getChildren().addAll(apply, quit);
		vBox.setSpacing(50);
		vBox.setAlignment(Pos.TOP_CENTER);
	}
	
	private void applyHandlers() {
		quit.setOnAction(new QuitHandler());
		//reset.setOnAction(new ResetHandler());
		apply.setOnAction(new ApplySettingsHandler());
		human1.setOnAction(new playerHandler());
		easy1.setOnAction(new playerHandler());
		med1.setOnAction(new playerHandler());
		hard1.setOnAction(new playerHandler());
		human2.setOnAction(new playerHandler());
		easy2.setOnAction(new playerHandler());
		med2.setOnAction(new playerHandler());
		hard2.setOnAction(new playerHandler());
	}
	
	private void setupPlayers() {
		player1 = new Human(Color.WHITE, game);
		player2 = new Human(Color.BLACK, game);
		human1.setSelected(true);
		human2.setSelected(true);
		deterministic1.setSelected(false);
		deterministic1.setDisable(true);
		deterministic2.setSelected(false);
		deterministic2.setDisable(true);
		
	}
	
	//get methods
	public VBox getSettingsPane() {
		return vBoxMain;
	}
	
	//event handler classes
	private class playerHandler implements EventHandler<ActionEvent> {
		
		private playerHandler() {}
		
		@Override
		public void handle(ActionEvent evt) {
			if (deterministic1.isDisabled()) {
				deterministic1.setSelected(true);
				deterministic1.setDisable(false);
			}
			if (deterministic2.isDisabled()) {
				deterministic2.setSelected(true);
				deterministic2.setDisable(false);
			}
			if (human1.isSelected()) {
				player1 = new Human(Color.WHITE, game);
				deterministic1.setSelected(false);
				deterministic1.setDisable(true);
			} else if (easy1.isSelected()) {
				player1 = new Computer(Color.WHITE, 1, game);
			} else if (med1.isSelected()) {
				player1 = new Computer(Color.WHITE, 2, game);
			} else if (hard1.isSelected()) {
				player1 = new Computer(Color.WHITE, 3, game);
			} 
			if (human2.isSelected()) {
				player2 = new Human(Color.BLACK, game);
				deterministic2.setSelected(false);
				deterministic2.setDisable(true);
			} else if (easy2.isSelected()) {
				player2 = new Computer(Color.BLACK, 1, game);
			} else if (med2.isSelected()) {
				player2 = new Computer(Color.BLACK, 2, game);
			} else if (hard2.isSelected()) {
				player2 = new Computer(Color.BLACK, 3, game);
			}
		}
		
	}
	
	private class ApplySettingsHandler implements EventHandler<ActionEvent> {
		
		private ApplySettingsHandler() {}
		
		@Override
		public void handle(ActionEvent evt) {
			if (player1 != null && player2 != null) {
				Referee referee;
				if (game.getReferee() == null || game.getReferee().getCurrPlayer().getPieceColor() == Color.WHITE) {
					referee = new Referee(player1, player2);
				} else {
					referee = new Referee(player2, player1);
				}
				game.setReferee(referee);
				game.setComputerTimelineNull();
				game.setupTurn();
			}
		}
	}
	
	private class QuitHandler implements EventHandler<ActionEvent> {
		
		private QuitHandler() {}
		
		@Override
		public void handle(ActionEvent evt) {
			Platform.exit();
		}
	}
	
	private class ResetHandler implements EventHandler<ActionEvent> {
		private ResetHandler() {}
		
		@Override
		public void handle(ActionEvent evt) {
			return;
		}
	}
}
