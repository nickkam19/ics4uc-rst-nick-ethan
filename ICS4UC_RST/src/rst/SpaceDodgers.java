
package rst;

import java.awt.Label;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import simpleIO.Console;

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
	private static int enemy1Speed = 3;
	private static int enemy2Speed = 3;
	private static int enemy3Speed = 3;
	private static int enemy4Speed = 3;
	private static int enemy5Speed = 3;
	private static int lazerSpeed = 3;
	private int shipSpeedY = 0;
	
	private long[] nextTimes = new long[5];
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
	
	int gameTime;
	
	ArrayList<ImageView> lazers = new ArrayList<ImageView>();
	
	ImageView[] enemies = new ImageView[5];
	
	Boolean[] isAlive = new Boolean[5];
	
	Text lblTimer;
	
	Group root;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < nextTimes.length; i++) {
			nextTimes[i] = 2000000;
		}
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
		
		URL backLocation = SpaceDodgers.class.getResource("/StarBackl.jpg");
		
		ImageView back = new ImageView(backLocation.toString());
		
		back.setFitHeight(SCREEN_HEIGHT);
		back.setFitWidth(SCREEN_WIDTH);
		back.setX(0);
		back.setY(0);
		
		URL shipLocation = SpaceDodgers.class.getResource("/SpaceShip8.png");
		
		ship = new ImageView(shipLocation.toString());
		ship.setFitHeight(80);
		ship.setFitWidth(80);
		ship.setY(400);
		ship.setX(350);
		
		URL enemyLocation1 = SpaceDodgers.class.getResource("/Enemy.png");
		
		lblTimer = new Text("Time: ");
		lblTimer.setLayoutX(15);
		lblTimer.setLayoutY(740);
		
	
		
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
		
		enemies[0] = enemy1;
		enemies[1] = enemy2;
		enemies[2] = enemy3;
		enemies[3] = enemy4;
		enemies[4] = enemy5;
		
		
		

		for (int i = 0; i < isAlive.length; i++) {
			isAlive[i] = false;
		}
		
		timer = new GameTimer();
		timer.start();
		
		root = new Group(back, menu, btnStop, btnStart, btnQuit, ship, enemy1, enemy2, enemy3, enemy4, enemy5,
				userShot, lblTimer);
		
	
		
		for (int i = 0; i < 5; i++) {
			
			lazers.add(i, new ImageView("/lazer1.png"));
			root.getChildren().add(lazers.get(i));
			lazers.get(i).setX(850);
			lazers.get(i).setY(15);
			
					}
		
		Scene myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		
		primaryStage.setScene(myScene);
		primaryStage.show();
		primaryStage.setTitle("Space Dodgers");
		
		myScene.setOnKeyPressed(event -> keyPressed(event));
		myScene.setOnKeyReleased(event -> keyReleased(event));
	}

	public void keyPressed(KeyEvent event) {
		code = event.getCode();
		
		if (code == KeyCode.UP) {
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
		
		if (code == KeyCode.UP || code == KeyCode.DOWN) {
			shipSpeedY = 0;
		}
		if(code == KeyCode.LEFT || code == KeyCode.RIGHT) {
			shipSpeedX = 0;
		}
		if(code == KeyCode.SHIFT) {
			userShot.setY(userShot.getY() + (-5));
		}
	}

	class GameTimer extends AnimationTimer {
	
		long currentTime = 0;
		
		long THRESHOLD_MIN = 2_000_000;
		
		long THRESHOLD_MAX = 4_000_000;

		@Override
		public void handle(long now) {
			
			for (int i = 0; i < nextTimes.length; i++) {
				
				if (now - currentTime > nextTimes[i]) {
			
					if (!(isAlive[i])) {
						
						nextTimes[i] = (long) ((Math.random() * ((THRESHOLD_MAX - THRESHOLD_MIN) + 1) + THRESHOLD_MIN) );
						
						Console.print(i);
						Console.print(nextTimes[i]);
				
						lazers.get(i).setX((enemies[i].getX() + enemies[i].getFitWidth()) / 2);
						lazers.get(i).setY((enemies[i].getY() + enemies[i].getFitHeight()) / 2);
					
						isAlive[i] = true;
					}
					currentTime = now;
					lblTimer.setText("Time: " + currentTime);
				}
				
			}
			
			gameTime = (int) (System.currentTimeMillis() / 1000);
			
			Bounds shipBounds = ship.getBoundsInLocal();
			Bounds menuBounds = menu.getBoundsInLocal();

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
			if (code != KeyCode.SHIFT) {
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
			if (shipBounds.intersects(menuBounds)) {
				shipSpeedY = 0;
				ship.setY(800 - ship.getFitHeight() - menu.getHeight() - 1);
			}
			if (ship.getX() < 15) {
				shipSpeedX = 0;
				ship.setX(15);
			}
			if (ship.getX() > 780 - ship.getFitWidth()) {
				shipSpeedX = 0;
				ship.setX(780 - ship.getFitWidth());
			}
			if (ship.getY() < 35) {
				shipSpeedY = 0;
				ship.setY(35);
			}
			ship.setX(ship.getX() + shipSpeedX);
			ship.setY(ship.getY() + shipSpeedY);
			for (int i = 0; i < 5; i++) {
				if (lazers.get(i).getY() > 800) {
					lazers.get(i).setX(850);
					isAlive[i] = false;
				} else {
					lazers.get(i).setY(lazers.get(i).getY() + lazerSpeed);
				}
			}
			if (code == KeyCode.SPACE) {
				userShot = new Rectangle(ship.getX(), ship.getY(), 4, 20);
				userShot.setFill(Color.ALICEBLUE);
				userShot.setY(userShot.getY() + (-5));
			}
		}
	}
}
