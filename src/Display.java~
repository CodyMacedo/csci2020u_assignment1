/*
 * Display
 *
 * This class uses Javafx to render a ui table among other fields that will
 * display the data for the other classes in this project
 * Additionally, this class is also used to prompt the user for folder selection
 * at the beginning of the program
 * 
 * @author	CodyMacedo	Omar Khan
 * @id		100486136	100523629
 * @date	March 10, 2016
 *
 */

import java.io.*;
import java.util.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.image.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.*;
import javafx.stage.DirectoryChooser;


public class Display extends Application {
    public Stage window;
    private BorderPane layout;
    private TableView<TestFile> table;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Spam Master 3000");
        
        
        /* get file from user with gui prompt */
        DirectoryChooser directoryChooser = new DirectoryChooser();
   		directoryChooser.setInitialDirectory(new File("."));
   		File mainDirectory = directoryChooser.showDialog(primaryStage);
        
        
        /* create the table (for the center of the user interface) */
        DataSource dataSource = new DataSource();
        dataSource.calculateData(mainDirectory);
        table = new TableView<>();
        table.setItems(dataSource.getData());
        table.setEditable(false);


        /* create the table's columns */
        TableColumn<TestFile,String> filenameColumn = null;
        filenameColumn = new TableColumn<>("File");
        filenameColumn.setMinWidth(350);
        filenameColumn.setCellValueFactory(new PropertyValueFactory<>("filename"));

        TableColumn<TestFile,String> actualClassColumn = null;
        actualClassColumn = new TableColumn<>("Actual Class");
        actualClassColumn.setMinWidth(150);
        actualClassColumn.setCellValueFactory(new PropertyValueFactory<>("actualClass"));

        TableColumn<TestFile,Double> spamProbColumn = null;
        spamProbColumn = new TableColumn<>("Spam Probablity");
        spamProbColumn.setMinWidth(300);
        spamProbColumn.setCellValueFactory(new PropertyValueFactory<>("spamProbability"));

        table.getColumns().add(filenameColumn);
        table.getColumns().add(actualClassColumn);
        table.getColumns().add(spamProbColumn);
        
        
        /* create accuracy and precision labels */
        GridPane displayArea = new GridPane();
        displayArea.setPadding(new Insets(10, 10, 10, 10));
        displayArea.setVgap(10);
        displayArea.setHgap(10);

        Label accuracyLabel = new Label("Accuracy:");
        displayArea.add(accuracyLabel, 0, 0);
        TextField accuracyField = new TextField();
       	accuracyField.setEditable(false);
        displayArea.add(accuracyField, 1, 0);

        Label precisionLabel = new Label("Precision:");
        displayArea.add(precisionLabel, 0, 1);
        TextField precisionField = new TextField();
        precisionField.setEditable(false);
        displayArea.add(precisionField, 1, 1);
        
        
        /* set layout and display */
        layout = new BorderPane();
        layout.setCenter(table);
        layout.setBottom(displayArea);
        
        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void launchDisplay(){
    	launch();
    }
}
