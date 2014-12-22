import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Basis {

	private final int WIDTH = 320;
	private final int HEIGHT = 240;

	public Basis() {
		Stage s = new Stage();
		s.show();
		s.setWidth(WIDTH);
		s.setHeight(HEIGHT);
		s.setResizable(false);
		s.setTitle("Basis");
		s.show();

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Scene scene = new Scene(grid, WIDTH, HEIGHT);
		s.setScene(scene);

		Text title = new Text("2%, what is your name?");
		title.setFont(new javafx.scene.text.Font("Tahoma", 24));

		grid.add(title, 1, 0, 2, 1);

		Label username = new Label();
		username.setText("Username:");
		grid.add(username, 1, 1);

		TextField tf = new TextField();
		grid.add(tf, 2, 1);

		Label password = new Label();
		password.setText("Password:");
		grid.add(password, 1, 2);

		PasswordField pf = new PasswordField();
		grid.add(pf, 2, 2);

	}
}
