package focusflow.Scenes;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import focusflow.Abstract.Home;

public class HomeScene extends Home {

    public HomeScene(Stage primaryStage) {
        super(primaryStage);
    }

    public void show() {
        StackPane sp = new StackPane();
        Scene scene = new Scene(sp, 640, 480);
        scene.getStylesheets().add(getClass().getResource("/CSS/HomeStyle.css").toExternalForm());

        ImageView ivBg = new ImageView("/image/bg.png");
        ivBg.setFitWidth(scene.getWidth());
        ivBg.setFitHeight(scene.getHeight());
        sp.getChildren().add(ivBg);

        Text tLeft = new Text("FOCUS");
        Text tRight = new Text("FLOW");
        tLeft.getStyleClass().add("text-left");
        tRight.getStyleClass().add("text-right");
        TextFlow tTitle = new TextFlow();
        tTitle.getChildren().addAll(tLeft, new Text("     "), tRight);



        ImageView ivLogo = new ImageView("/image/logo.png");
        ivLogo.setFitWidth(50);
        ivLogo.setFitHeight(50);

        Rectangle clip = new Rectangle(ivLogo.getFitWidth(), ivLogo.getFitHeight());
        clip.setArcWidth(20); // Mengatur lebar sudut tumpul
        clip.setArcHeight(20); // Mengatur tinggi sudut tumpul

        ivLogo.setClip(clip);

        sp.getChildren().add(ivLogo);


        Label label1 = new Label(
                "Hello, welcome to Focus Flow");
        label1.getStyleClass().add("welcome-text");
        label1.setWrapText(true);
        label1.setMaxWidth(355);

        Label label2 = new Label("User Name : ");
        label2.getStyleClass().add("label");


        TextField input1 = new TextField();
        input1.setPromptText("Username");

        Image img = new Image("image/mulai.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(20);
        view.setPreserveRatio(true);

        Button bt1 = new Button("ENTER");
        bt1.getStyleClass().add("cool-button");
        bt1.setCursor(Cursor.HAND);
        bt1.setGraphic(view);

        
        HBox gp1 = new HBox(label2, input1);
        gp1.setSpacing(5);
        gp1.setAlignment(Pos.CENTER);

        HBox gp2 = new HBox(ivLogo, tTitle);
        gp2.setSpacing(25);
        gp2.setAlignment(Pos.CENTER_LEFT);
        
        VBox v1 = new VBox(gp2, label1, gp1, bt1);
        v1.setSpacing(10);
        sp.getChildren().add(v1);
        v1.setPadding(new Insets(50));
        v1.setAlignment(Pos.CENTER);
        
        bt1.setOnAction(v -> {
            String userName = input1.getText();
            MainScene mainScene = new MainScene(primaryStage, userName);
            mainScene.show();
        });

        primaryStage.setScene(scene);
    }
}
