package rst;

import java.net.URL;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Bounds;



public class GetToTheOtherSide extends Application{

	
	//defining global constants and variables
	 static final double SCREEN_WIDTH = 1000;
	 static final double SCREEN_HEIGHT = 800;
	 
	 final int CAR_WIDTH = 60;
	 final int CAR_LENGTH = 45;
	 double carSpeed = randomSpeed();
	 double car2Speed = randomSpeed();
	 double car3Speed = randomSpeed();
	 
	 final int ROAD_WIDTH = 240;
	 final int ROAD_LENGTH = 1000;
	
	 Rectangle car, car2, car3, road;

	 
	 static final double CHICKEN_SIZE = 30;
	 double chickenSpeedX = 0;
	 double chickenSpeedY = 0;
	 
	 
	 static final double MAX_SPEED = 3.0;
	 static final double MIN_SPEED = 0.5;
	 
	 final short NORTH = -4;
	 final short SOUTH = 4;
	 final short EAST = 4;
	 final short WEST = -4;
	 
	 GameTimer timer;
	 
	 ImageView chicken, flag;
	 
	 Text title, fails, wins;
	
	 int failNum, winNum = 0;
	
	
	
	public void start(Stage myStage) {
		
		//creating background elements
		road = new Rectangle(0, 205, ROAD_LENGTH, ROAD_WIDTH);
		
		road.setFill(Color.BLACK);
		
		//title, fail counter, win counter
		title = new Text("Get To The Other Side");
		title.setFont(Font.font(40));
		title.setX(250);
		title.setY(50);
		title.setFill(Color.RED);
		
		fails = new Text("Fails: " + failNum);
		fails.setFont(Font.font(20));
		fails.setX(20);
		fails.setY(200);
		
		
		wins = new Text("Wins: " + winNum);
		wins.setFont(Font.font(20));
		wins.setX(20);
		wins.setY(175);
		
		
		
		//Setting the finish line
		URL location2 = GetToTheOtherSide.class.getResource("/checkered.jpg");
	
		flag = new ImageView(location2.toString());
		flag.setFitWidth(1000);
		flag.setFitHeight(80);
		flag.setX(0);
		flag.setY(0);
		
		
		
		//creating game elements
		
		car = new Rectangle(0, 375, CAR_WIDTH, CAR_LENGTH);
		car2 = new Rectangle(0, 300, CAR_WIDTH, CAR_LENGTH);
		car3 = new Rectangle(0, 225, CAR_WIDTH, CAR_LENGTH);
		URL location = GetToTheOtherSide.class.getResource("/chicken_PNG2172.png");
		chicken = new ImageView(location.toString());
		
		//positioning chicken
		chicken.setFitHeight(50);
		chicken.setFitWidth(50);
		chicken.setX(450);
		chicken.setY(750);
	
		//setting car color
		car.setFill(Color.BLUE);
		car2.setFill(Color.CRIMSON);
		car3.setFill(Color.DIMGREY);
		
		//grouping game elements
		Group root = new Group(flag, road, car, car2, car3, chicken, title, fails, wins);
	  
		//setting scene
		Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		scene.setOnKeyPressed(event -> keyPressed(event));
		scene.setOnKeyReleased(event -> keyReleased(event));
		 timer = new GameTimer();
	        timer.start();
		
	        //setting stage
		myStage.setTitle("Get To The Other Side");
		myStage.setScene(scene);
	    myStage.show();
	}
	
	class GameTimer extends AnimationTimer{

		@Override
		public void handle(long now) {
			
			//moving chicken
			chicken.setX(chicken.getX() + chickenSpeedX);
			chicken.setY(chicken.getY() + chickenSpeedY);
			
			//moving cars
			car.setX(car.getX() + carSpeed);
			car2.setX(car2.getX() + car2Speed);
			car3.setX(car3.getX() + car3Speed);
			
			//boundaries
			Bounds chickenBounds = chicken.getBoundsInLocal();
			Bounds carBounds = car.getBoundsInLocal();
			Bounds car2Bounds = car2.getBoundsInLocal();
			Bounds car3Bounds = car3.getBoundsInLocal();
			Bounds flagBounds = flag.getBoundsInLocal();
			
			
			//counting wins and detecting finish
			if (chickenBounds.intersects(flagBounds)) {
				restart();
				winNum++;
				wins.setText("Wins: " + winNum);
			}
			
			//restart when car hits
			if (chickenBounds.intersects(carBounds) || chickenBounds.intersects(car2Bounds) ||
					chickenBounds.intersects(car3Bounds)) {
				restart();
				failNum++;
				fails.setText("Fails: " + failNum);
			}
			//stage boundaries
			if(chickenBounds.getMinX() < 0) {
				if(chickenSpeedX == WEST) {
					chickenSpeedX = 0;
				}
			}
			
			if(chickenBounds.getMaxX() > 1000) {
				if(chickenSpeedX == EAST) {
					chickenSpeedX = 0;
				}
			}
			if(chickenBounds.getMaxY() > 800) {
				if(chickenSpeedY == SOUTH) {
					chickenSpeedY = 0;
				}
			}
			
			//random speed for car every time it hits edge
			if(car.getX() <= 0) {
				carSpeed = randomSpeed();
			
			}
			if(car.getX() > 940){
				carSpeed = -randomSpeed();				
			}
			
			if(car2.getX() <= 0) {
				car2Speed = randomSpeed();
			
			}
			if(car2.getX() > 940){
				car2Speed = -randomSpeed();				
			}
			if(car3.getX() <= 0) {
				car3Speed = randomSpeed();
			
			}
			if(car3.getX() > 940){
				car3Speed = -randomSpeed();				
			}
					
			}
			
		}
		
	 //key input
	public void keyPressed(KeyEvent event){
		KeyCode code = event.getCode();
		
		if(code == KeyCode.UP || code == KeyCode.W) {
			chickenSpeedY = NORTH;
		}
		if(code == KeyCode.DOWN) {
			chickenSpeedY = SOUTH;
		}
		if(code == KeyCode.LEFT) {
			chickenSpeedX = WEST;
		}
		if(code == KeyCode.RIGHT) {
			chickenSpeedX = EAST;
		}
	}
	
	//key released 
	public void keyReleased(KeyEvent event) {
		KeyCode code = event.getCode();

        if (code == KeyCode.UP || code == KeyCode.LEFT || 
                code == KeyCode.DOWN || code == KeyCode.RIGHT) {
            chickenSpeedX = 0;
            chickenSpeedY = 0;
        }
    }
	
	//getting random speed value
	public static double randomSpeed(){
		double speed = (Math.random() * 13) + 6;
		return speed;
	}
	
	//sets chicken back to start
	void restart() {
		chicken.setX(450);
		chicken.setY(750);
	}
	
	//launch
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
		
	}

}
