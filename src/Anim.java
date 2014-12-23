import java.awt.Canvas;
import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.RotateTransitionBuilder;
import javafx.animation.SequentialTransitionBuilder;
import javafx.animation.TimelineBuilder;
import javafx.animation.TranslateTransitionBuilder;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Anim extends Main {

	private Stage s;
	private Sphere circle;
	private double x, y, dx, dy;
	private final int WIDTH = 200;
	private final int HEIGHT = 200;

	static Image backgroundImage = new Image(Anim.class.getResource("res/images/background.png").toString());

	static Image treeOne = new Image(Anim.class.getResource("res/images/tree1.png").toString());
	static Image treeTwo = new Image(Anim.class.getResource("res/images/tree2.png").toString());
	static Image treeThree = new Image(Anim.class.getResource("res/images/tree3.png").toString());

	static Image birdOne = new Image(Anim.class.getResource("res/images/bird1.png").toString());
	static Image birdTwo = new Image(Anim.class.getResource("res/images/bird2.png").toString());

	private Group bird;

	private Animation current;

	private Random r = new Random();

	@Override
	public void start(Stage primaryStage) {
		super.start(primaryStage);
	}

	public Anim() {

		ImageView background = new ImageView(backgroundImage);

		ImageView tree1 = new ImageView(treeOne);
		ImageView tree2 = new ImageView(treeTwo);
		ImageView tree3 = new ImageView(treeThree);

		ImageView bird1 = new ImageView(birdOne);
		ImageView bird2 = new ImageView(birdTwo);

		bird = new Group(bird1);

		Group foregroundGroup = new Group(tree1, tree2, tree3);

		Group backgroundGroup = new Group(background, foregroundGroup, bird);

		Scene scene = new Scene(backgroundGroup, WIDTH, HEIGHT);

		tree1.setTranslateX(0);
		tree1.setTranslateY(40);

		tree2.setTranslateX(50);
		tree2.setTranslateY(30);

		tree3.setTranslateX(100);
		tree3.setTranslateY(55);

		s = new Stage();
		s.setWidth(WIDTH);
		s.setHeight(HEIGHT);
		s.setResizable(false);
		s.setScene(scene);
		s.setTitle("Flappy Fish");
		s.show();

		startAnimation();

		TimelineBuilder.create().cycleCount(Animation.INDEFINITE).keyFrames(

		new KeyFrame(Duration.millis(150), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				bird.getChildren().setAll(bird2);
			}
		}), new KeyFrame(Duration.millis(300), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				bird.getChildren().setAll(bird1);
			}
		})

		).build().play();

		SequentialTransitionBuilder hitAnimation = SequentialTransitionBuilder
				.create()
				.node(bird)
				.children(
						RotateTransitionBuilder.create().fromAngle(0).toAngle(1260).duration(Duration.millis(800))
								.build(),
						TranslateTransitionBuilder.create().byY(1000).duration(Duration.millis(800)).build());

		bird.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				current.stop();
			}

		});

	}

	private void startAnimation() {
		if (current != null) {
			current.stop();
		}

		final int y0 = r.nextInt(HEIGHT / 2) + (HEIGHT / 4);
		final int y1 = r.nextInt(HEIGHT / 2) + (HEIGHT / 4);

		System.out.println(y0 + " " + y1);

		current = TranslateTransitionBuilder.create().node(bird).fromX(-100).toX(WIDTH).fromY(y0).toY(y1)
				.duration(Duration.seconds(2)).onFinished(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						startAnimation();
					}
				}).build();
		current.play();
	}

	public void update() {

	}

	public void render() {

	}
}
