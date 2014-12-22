import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class Loops extends Main {
	private boolean running;

	private final int WIDTH;
	private final int HEIGHT;

	public Loops() {

		// THIS CODE IS GARBAGE

		running = true;
		WIDTH = HEIGHT = 200;

		Group root = new Group();
		Canvas canvas = new Canvas(200, 200);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		Task<Integer> task = new Task<Integer>() {
			@Override
			protected Integer call() throws Exception {
				long lastTime = System.nanoTime();
				double tps = 1000000000D / 60;

				int ticks = 0;
				int frames = 0;

				long lastTimer = System.currentTimeMillis();
				double delta = 0;

				while (running) {
					long now = System.nanoTime();
					boolean shouldRender = false;
					delta += (now - lastTime) / tps;
					lastTime = now;
					while (delta >= 1) {
						ticks++;
						delta -= 1;
						shouldRender = true;
					}

					if (shouldRender) {
						frames++;
						update();
					}
					if (System.currentTimeMillis() - lastTimer >= 1000) {
						lastTimer += 1000;
						System.out.println(frames + ":" + ticks);
						frames = 0;
						ticks = 0;
					}
					render();
					drawBackground(gc);
				}
				return null;
			}
		};

		new Thread(task).start();

		Stage s = new Stage();
		s.setTitle("Animation");
		s.setWidth(WIDTH);
		s.setHeight(HEIGHT);
		s.setResizable(false);

		root.getChildren().add(canvas);
		s.setScene(new Scene(root));

		s.show();

		s.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				running = false;
			}
		});

	}

	public void drawBackground(GraphicsContext gc) {
		gc.setFill(Color.CYAN);
		gc.fillRect(0, 0, WIDTH, HEIGHT);

	}

	public void update() {
		System.out.println("GG");
	}

	public void render() {

	}

}
