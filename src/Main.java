//2%

//Added imports for Application parent class, staging, scenes, and a button class
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;

//Class extends applicatoin
public class Main extends Application{
        
        //@Override // start method in application class
        public void start(Stage primaryStage){
            //Create a scene and place the button in the scene
            Scene twoPercent = new Scene(new Button("2%"),250,250);
            primaryStage.setTitle("2%");
            primaryStage.setScene(twoPercent); //Sets scene in stage
            primaryStage.show();//Display stage
        }
        
	public static void main(String args[]) {
		launch(args);//Launch arguments
	}

	public Main() {
		System.out.println("2%");
	}
}
