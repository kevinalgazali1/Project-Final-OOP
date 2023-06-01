package focusflow.Scenes;

import java.util.LinkedList;
import java.util.Queue;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import focusflow.Model.Tugas;

public class MainScene {
    private Stage primaryStage;
    private String userName;
    private Timeline timeline;
    private PauseTransition delay;
    private Queue<Tugas> taskQueue = new LinkedList<>();


    public MainScene(Stage primaryStage, String userName) {
        this.primaryStage = primaryStage;
        this.userName = userName;

    }

        public void show(){

        VBox root = new VBox();
        Scene scene = new Scene(root, 640, 480);
        scene.getStylesheets().add(getClass().getResource("/CSS/MainStyle.css").toExternalForm());

        HBox topBox = generateTopBox();

        TableView<Tugas> tableView = generateTableView();
        

        TextField input1 = new TextField();
        input1.setPromptText("Nama Tugas");
        TextField input2 = new TextField();
        input2.setPromptText("Timer");
        HBox hbox = new HBox(input1, input2);
        


        Button btnAdd = new Button("Tambah");
        btnAdd.setOnAction(v -> {
            Tugas newTugas = new Tugas(input1.getText(), Integer.parseInt(input2.getText()));
            tableView.getItems().add(newTugas);
            startTimer(newTugas);

            input1.clear();
            input2.clear();

            taskQueue.add(newTugas);
        });

       

        root.getChildren().addAll(topBox, tableView, hbox, btnAdd);
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox generateTopBox() {
        HBox topBox = new HBox();
        topBox.setSpacing(225);

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            HomeScene homeScene = new HomeScene(primaryStage);
            homeScene.show();
        });

        Label titleLabel = new Label("Data Tugas");
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        topBox.getChildren().addAll(backButton, titleLabel);
        return topBox;
    }

    private TableView<Tugas> generateTableView() {
        TableView<Tugas> tableView = new TableView<>();

        TableColumn<Tugas, String> column1 = new TableColumn<>("Nama Tugas");
        TableColumn<Tugas, Integer> column2 = new TableColumn<>("Timer");

        column1.setCellValueFactory(new PropertyValueFactory<>("namaTugas"));
        column2.setCellValueFactory(new PropertyValueFactory<>("timer"));

        tableView.getColumns().addAll(column1, column2);
        tableView.setItems(getDummyData());

        column1.setResizable(true);
        column2.setResizable(true);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



        return tableView;
    }

    private ObservableList<Tugas> getDummyData() {
        return FXCollections.observableArrayList();
    }
    
    private ObservableList<Tugas> getNewDummyData() {
        return FXCollections.observableArrayList();
    }

    private void showNextNotification() {
        Tugas nextTugas = taskQueue.poll();
        if (nextTugas != null) {
            startTimer(nextTugas);
        }
    }

    private void startTimer(Tugas tugas) {
        if (timeline != null && timeline.getStatus() == Timeline.Status.RUNNING) {
            timeline.stop();
        }
        if (delay != null && delay.getStatus() == javafx.animation.Animation.Status.RUNNING) {
            delay.stop();
        }

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (tugas.getTimer() > 0) {
                tugas.decrementTimer();
            } else {
                if (!tugas.isNotificationShown()) {
                    showNotification(tugas.getNamaTugas() + " kerja tugas mu memek"); // Menampilkan notifikasi setelah timer berakhir
                    tugas.setNotificationShown(true);
                }
                stopTimer();
                showNextNotification();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        delay = new PauseTransition(Duration.seconds(tugas.getTimer()));
        delay.setOnFinished(event -> {
            timeline.stop();
            if (!tugas.isNotificationShown()) {
                showNotification(userName + " kerja tugas " + tugas.getNamaTugas() + "mu sekarang !!!"); // Menampilkan notifikasi setelah timer berakhir

                tugas.setNotificationShown(true);
            }
            showNextNotification();
        });
        delay.play();
    }

    private void stopTimer() {
        if (timeline != null) {
            timeline.stop();
        }
        if (delay != null) {
            delay.stop();
        }
    }

    private void showNotification(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notifikasi");
        alert.setHeaderText(null);
        alert.setContentText(message);

        DialogPane dialogPane = alert.getDialogPane();

    // Membuat ImageView dengan gambar yang ingin ditampilkan
    ImageView imageView = new ImageView(new Image("image/b.gif"));
    imageView.setFitWidth(50); // Mengatur lebar gambar
    imageView.setPreserveRatio(true); // Mempertahankan rasio aspek gambar

    // Menambahkan ImageView ke DialogPane
    dialogPane.setGraphic(imageView);
    
        alert.show();  

    }
}