//2%

//Application, stage, scene, shapes, event handling
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//Pane imports
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
//Imports for event handling
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

//Class extends applicatoin
public class Main extends Application {

	public static void main(String args[]) {
		launch(args);// Launch arguments
	}

	@Override
	// start method in application class
	public void start(Stage primaryStage) {
		// Create a scene and place the button in the scene
		Scene twoPercent = new Scene(new Button("2%"), 250, 250);
		primaryStage.setTitle("2%");
		primaryStage.setScene(twoPercent); // Sets scene in stage
		primaryStage.show();// Display stage

		// Now we create another stage with pane examples
		Stage secondStage = new Stage();
		// Create checkerboard, first make 2 arrays with 32 squares each
		Rectangle[] blacks = new Rectangle[32];
		for (int i = 0; i < 32; i++) {
			blacks[i] = new Rectangle(0, 0, 10, 10);
			blacks[i].setFill(Color.BLACK);
		}
		Rectangle[] reds = new Rectangle[32];
		for (int i = 0; i < 32; i++) {
			reds[i] = new Rectangle(0, 0, 10, 10);
			reds[i].setFill(Color.RED);
		}

		// create gridpane and place squares from red and black arrays in
		GridPane checkerboard = new GridPane();
		int b = 0, r = 0;
		for (int h = 0; h < 8; h++) {
			for (int w = 0; w < 8; w++) {
				if ((h + w) % 2 == 0) {
					checkerboard.add(blacks[b], h, w);
					b++;
				} else {
					checkerboard.add(reds[r], h, w);
					r++;
				}
			}
		}
		checkerboard.setAlignment(Pos.CENTER);// This keeps our checkerboard at
												// the center
		// Now, we make a button to swap the colors
		Button swapColors = new Button("Swap Colors");
		swapColors.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				randomizeColors(blacks);
				randomizeColors(reds);
			}
		});
		// Add button to bottom of grid pane:
		checkerboard.add(swapColors, 9, 9);
		// Set scene, set stage, and display stage
		Scene paneExample = new Scene(checkerboard, 200, 200);
		secondStage.setScene(paneExample);
		secondStage.show();

		Basis basis = new Basis();
	}

	public void randomizeColors(Rectangle[] r) {
		int red = (int) (Math.random() * 256), blue = (int) (Math.random() * 256), green = (int) (Math.random() * 256);
		for (Rectangle x : r)
			x.setFill(Color.rgb(red, green, blue));
	}

	public Main() {
		System.out.println("2%");
	}
}
