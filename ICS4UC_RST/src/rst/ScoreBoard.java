package rst;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import simpleIO.Console;

public class ScoreBoard extends Application {
	ArrayList<Player> playerList;
	ArrayList<String> lines;

	String first;
	String second;
	String third;

	int linesLength;

	GridPane root;

	static final int GAP = 15;
	static final int SCREEN_HEIGHT = 850;
	static final int SCREEN_WIDTH = 800;
	static final int FONT_TITLE = 45;
	static final int FONT_LARGE = 15;
	static final int FONT_MED = 10;
	static final int FONT_SMALL = 7;

	Button btnAdd, btnSortAlpha;

	Label lblPoints, lblTitle, lblFirst, lblSecond, lblThird, lblName, lblScore, lblScoreNum;

	TextField txtName;

	ListView<Player> lstPlayer;

	@Override
	public void start(Stage myStage) throws Exception {
		playerList = new ArrayList<Player>();

		root = new GridPane();
		root.setVgap(GAP);
		root.setHgap(GAP);
		root.setPadding(new Insets(GAP, GAP, GAP, GAP));
		root.setStyle("-fx-background-color:#899aab; -fx-opacity:1;");

		lblTitle = new Label("Leaderboard");
		lblTitle.setFont(Font.font(FONT_TITLE));
		root.add(lblTitle, 0, 0);

		lblFirst = new Label("");
		lblFirst.setFont(Font.font(FONT_LARGE));
		root.add(lblFirst, 0, 5);

		lblSecond = new Label("");
		lblSecond.setFont(Font.font(FONT_LARGE));
		root.add(lblSecond, 1, 5);

		lblThird = new Label("");
		lblThird.setFont(Font.font(FONT_LARGE));
		root.add(lblThird, 2, 5);

		lblName = new Label("Name : ");
		lblName.setFont(Font.font(FONT_MED));
		root.add(lblName, 3, 1);

		lblScore = new Label("Score : ");
		lblScore.setFont(Font.font(FONT_MED));
		root.add(lblScore, 3, 2);

		lblScoreNum = new Label("");
		lblScoreNum.setFont(Font.font(FONT_MED));
		root.add(lblScoreNum, 3, 3);

		txtName = new TextField("");
		root.add(txtName, 4, 1);

		lstPlayer = new ListView<Player>();
		root.add(lstPlayer, 0, 1, 2, 2);

		fill();
		setHighScore();

		Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
		myStage.setScene(scene);
		myStage.setTitle("Leaderboard"); // Title
		myStage.show();

	}

	private void setHighScore() {
		Player temp;
		int moveItem;
		for (int i = 0; i < playerList.size(); i++) {
			temp = playerList.get(i);
			moveItem = i;
			while (moveItem > 0 && temp.getScore() > playerList.get(moveItem - 1).getScore()) {

				playerList.set(moveItem, playerList.get(moveItem - 1));
				moveItem = moveItem - 1;
			}
			playerList.set(moveItem, temp);

		}
		first = playerList.get(0).getName() + " Pnts :" + String.valueOf(playerList.get(0).getScore());
		second = playerList.get(1).getName() + " Pnts :" + String.valueOf(playerList.get(1).getScore());
		third = playerList.get(2).getName() + " Pnts :" + String.valueOf(playerList.get(2).getScore());

		lblFirst.setText(first);
		lblSecond.setText(second);
		lblThird.setText(third);
		
	}

	private void fill() {

		linesLength = 0;
		lines = new ArrayList<String>();

		try {
			BufferedReader br = new BufferedReader(new FileReader("data/Players.txt"));
			String line;

			while ((line = br.readLine()) != null) {
				lines.add(line);
				linesLength++;
			}

			br.close();
		} catch (FileNotFoundException e) {
			// Let the user know, but continue the program;
			Console.print("File not found");
		} catch (IOException e) {
			System.err.println("Exception: " + e);
			System.exit(0);
		}

		for (int i = 0; i < linesLength; i += 2) {

			playerList.add(new Player(lines.get(i), lines.get(i + 1)));
		}
		updateList();

	}

	private void updateList() {

		lstPlayer.getItems().clear();
		lstPlayer.getItems().addAll(playerList);

	}

	public static void main(String[] args) {

		launch(args);

	}
}
