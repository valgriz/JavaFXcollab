import java.awt.Canvas;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Anim extends Main {

	private Stage s;
	private Sphere circle;
	private double x, y, dx, dy;

	@Override
	public void start(Stage primaryStage) {
		// TODO Auto-generated method stub
		super.start(primaryStage);
	}

	public Anim() {
		s = new Stage();
		s.setWidth(200);
		s.setHeight(200);
		s.setResizable(false);
		s.show();

		circle = new Sphere(30.00);

		Group gr = new Group();

		Canvas c = new Canvas();

		// JavaFX uses Timeline instead of Threading for game loops,
		// demonstrated below

		final Duration oneFrameAmt = Duration.millis(1000 / 60);
		final KeyFrame oneFrame = new KeyFrame(oneFrameAmt, new EventHandler() {
			public void handle(Event event) {
				update();
				render();
			}
		});
		TimelineBuilder.create().cycleCount(Animation.INDEFINITE).keyFrames(oneFrame).build().play();
	}

	public void update() {

	}

	public void render() {

	}
}
