/**
 * Entry point for the Flöskumóttaka application.
 * Initializes and displays the user interface.
 */
package hi.verkefni.vidmot.flaskumottaka;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FloskurApplication extends Application {

    /**
     * Starts the JavaFX application by loading the FXML layout and setting the stage.
     *
     * @param stage the main stage for the application
     * @throws Exception if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(loader.load(), 600, 600);
        stage.setTitle("Flöskumóttaka");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method to launch the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
