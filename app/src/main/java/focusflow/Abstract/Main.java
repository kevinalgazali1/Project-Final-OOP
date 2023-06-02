package focusflow.Abstract;

import javafx.stage.Stage;

public abstract class Main {
    protected Stage primaryStage;
    protected String userName;

    public Main(Stage primaryStage, String userName) {
        this.primaryStage = primaryStage;
        this.userName = userName;
    }

    public abstract void show();
}
