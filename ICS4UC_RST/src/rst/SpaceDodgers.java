package rst;

import java.net.URL;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SpaceDodgers extends Application {

	// defining global constants and variables
	static final double SCREEN_WIDTH = 800;
	static final double SCREEN_HEIGHT = 800;
	static final int GAP = 15;
	static final int SML = 15;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		Rectangle menu = new Rectangle(0, 700, 800 ,100);
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
		ImageView ship = new ImageView(shipLocation.toString());
		ship.setFitHeight(80);
		ship.setFitWidth(80);
		ship.setY(400);
		ship.setX(350);

		
		Group root = new Group(back, menu, btnStop, btnStart, btnQuit, ship);

		Scene myScene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);

		primaryStage.setScene(myScene);
		primaryStage.show();
		primaryStage.setTitle("Space Dodgers");

	}

}
