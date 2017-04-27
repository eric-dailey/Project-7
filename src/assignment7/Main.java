package assignment7;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Stage primaryStage;
	private AnchorPane mainLayout;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Chat");
		try {
			showMainView();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
	
	private void showMainView() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/ChatGUI.fxml"));
		mainLayout = loader.load();
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
