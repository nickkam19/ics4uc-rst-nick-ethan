package rst;

import java.net.URL;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SpaceDodgers extends Application {

	// defining global constants and variables
	private static final double SCREEN_WIDTH = 800;
	private static final double SCREEN_HEIGHT = 800;
	private static final int SML = 15;
	private static final int NORTH = 4;
	private static final int SOUTH = -4;
	private static final int EAST = 4;
	private static final int WEST = -4;

	private int shipSpeedX = 0;
	private int shipSpeedY = 0;

	private ImageView ship;

	private Rectangle menu;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		menu = new Rectangle(0, 700, 800, 100);
		menu.setFill(Color.DARKGREY);

		Button btnStop = new Button("STOP");
		btnStop.setFont(Font.font(SML));
		btnStop.setLayoutX(120);
		btnStop.setLayoutY(740);

		Button btnStart = new Button("START");
		btnStart.setFont(Font.font(SML));
		btnStart.setLayoutX(350);
		btnStart.setLayoutY(740);

		Button btnQuit = new Button("EXIT");
		btnQuit.setFont(Font.font(SML));
		btnQuit.setLayoutX(570);
		btnQuit.setLayoutY(740);

		URL backLocation = SpaceDodgers.class.getResource("StarBackl.jpg");
		ImageView back = new ImageView(backLocation.toString());
		back.setFitHeight(SCREEN_HEIGHT);
		back.setFitWidth(SCREEN_WIDTH);
		back.setX(0);
		back.setY(0);

		URL shipLocation = SpaceDodgers.class.getResource("SpaceshipRm.png");
		ship = new ImageView(shipLocation.toString());
		ship.setFitHeight(80);
		ship.setFitWidth(80);
		ship.setY(400);
		ship.setX(350);

		Group root = new Group(back, menu, btnStop, btnStart, btnQuit, ship);

		Scene myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);

		primaryStage.setScene(myScene);
		primaryStage.show();
		primaryStage.setTitle("Space Dodgers");

		myScene.setOnKeyPressed(event -> keyPressed(event));
		myScene.setOnKeyReleased(event -> keyReleased(event));
	}

	public void keyPressed(KeyEvent event) {
		KeyCode code = event.getCode();

		if (code == KeyCode.UP || code == KeyCode.W) {
			shipSpeedY = NORTH;
		}
		if (code == KeyCode.DOWN) {
			shipSpeedY = SOUTH;
		}
		if (code == KeyCode.LEFT) {
			shipSpeedX = WEST;
		}
		if (code == KeyCode.RIGHT) {
			shipSpeedX = EAST;
		}
	}

	public void keyReleased(KeyEvent event) {
		KeyCode code = event.getCode();

		if (code == KeyCode.UP || code == KeyCode.LEFT || code == KeyCode.DOWN || code == KeyCode.RIGHT) {
			shipSpeedX = 0;
			shipSpeedY = 0;
		}
	}

	class GameTimer extends AnimationTimer {

		@Override
		public void handle(long now) {
			
			ship.setX(ship.getX() + shipSpeedX);
			ship.setY(ship.getY() + shipSpeedY);

			Bounds shipBounds = ship.getBoundsInLocal();
			Bounds menuBounds = menu.getBoundsInLocal();

			if (shipBounds.intersects(menuBounds)) {
				shipSpeedY = 0;
				shipSpeedX = 0;
			}
			if(ship.getX() < 15 || ship.getX() > 780 || ship.getY() > 780) {
				shipSpeedX = 0;
				shipSpeedY = 0;
			}
		}
	}
}
