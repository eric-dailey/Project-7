package testpack.view;

import testpack.Critter;
import testpack.InvalidCritterException;
import testpack.Params;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class ChoiceBoxController {

	private static String myPackage;
	static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }
	
	@FXML
	private ChoiceBox<String> animateChoice;
	@FXML
	private TextField makeChoice;
	@FXML
	private TextField makeText;
	@FXML
	private Button makeButt;
	@FXML
	private Button animateButt;
	@FXML
	private Button stopButt;
	@FXML
	private Button statsButt;
	@FXML
	private Button stepButt;
	@FXML
	private TextField stepText;
	@FXML
	private Button seedButt;
	@FXML
	private TextField seedText;
	@FXML
	private Button quitButt;
	@FXML
	private TextField statsChoice;
	@FXML
	private GridPane gridpane;
	@FXML
	private TextArea textarea;
	
	
	public static double width = (550)/Params.world_width;
	public static double height = (550)/Params.world_height;
	ObservableList<String> animationspeeds = FXCollections.observableArrayList("- select speed -","1","2","5","10","20","50","100");
	
	
	public static boolean animation_flag;
	public void animationTasks(){
		
    	Critter.worldTimeStep();
		Critter.displayWorld(gridpane);
	}
	
	
	
	@FXML
	private void initialize(){
		gridpane.setHgap(-1/*550/Params.world_width*/);
		gridpane.setVgap(-1/*550/Params.world_height*/);
		gridpane.getColumnConstraints().clear();
		gridpane.getRowConstraints().clear();
		gridpane.getChildren().clear();
		animation_flag = false;
		
		for (int i = 0; i < Params.world_width; i++) {
			for (int j = 0; j < Params.world_height; j++) {
				Shape s = new Rectangle(width, height);
				s.setFill(null);
				s.setStroke(Color.BLACK);
				gridpane.add(s, i, j);
			}
		}		
		 
		animateChoice.setItems(animationspeeds);
		animateChoice.setValue("- select speed -");
		

		quitButt.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	System.exit(0);
		    }
		});
		
		/*animateButt.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	animation_flag = true;
		    	new Thread(new Runnable(){

					@Override
					public void run() {
						while(ChoiceBoxController.animation_flag){
							animationTasks();
							try {
								int speed = 0;
								speed = Integer.valueOf(speed); 
								animationTasks();
								Thread.sleep(1000/speed);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
						
					}}).start();
		    	new Thread(new Runnable() {
		    		@Override public void run() {
		    			
				    	while(animation_flag){
				    		 Platform.runLater(new Runnable() {
				    	            @Override public void run() {
				    	            	int speed = 0;
								    	try{  speed = Integer.valueOf(animateChoice.getValue()); }
								    	catch(NumberFormatException e1){
								    		animation_flag = false;
								    	}
				    	            	Critter.worldTimeStep();
							    		Critter.displayWorld(gridpane);
							    		try {
											Thread.sleep(1000/speed);
										} catch (InterruptedException e1) {
											e1.printStackTrace();
										}
				    	            }
				    	        });
				    		
				    	}
		    		}
		    	}).start();
		    }
		});
		stopButt.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	animation_flag = false;
		    }
		});*/
		
		
		makeButt.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	String critter_name = makeChoice.getText();
		    	String critter_amount = makeText.getText();
		    	int count;
		    	if(critter_amount.contentEquals("")) count = 1;
		    	else count = Integer.valueOf(critter_amount);
		    	for(int i = 0; i < count; i++){
    				try {
						Critter.makeCritter(critter_name);
					} catch (InvalidCritterException e1) {
						e1.printStackTrace();
					}
    			}
		    	makeChoice.clear(); makeText.clear();
		    	Critter.displayWorld(gridpane);
		    	
		    	String critter_name1 = statsChoice.getText();
				try{
				java.lang.reflect.Method method = Class.forName(myPackage + "." + critter_name1).getMethod("runStats", java.util.List.class);
				List<Critter> list = Critter.getInstances(critter_name1);
				textarea.clear();
				textarea.setText((String) method.invoke(Class.forName(myPackage + "." + critter_name1), list));
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
		    }
		});
		
		
		
		statsButt.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				String critter_name = statsChoice.getText();
				try{
				java.lang.reflect.Method method = Class.forName(myPackage + "." + critter_name).getMethod("runStats", java.util.List.class);
				List<Critter> list = Critter.getInstances(critter_name);
				textarea.clear();
				textarea.setText((String) method.invoke(Class.forName(myPackage + "." + critter_name), list));
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		
		seedButt.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				String seed = seedText.getText();
				long seed_number = Long.valueOf(seed);
				Critter.setSeed(seed_number);
				seedText.clear();
			}
		
		});
		
		stepButt.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				String step = stepText.getText();
				int count;
				if(step.contentEquals("")) count = 1;
				else count = Integer.valueOf(step);
				for(int i = 0; i < count; i++){
					Critter.worldTimeStep();
				}
				Critter.displayWorld(gridpane);
				
				String critter_name = statsChoice.getText();
				try{
				java.lang.reflect.Method method = Class.forName(myPackage + "." + critter_name).getMethod("runStats", java.util.List.class);
				List<Critter> list = Critter.getInstances(critter_name);
				textarea.clear();
				textarea.setText((String) method.invoke(Class.forName(myPackage + "." + critter_name), list));
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		
	}
	

	
	
	
	
}
