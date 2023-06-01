package focusflow.Scenes;

import java.util.LinkedList;
import java.util.Queue;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import focusflow.Model.Tugas;

public class MainScene {
    private Stage primaryStage;
    private Queue<Tugas> taskQueue = new LinkedList<>();


    public MainScene(Stage primaryStage, String userName) {
        this.primaryStage = primaryStage;

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


}