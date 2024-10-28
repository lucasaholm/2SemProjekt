package GUI;

import Application.Controller.Controller;
import Application.Model.*;
import Storage.Storage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.time.LocalDate;

public class FadHistorikGui extends BorderPane {

    private final Controller controller = new Controller(Storage.getInstance());

    private GridPane destilleringGrid;
    private TextField idTextField, navnTextField;
    private DatePicker påfyldningsDato;
    private ComboBox<Destillering> destilleringComboBox;
    private Button opretFadHistorikbtn, sletFadHistorikbtn, btnAddFad, btnFyldAlleFade;
    private ListView<Fad> fadeListView;
    private ListView<FadHistorik> FadHistorikListView;
    private Label lblAddFad;



    public FadHistorikGui() {
        createDestilleringGrid();
    }


    private void createDestilleringGrid() {
        destilleringGrid = new GridPane();
        destilleringGrid.setHgap(10);
        destilleringGrid.setVgap(10);
        destilleringGrid.setPadding(new Insets(10, 10, 10, 10));
        destilleringGrid.setAlignment(Pos.CENTER);


        Label idLabel = new Label("ID:");
        destilleringGrid.add(idLabel, 0, 0);
        idTextField = new TextField();
        destilleringGrid.add(idTextField, 1, 0);

        Label navnLabel = new Label("UdtagelsesVolumen:");
        destilleringGrid.add(navnLabel, 0, 1);
        navnTextField = new TextField();
        destilleringGrid.add(navnTextField, 1, 1);

        Label påfyldningsDatoLabel = new Label("påfyldningsdato:");
        destilleringGrid.add(påfyldningsDatoLabel, 0, 3);
        påfyldningsDato = new DatePicker();
        destilleringGrid.add(påfyldningsDato, 1, 3);

        Label destilleringLabel = new Label("Destilleringer:");
        destilleringGrid.add(destilleringLabel, 2, 10);
        destilleringComboBox = new ComboBox<>();
        destilleringGrid.add(destilleringComboBox, 1, 10);
        ObservableList<Destillering> des = FXCollections.observableArrayList();
        des.addAll(Storage.getInstance().getDestilleringer());
        destilleringComboBox.setItems(des);

        opretFadHistorikbtn = new Button("Opret");
        sletFadHistorikbtn = new Button("Slet");
        btnAddFad = new Button("Tilføj fad");
        lblAddFad = new Label("");
        btnFyldAlleFade = new Button("Fyld fade");



        // Tilføj hvert medarbejdernavn til ComboBoxen
        HBox inputFieldsHBox1 = new HBox(20); // 20 er afstanden i HBox
        inputFieldsHBox1.setAlignment(Pos.CENTER);
        inputFieldsHBox1.getChildren().addAll(
                createInputFieldVBox(idLabel, idTextField),
                createInputFieldVBox(navnLabel, navnTextField),
                createInputFieldVBox(påfyldningsDatoLabel, påfyldningsDato),
                createInputFieldVBox(destilleringLabel, destilleringComboBox)
        );


        //Action når man trykker på knappen
        opretFadHistorikbtn.setOnAction(e -> {
            if (idTextField.getText().isEmpty() || navnTextField.getText().isEmpty() || påfyldningsDato.getValue() == null) {
                // Vis en fejlmeddelelse hvis nogen af inputfelterne er tomme
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fejl");
                alert.setHeaderText("Ugyldig input");
                alert.setContentText("Udfyld alle felter og prøv igen.");
                alert.showAndWait();
            } else {
                try {
                    // Opret en ny FadHistorik instans ved hjælp af Controller metoden
                    int id = Integer.parseInt(idTextField.getText());
                    int udtagelsesVolumen = Integer.parseInt(navnTextField.getText());
                    LocalDate dato = påfyldningsDato.getValue();
                    Destillering destillering = destilleringComboBox.getValue();
                    FadHistorik nyFadHistorik = controller.createFadhistorik(id, udtagelsesVolumen, dato, destillering);

                    // Opdater fadHistorikListView
                    FadHistorikListView.getItems().setAll(Storage.getInstance().getFadHistorikker());


                    idTextField.clear();
                    navnTextField.clear();
                    påfyldningsDato.setValue(null);
                    ObservableList<Destillering> destil = FXCollections.observableArrayList();
                    destil.addAll(Storage.getInstance().getDestilleringer());
                    destilleringComboBox.setItems(destil);
                } catch (NumberFormatException ex) {

                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Fejl");
                    alert.setHeaderText("Ugyldig input");
                    alert.setContentText("Indtast venligst et gyldigt tal for ID og udtagelsesvolumen.");
                    alert.showAndWait();
                }
            }
        });

        sletFadHistorikbtn.setOnAction(e -> {
            FadHistorik valgtFadHistorik = FadHistorikListView.getSelectionModel().getSelectedItem();

            if (valgtFadHistorik != null) {
                Storage.getInstance().removeFadHistorik(valgtFadHistorik);
                FadHistorikListView.getItems().setAll(Storage.getInstance().getFadHistorikker());
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Fejl");
                alert.setHeaderText("Ingen FadHistorik valgt");
                alert.setContentText("Vælg venligst en FadHistorik fra listen for at slette den");
                alert.setTitle("Fejl");
                alert.setHeaderText("Ingen FadHistorik valgt");
                alert.setContentText("Vælg venligst en FadHistorik fra listen for at slette den.");
                alert.showAndWait();
            }
        });

        btnAddFad.setOnAction(e -> addFadMetode());
        btnFyldAlleFade.setOnAction(e -> FyldFade());

        HBox buttonBox = new HBox(10, opretFadHistorikbtn, sletFadHistorikbtn, btnAddFad, btnFyldAlleFade, lblAddFad);
        buttonBox.setAlignment(Pos.BASELINE_LEFT);
        destilleringGrid.add(buttonBox, 0, 11, 3, 1);

        VBox inputFieldsVBox = new VBox(10); // 10 er afstanden mellem børnene i VBox
        inputFieldsVBox.setAlignment(Pos.CENTER);
        inputFieldsVBox.getChildren().addAll(inputFieldsHBox1);

        destilleringGrid.add(inputFieldsVBox, 0, 0, 2, 1);

        Label fadhistorikLabel = new Label("Fadhistorikker:");
        FadHistorikListView = new ListView<>();
        FadHistorikListView.setPrefWidth(450);
        FadHistorikListView.setPrefHeight(200);
        ObservableList<FadHistorik> fadHistoriks = FXCollections.observableArrayList();
        fadHistoriks.addAll(Storage.getInstance().getFadHistorikker());
        FadHistorikListView.setItems(fadHistoriks);

        Label fadLabel = new Label("Fade:");
        fadeListView = new ListView<>();
        fadeListView.setPrefWidth(450);
        fadeListView.setPrefHeight(200);
        ObservableList<Fad> fade = FXCollections.observableArrayList();
        fade.addAll(Storage.getInstance().getFade());
        fadeListView.setItems(fade);


        // Opret en VBox for at holde fadHistorikLabel og fadHistorikListView
        VBox fadHistorikVBox = new VBox(5);
        fadHistorikVBox.setAlignment(Pos.CENTER);
        fadHistorikVBox.getChildren().addAll(fadhistorikLabel, FadHistorikListView);

        // Opret en VBox for at holde fadLabel og fadeListView
        VBox fadVBox = new VBox(5);
        fadVBox.setAlignment(Pos.CENTER);
        fadVBox.getChildren().addAll(fadLabel, fadeListView);


        // Opret en HBox for at holde fadeTableView og destilleringTableView
        HBox listViewHBox = new HBox(10);
        listViewHBox.setAlignment(Pos.CENTER);
        listViewHBox.getChildren().addAll(fadHistorikVBox, fadVBox);

        // Tilføj tableViewsHBox til destilleringGrid
        destilleringGrid.add(listViewHBox,0,12,2,1);




    }

