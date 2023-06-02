package focusflow.Scenes;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import focusflow.Abstract.Main;
import focusflow.Model.Tugas;

public class MainScene extends Main {
    private Timeline timeline;
    private PauseTransition delay;
    private Queue<Tugas> taskQueue = new LinkedList<>();
    private String path;
    private Media media;
    private MediaPlayer mp;
    private TableView<Tugas> tableView;
    private ObservableList<Tugas> tugas;

    public MainScene(Stage primaryStage, String userName) {
        super(primaryStage, userName);
        this.path = "sounds/a.mp3";
        this.media = new Media(new File(path).toURI().toString());
        this.mp = new MediaPlayer(media);
    }

    public void show() {
        VBox root = new VBox();
        Scene scene = new Scene(root, 640, 480);
        scene.getStylesheets().add(getClass().getResource("/CSS/MainStyle.css").toExternalForm());

        scene.getRoot().setStyle("-fx-background-color: #c0c0c0;");

        HBox topBox = generateTopBox();

        tableView = generateTableView();
        tableView.setStyle("-fx-font-size: 14px;");

        TextField input1 = new TextField();
        input1.setPromptText("Nama Tugas");
        TextField input2 = new TextField();
        input2.setPromptText("Timer (H.M.S)");
        HBox hbox = new HBox(input1, input2);
        hbox.setSpacing(10);
        hbox.setAlignment(Pos.CENTER);

        Button btnAdd = new Button("Tambah");
        btnAdd.getStyleClass().add("cool-button");
        btnAdd.setOnAction(v -> {
            String namaTugas = input1.getText();
            String timerString = input2.getText();
            int timer = parseTimerString(timerString);
            if (timer >= 0) {
                Tugas newTugas = new Tugas(namaTugas, timer, timerString);
                tableView.getItems().add(newTugas);
                startTimer(newTugas);

                input1.clear();
                input2.clear();

                taskQueue.add(newTugas);
            } else {
                showAlert("Format waktu tidak valid! Harap masukkan waktu dengan format HH:MM:SS");
            }
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button btnDel = new Button("Hapus");
        btnDel.getStyleClass().add("cool-button");
        btnDel.setOnAction(e -> {
            Tugas selectedTugas = tableView.getSelectionModel().getSelectedItem();
            if (selectedTugas != null) {
                stopTimer();
                tableView.getItems().remove(selectedTugas);
            }
        });

        HBox hBox2 = new HBox();
        hBox2.setSpacing(10);
        hBox2.setAlignment(Pos.CENTER_LEFT);
        hBox2.getChildren().addAll(btnAdd, spacer, btnDel);

        root.getChildren().addAll(topBox, tableView, hbox, hBox2);
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(String string) {
    }

    private int parseTimerString(String timerString) {
        try {
            String[] parts = timerString.split("\\.");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            int seconds = Integer.parseInt(parts[2]);
            return hours * 3600 + minutes * 60 + seconds;
        } catch (Exception e) {
            return -1; // Invalid format
        }
    }

    private HBox generateTopBox() {
        HBox topBox = new HBox();
        topBox.setSpacing(200);

        Button backButton = new Button("Back");
        backButton.getStyleClass().add("cool-button");
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

        tableView.setStyle("-fx-cell-size: 50px;");

        column1.setStyle("-fx-alignment: CENTER;");
        column2.setStyle("-fx-alignment: CENTER;");

        column1.setResizable(true);
        column2.setResizable(true);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return tableView;
    }

    private ObservableList<Tugas> getDummyData() {
        return FXCollections.observableArrayList();
    }

    private void showNextNotification() {
        Tugas nextTugas = taskQueue.poll();
        if (nextTugas != null) {
            startTimer(nextTugas);
        }
    }

    private void suara() {
        this.mp.play();
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
                    showNotification(tugas.getNamaTugas() + " kerja tugas mu sekarang !!!"); // Menampilkan notifikasi setelah timer berakhir
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
                showNotification(userName + " waktunya kerja " + tugas.getNamaTugas() + " mu sekarang !!!"); // Menampilkan notifikasi setelah timer berakhir

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
        alert.setOnCloseRequest(v -> {
            this.mp.stop();
        });

        DialogPane dialogPane = alert.getDialogPane();

        // Membuat ImageView dengan gambar yang ingin ditampilkan
        ImageView imageView = new ImageView(new Image("image/b.gif"));
        suara();
        imageView.setFitWidth(50); // Mengatur lebar gambar
        imageView.setPreserveRatio(true); // Mempertahankan rasio aspek gambar

        // Menambahkan ImageView ke DialogPane
        dialogPane.setGraphic(imageView);

        alert.show();
    }
}
