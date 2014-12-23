import java.util.Random;

import javafx.animation.Animation;
import javafx.animation.AnimationBuilder;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.animation.TranslateTransitionBuilder;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class JumpGame extends Main {

	private Stage s;

	private Image backgroundImage = new Image(JumpGame.class.getResource("/res/images/jgbackground.png").toString());
	private Image cloudImage1 = new Image(JumpGame.class.getResource("/res/images/jgcloud1.png").toString());
	private Image cloudImage2 = new Image(JumpGame.class.getResource("/res/images/jgcloud2.png").toString());
	private Image platformImage1 = new Image(JumpGame.class.getResource("/res/images/jgplatform1.png").toString());
	private Image birdImage1 = new Image(JumpGame.class.getResource("/res/images/jgbird1.png").toString());

	private ImageView backgroundImageView1;
	private ImageView backgroundImageView2;
	private ImageView birdImageView1;

	private Animation backgroundAnimation;

	private Group background;
	private Group foregroundGroup;
	private Group platformGroup;

	private Random r;

	private ImageView[] ivArray;
	private ImageView[] platformArray;

	private ImageView birdImageView;

	private static final int WIDTH = 480;
	private static final int HEIGHT = 300;

	private double backgroundX;
	private double backgroundDx;
	private double gravity;
	private double energyLoss;
	private double dT;

	private double birdX, birdY, birdDx, birdDy;

	public JumpGame() {
		s = new Stage();
		s.show();
		s.setWidth(WIDTH);
		s.setHeight(HEIGHT);
		s.setTitle("Jump Game");
		s.setResizable(false);

		birdX = birdY = 20;
		birdDx = birdDy = 1;

		r = new Random();

		backgroundImageView1 = new ImageView(backgroundImage);
		backgroundImageView2 = new ImageView(backgroundImage);

		ImageView iv1;
		ImageView iv2;

		iv1 = new ImageView(cloudImage1);
		iv2 = new ImageView(cloudImage2);

		ivArray = new ImageView[3];
		randomizeClouds();

		foregroundGroup = new Group();
		for (int i = 0; i < ivArray.length; i++) {
			foregroundGroup.getChildren().add(ivArray[i]);
			ivArray[i].setX(20 * (r.nextInt(24)));
			ivArray[i].setY(20 * (r.nextInt(5)));
		}

		backgroundImageView2.setX(WIDTH);
		background = new Group(backgroundImageView1, backgroundImageView2, foregroundGroup);
		Scene scene1 = new Scene(background, WIDTH, HEIGHT);

		s.setScene(scene1);

		backgroundX = 0;
		backgroundDx = .6;

		platformArray = new ImageView[5];

		platformGroup = new Group();

		for (int i = 0; i < platformArray.length; i++) {
			platformArray[i] = new ImageView(platformImage1);
			platformGroup.getChildren().add(platformArray[i]);
		}

		background.getChildren().add(platformGroup);

		birdImageView = new ImageView(birdImage1);
		Group birdGroup = new Group(birdImageView);
		background.getChildren().add(birdGroup);

		//
		dT = .5;
		gravity = 20;

		allignPlatformConfiguration();

		animateBackgroundUsingTimeline();

		s.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.LEFT | event.getCode() == KeyCode.A) {
					birdDx = -1;
				}
				if (event.getCode() == KeyCode.RIGHT | event.getCode() == KeyCode.D) {
					birdDx = 1;
				}

			}

		});

		s.getScene().setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.LEFT | event.getCode() == KeyCode.A) {
					birdDx = 0;
				}
				if (event.getCode() == KeyCode.RIGHT | event.getCode() == KeyCode.D) {
					birdDx = 0;
				}
			}
		});

	}

	private void allignPlatformConfiguration() {
		for (int i = 0; i < platformArray.length; i++) {
			platformArray[i].setX(i * 104);
			platformArray[i].setY(175);
		}
	}

	private void randomizePlatformConfiguration() {
		for (int i = 0; i < platformArray.length; i++) {
			platformArray[i].setX(r.nextInt(6) * 100);
			platformArray[i].setY(170 + r.nextInt(100));
		}
	}

	private void randomizeClouds() {
		for (int i = 0; i < ivArray.length; i++) {
			int l = r.nextInt(2);
			System.out.println(l);
			if (l == 0)
				ivArray[i] = new ImageView(cloudImage1);
			else
				ivArray[i] = new ImageView(cloudImage2);
		}
	}

	@SuppressWarnings("deprecation")
	private void animateBackgroundUsingTimeline() {

		TimelineBuilder.create().cycleCount(Animation.INDEFINITE).keyFrames(

		new KeyFrame(Duration.millis(30), new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				backgroundImageView1.setX(backgroundX);
				backgroundImageView2.setX(WIDTH + backgroundX);
				backgroundX -= backgroundDx;

				System.out.println("X: " + birdX);

				System.out.println("Y: " + birdY);

				System.out.println("dX: " + birdDx);

				System.out.println("dY: " + birdDy);

				for (int i = 0; i < platformArray.length; i++) {
					if (birdY > platformArray[i].getY()) {
						if (birdX > platformArray[i].getX() && birdX < platformArray[i].getX() + 105) {
							gravity =-gravity;
						}

					}
					platformArray[i].setX(platformArray[i].getX() - .85);
					if (platformArray[i].getX() + 104 < 0) {
						platformArray[i].setX(WIDTH + r.nextInt(120));
						platformArray[i].setY(170 + r.nextInt(100));
					}
				}

				updateBird();

				if (backgroundX <= -WIDTH) {
					backgroundX = 0;
				}

				for (int i = 0; i < ivArray.length; i++) {
					ivArray[i].setX(ivArray[i].getX() - 0.2);
					if (ivArray[i].getX() + 137 < 0) {
						ivArray[i].setX(WIDTH + r.nextInt(WIDTH / 3));
						ivArray[i].setY(20 * r.nextInt(5));
					}
				}
			}
		})).build().play();

	}

	public void updateBird() {
		birdX = ((int) (birdImageView.getX() + birdDx));
		birdDy += gravity * dT;
		birdY = (((birdDy * dT) + (.5 * gravity * dT * dT)));
		birdImageView.setX(birdX);
		birdImageView.setY(birdY);
	}

	@SuppressWarnings({ "deprecation", "unused" })
	private void animateBackgroundUsingTransition() {

		backgroundAnimation = TranslateTransitionBuilder.create().node(background).fromX(0).toX(WIDTH)
				.duration(Duration.seconds(8)).onFinished(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						animateBackgroundUsingTransition();
					}
				}).build();
		backgroundAnimation.play();
	}
}