    private void FyldFade() {
        if (!FadHistorikListView.getSelectionModel().isEmpty()) {
            FadHistorik fh = FadHistorikListView.getSelectionModel().getSelectedItem();
            fh.påfyldFade();
            lblAddFad.setStyle("-fx-text-fill: green;");
            lblAddFad.setText("alle fadene for den valgte fadhistorik er påfyldt");
        } else {
            lblAddFad.setStyle("-fx-text-fill: red;");
            lblAddFad.setText("vælg en fadhistorik");
        }
        ObservableList<Fad> fade = FXCollections.observableArrayList();
        fade.addAll(Storage.getInstance().getFade());
        fadeListView.setItems(fade);
        ObservableList<FadHistorik> fadHistoriks = FXCollections.observableArrayList();
        fadHistoriks.addAll(Storage.getInstance().getFadHistorikker());
        FadHistorikListView.setItems(fadHistoriks);
    }

    private void addFadMetode() {
        if (!fadeListView.getSelectionModel().isEmpty() && !FadHistorikListView.getSelectionModel().isEmpty()) {
            Fad fad = fadeListView.getSelectionModel().getSelectedItem();
            FadHistorik fh = FadHistorikListView.getSelectionModel().getSelectedItem();
            fh.addFad(fad);
            lblAddFad.setStyle("-fx-text-fill: green;");
            lblAddFad.setText("Fadet er nu tilføjet");
        } else {
            lblAddFad.setStyle("-fx-text-fill: red;");
            lblAddFad.setText("vælg en fadhistorik og et fad");
        }
        ObservableList<Fad> fade = FXCollections.observableArrayList();
        fade.addAll(Storage.getInstance().getFade());
        fadeListView.setItems(fade);
        ObservableList<FadHistorik> fadHistoriks = FXCollections.observableArrayList();
        fadHistoriks.addAll(Storage.getInstance().getFadHistorikker());
        FadHistorikListView.setItems(fadHistoriks);

    }


    private VBox createInputFieldVBox(Label label, Control control) {
        VBox vbox = new VBox(5); // 5 er afstanden mellem børnene i VBox
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(label, control);
        return vbox;
    }



    public GridPane getFadHistorikGrid() {
        return destilleringGrid;
    }


}