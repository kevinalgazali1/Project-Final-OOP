package focusflow;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import focusflow.Scenes.HomeScene;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        HomeScene homeScene = new HomeScene(primaryStage);
        homeScene.show();

        primaryStage.setTitle("Focus Flow");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(App.class.getResourceAsStream("/image/logo.png")));    
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
