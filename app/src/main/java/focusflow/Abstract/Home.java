package focusflow.Abstract;

import javafx.stage.Stage;

public abstract class Home {
    protected Stage primaryStage;

    public Home(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public abstract void show();
}