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
	private static final int NORTH = -4;
	private static final int SOUTH = 4;
	private static final int EAST = 4;
	private static final int WEST = -4;
	private int shipSpeedX = 0;

	private static int enemy1Speed = 5;
	private static int enemy2Speed = 5;
	private static int enemy3Speed = 5;
	private static int enemy4Speed = 5;
	private static int enemy5Speed = 5;

	private int shipSpeedY = 0;
	KeyCode code;
	private ImageView ship;

	private Rectangle menu;
	private Rectangle userShot;
	private ImageView enemy1;
	private ImageView enemy2;
	private ImageView enemy3;
	private ImageView enemy4;
	private ImageView enemy5;
	private GameTimer timer;
Group root;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		menu = new Rectangle(0, 700, 800, 100);
		menu.setFill(Color.DARKGREY);
		
		userShot = new Rectangle(400, 400, 4, 40);
		userShot.setFill(Color.BLUE);

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

		URL shipLocation = SpaceDodgers.class.getResource("SpaceShip8.png");
		ship = new ImageView(shipLocation.toString());
		ship.setFitHeight(80);
		ship.setFitWidth(80);
		ship.setY(400);
		ship.setX(350);

		URL enemyLocation1 = SpaceDodgers.class.getResource("Enemy.png");

		enemy1 = new ImageView(enemyLocation1.toString());
		enemy1.setFitHeight(65);
		enemy1.setFitWidth(65);
		enemy1.setX(155);
		enemy1.setY(0);

		enemy2 = new ImageView(enemyLocation1.toString());
		enemy2.setFitHeight(65);
		enemy2.setFitWidth(65);
		enemy2.setX(310);
		enemy2.setY(0);

		enemy3 = new ImageView(enemyLocation1.toString());
		enemy3.setFitHeight(65);
		enemy3.setFitWidth(65);
		enemy3.setX(465);
		enemy3.setY(0);

		enemy4 = new ImageView(enemyLocation1.toString());
		enemy4.setFitHeight(65);
		enemy4.setFitWidth(65);
		enemy4.setX(620);
		enemy4.setY(0);

		enemy5 = new ImageView(enemyLocation1.toString());
		enemy5.setFitHeight(65);
		enemy5.setFitWidth(65);
		enemy5.setX(774);
		enemy5.setY(0);

		timer = new GameTimer();
		timer.start();

		 root = new Group(back, menu, btnStop, btnStart, btnQuit, ship, enemy1, enemy2, enemy3, enemy4, enemy5, userShot);

		Scene myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);

		primaryStage.setScene(myScene);
		primaryStage.show();
		primaryStage.setTitle("Space Dodgers");

		myScene.setOnKeyPressed(event -> keyPressed(event));
		myScene.setOnKeyReleased(event -> keyReleased(event));
	}

	public void keyPressed(KeyEvent event) {
		code = event.getCode();

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

			Bounds shipBounds = ship.getBoundsInLocal();
			Bounds menuBounds = menu.getBoundsInLocal();
			Bounds enemy1Bounds = enemy1.getBoundsInLocal();
			Bounds enemy2Bounds = enemy2.getBoundsInLocal();
			Bounds enemy3Bounds = enemy3.getBoundsInLocal();
			Bounds enemy4Bounds = enemy4.getBoundsInLocal();
			Bounds enemy5Bounds = enemy5.getBoundsInLocal();

			if (shipBounds.intersects(menuBounds) && code == KeyCode.DOWN) {
				shipSpeedX = 0;
				shipSpeedY = 0;
			}

				if (enemy1.getX() <= 0 || enemy1.getX() >= 775) {
					enemy1Speed = enemy1Speed * -1;
				}
				if (enemy2.getX() <= 0 || enemy2.getX() >= 775) {
					enemy2Speed = enemy2Speed * -1;
				}
				if (enemy3.getX() <= 0 || enemy3.getX() >= 775) {
					enemy3Speed = enemy3Speed * -1;
				}
				if (enemy4.getX() <= 0 || enemy4.getX() >= 775) {
					enemy4Speed = enemy4Speed * -1;
				}
				if (enemy5.getX() <= 0 || enemy5.getX() >= 775) {
					enemy5Speed = enemy5Speed * -1;
				}
				
				if(code != KeyCode.SHIFT) {
				userShot.setY(userShot.getY() + (-5));
				}

				enemy1.setX(enemy1.getX() + enemy1Speed);
				enemy2.setX(enemy2.getX() + enemy2Speed);
				enemy3.setX(enemy3.getX() + enemy3Speed);
				enemy4.setX(enemy4.getX() + enemy4Speed);
				enemy5.setX(enemy5.getX() + enemy5Speed);

				if (shipBounds.intersects(menuBounds) && code == KeyCode.DOWN) {
					shipSpeedX = 0;
					shipSpeedY = 0;

				} else {

					ship.setX(ship.getX() + shipSpeedX);
					ship.setY(ship.getY() + shipSpeedY);
				}
				
				if (code == KeyCode.SHIFT) {
					
					userShot.setX(ship.getX() + 40);
					userShot.setY(ship.getY());
					
					
					 }

				if (ship.getX() <= 15 && code == KeyCode.LEFT) {

					shipSpeedX = 0;
					shipSpeedY = 0;
				}
				if (ship.getX() >= 780 && code == KeyCode.RIGHT) {
					shipSpeedX = 0;
					shipSpeedY = 0;
				}
				if (ship.getY() <= 15 && code == KeyCode.UP) {
					shipSpeedX = 0;
					shipSpeedY = 0;
				}
					

			if (enemy1.getX() <= 0 || enemy1.getX() >= 775) {
				enemy1Speed = enemy1Speed * -1;
			}
			if (enemy2.getX() <= 0 || enemy2.getX() >= 775) {
				enemy2Speed = enemy2Speed * -1;
			}
			if (enemy3.getX() <= 0 || enemy3.getX() >= 775) {
				enemy3Speed = enemy3Speed * -1;
			}
			if (enemy4.getX() <= 0 || enemy4.getX() >= 775) {
				enemy4Speed = enemy4Speed * -1;
			}
			if (enemy5.getX() <= 0 || enemy5.getX() >= 775) {
				enemy5Speed = enemy5Speed * -1;

			}

			enemy1.setX(enemy1.getX() + enemy1Speed);
			enemy2.setX(enemy2.getX() + enemy2Speed);
			enemy3.setX(enemy3.getX() + enemy3Speed);
			enemy4.setX(enemy4.getX() + enemy4Speed);
			enemy5.setX(enemy5.getX() + enemy5Speed);

			if (shipBounds.intersects(menuBounds) && code == KeyCode.DOWN) {
				shipSpeedX = 0;
				shipSpeedY = 0;

			} else {

				ship.setX(ship.getX() + shipSpeedX);
				ship.setY(ship.getY() + shipSpeedY);
			}

			if (ship.getX() <= 15 && code == KeyCode.LEFT) {

				shipSpeedX = 0;
				shipSpeedY = 0;
			}
			if (ship.getX() >= 780 && code == KeyCode.RIGHT) {
				shipSpeedX = 0;
				shipSpeedY = 0;
			}
			if (ship.getY() <= 15 && code == KeyCode.UP) {
				shipSpeedX = 0;

				shipSpeedY = 0;
			}
			if (code == KeyCode.SPACE) {
				userShot = new Rectangle(ship.getX(), ship.getY(), 4, 20);
				userShot.setFill(Color.ALICEBLUE);
				userShot.setY(userShot.getY() + (-5));
			}
		}
	}

}
