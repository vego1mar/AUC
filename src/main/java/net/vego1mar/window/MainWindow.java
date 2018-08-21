package net.vego1mar.window;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public class MainWindow extends Application {

    private static final Logger log = Logger.getLogger(MainWindow.class);

    public static void main(String[] args) {
        log.info("Application started.");
        launch(args);
        log.info("Application exited.");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainWindow/MainWindow.fxml"));
        primaryStage.setTitle("AppsUpdatesCollector");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
}
