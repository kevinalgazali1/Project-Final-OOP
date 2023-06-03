package focusflow.Scenes;

import javafx.application.Platform;
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

        Label label2 = new Label("User Name : ");
        label2.getStyleClass().add("label");

        TextField input1 = new TextField();
        input1.setPromptText("Username");

        Image img = new Image("image/mulai.png");
        ImageView view = new ImageView(img);
        view.setFitHeight(20);
        view.setPreserveRatio(true);

        Button bt1 = new Button("ENTER");
        bt1.setStyle(
        "-fx-background-color: linear-gradient(#5cb85c, #4cae4c);" +
        "-fx-text-fill: white;" +
        "-fx-font-weight: bold;" +
        "-fx-font-size: 14px;" +
        "-fx-pref-width: 100px;" +
        "-fx-pref-height: 30px;" +
        "-fx-background-radius: 15px;" +
        "-fx-border-color: transparent;" +
        "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.5) , 0,0,0,1 );"
        );

        bt1.setOnMouseEntered(e -> {
            bt1.setStyle(
            "-fx-background-color: linear-gradient(#4cae4c, #45a945);" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 14px;" +
            "-fx-pref-width: 100px;" +
            "-fx-pref-height: 30px;" +
            "-fx-background-radius: 15px;" +
            "-fx-border-color: transparent;" +
            "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.5) , 0,0,0,1 );"
            );
        });

        bt1.setOnMouseExited(e -> {
            bt1.setStyle(
            "-fx-background-color: linear-gradient(#5cb85c, #4cae4c);" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 14px;" +
            "-fx-pref-width: 100px;" +
            "-fx-pref-height: 30px;" +
            "-fx-background-radius: 15px;" +
            "-fx-border-color: transparent;" +
            "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.5) , 0,0,0,1 );"
            );
        });

        bt1.setOnMousePressed(e -> {
            bt1.setStyle(
            "-fx-background-color: linear-gradient(#45a945, #3d9e3d);" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 14px;" +
            "-fx-pref-width: 100px;" +
            "-fx-pref-height: 30px;" +
            "-fx-background-radius: 15px;" +
            "-fx-border-color: transparent;" +
            "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.5) , 0,0,0,1 );"
            );
        });

        bt1.setOnMouseReleased(e -> {
            bt1.setStyle(
            "-fx-background-color: linear-gradient(#4cae4c, #45a945);" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 14px;" +
            "-fx-pref-width: 100px;" +
            "-fx-pref-height: 30px;" +
            "-fx-background-radius: 15px;" +
            "-fx-border-color: transparent;" +
            "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.5) , 0,0,0,1 );"
            );
        });
        bt1.setCursor(Cursor.HAND);
        bt1.setGraphic(view);

        Button bt2 = new Button("EXIT");
        bt2.setStyle(
        "-fx-background-color: #808080;" +
        "-fx-text-fill: white;" +
        "-fx-font-weight: bold;" +
        "-fx-font-size: 14px;" +
        "-fx-pref-width: 100px;" +
        "-fx-pref-height: 30px;" +
        "-fx-background-radius: 15px;" +
        "-fx-border-color: transparent;" +
        "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.5) , 0,0,0,1 );"
        );

        bt2.setOnMouseEntered(e -> {
            bt2.setStyle(
            "-fx-background-color: #A9A9A9;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 14px;" +
            "-fx-pref-width: 100px;" +
            "-fx-pref-height: 30px;" +
            "-fx-background-radius: 15px;" +
            "-fx-border-color: transparent;" +
            "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.5) , 0,0,0,1 );"
            );
        });

        bt2.setOnMouseExited(e -> {
            bt2.setStyle(
            "-fx-background-color: #808080;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 14px;" +
            "-fx-pref-width: 100px;" +
            "-fx-pref-height: 30px;" +
            "-fx-background-radius: 15px;" +
            "-fx-border-color: transparent;" +
            "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.5) , 0,0,0,1 );"
            );
        });

        bt2.setOnMousePressed(e -> {
            bt2.setStyle(
            "-fx-background-color: #696969;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 14px;" +
            "-fx-pref-width: 100px;" +
            "-fx-pref-height: 30px;" +
            "-fx-background-radius: 15px;" +
            "-fx-border-color: transparent;" +
            "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.5) , 0,0,0,1 );"
            );
        });

        bt2.setOnMouseReleased(e -> {
             bt2.setStyle(
            "-fx-background-color: #A9A9A9;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 14px;" +
            "-fx-pref-width: 100px;" +
            "-fx-pref-height: 30px;" +
            "-fx-background-radius: 15px;" +
            "-fx-border-color: transparent;" +
            "-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.5) , 0,0,0,1 );"
            );
        });
        bt2.setCursor(Cursor.HAND);

        HBox gp1 = new HBox(label2, input1);
        gp1.setSpacing(5);
        gp1.setAlignment(Pos.CENTER);

        HBox gp2 = new HBox(ivLogo, tTitle);
        gp2.setSpacing(25);
        gp2.setAlignment(Pos.CENTER_LEFT);

        VBox v2 = new VBox(gp1, bt1, bt2);
        v2.setAlignment(Pos.CENTER);
        v2.setSpacing(10);
        v2.setPadding(new Insets(50));
        
        VBox v1 = new VBox(gp2, v2);
        v1.setSpacing(50);
        sp.getChildren().add(v1);
        v1.setPadding(new Insets(50));
        v1.setAlignment(Pos.CENTER);
        
        bt1.setOnAction(v -> {
            String userName = input1.getText();
            MainScene mainScene = new MainScene(primaryStage, userName);
            mainScene.show();
        });

        bt2.setOnAction(v -> {
            Thread thread = new Thread(() -> {
            Platform.exit();
            });
            thread.start();
        });

        primaryStage.setScene(scene);
    
}
}