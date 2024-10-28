package GUI;

import Application.Controller.Controller;
import Application.Model.Destillering;
import Application.Model.Fad;
import Application.Model.FadHistorik;
import Storage.Storage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

public class DestilleringFadHistorikOmhæld {
    static Controller controller;
    static Button omhældBtn = new Button("Omhæld");
    static ListView<Fad> fadListview = new ListView<>();
    static TextField udtagelsesVolumetxf = new TextField();
    static ListView<Fad> nyFadListView = new ListView<>();
    static Label lblError = new Label("");
    static FadHistorik fadHistorik;
    static Destillering destillering;

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Omhæld");


        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(lblError, 0, 9);

        Label titleLabel = new Label("Omhæld fra");
        Label titleLabel2 = new Label("Omhæld til");
        titleLabel.setAlignment(Pos.BASELINE_RIGHT);
        titleLabel.setStyle("-fx-font-size: 16px;");
        titleLabel2.setStyle("-fx-font-size: 16px;");
        gridPane.add(titleLabel, 0, 0, 3, 1);
        gridPane.add(titleLabel2, 5, 0, 3, 1);
        Label fadLabel = new Label("Fad:");
        Label fadLabel2 = new Label("Fad:");

        Label udtagelsesVolume = new Label("Udtagelses volume: ");
        gridPane.add(udtagelsesVolume, 0, 6);
        gridPane.add(udtagelsesVolumetxf, 1, 6);


        omhældBtn.setOnAction(e -> omhældSpiritus());
        gridPane.add(omhældBtn, 0, 8);

        nyFadListView.getItems().setAll(Storage.getInstance().getFade());
        nyFadListView.setPrefWidth(400);
        nyFadListView.setPrefHeight(300);

        fadListview.getItems().setAll(Storage.getInstance().getFade());
        fadListview.setPrefWidth(400);
        fadListview.setPrefHeight(300);


        HBox fadHBox = new HBox(10, fadLabel, fadListview);


        HBox fad2HBox = new HBox(10, fadLabel2, nyFadListView);
        gridPane.add(fad2HBox, 5, 1, 2, 1);

        gridPane.add(fadHBox, 0, 1, 2, 1);


        Scene scene = new Scene(gridPane, 1100, 600);
        window.setScene(scene);
        window.show();
    }

    public static void omhældSpiritus() {
        if (fadListview.getSelectionModel().isEmpty()) {
            lblError.setStyle("-fx-text-fill: red;");
            lblError.setText("Vælg et fad.");
        }
        if (nyFadListView.getSelectionModel().isEmpty()) {
            lblError.setStyle("-fx-text-fill: red;");
            lblError.setText("Vælg et fad.");
        } else if (udtagelsesVolumetxf.getText().isEmpty()) {
            lblError.setStyle("-fx-text-fill: red;");
            lblError.setText("Skriv en udtagelses volume.");

        }
        else if (nyFadListView.getSelectionModel().getSelectedItem().getVolumen() < nyFadListView.getSelectionModel().getSelectedItem().getNuværendeVolumen() + Double.parseDouble(udtagelsesVolumetxf.getText())) {
            lblError.setStyle("-fx-text-fill: red;");
            lblError.setText("Det er ikke plads i fadet");
            udtagelsesVolumetxf.clear();
        } else if (Double.parseDouble(udtagelsesVolumetxf.getText()) + nyFadListView.getSelectionModel().getSelectedItem().getNuværendeVolumen() < nyFadListView.getSelectionModel().getSelectedItem().getVolumen()) {
            fadListview.getSelectionModel().getSelectedItem();
            //nyFadListView.getSelectionModel().getSelectedItem().setFadHistorik(fadHistorik);
            double nyVolumeNytFad = Double.parseDouble(udtagelsesVolumetxf.getText()) + nyFadListView.getSelectionModel().getSelectedItem().getNuværendeVolumen();
            double nyVolumeGammeltFad = fadListview.getSelectionModel().getSelectedItem().getNuværendeVolumen() - Double.parseDouble(udtagelsesVolumetxf.getText());
            fadListview.getSelectionModel().getSelectedItem().setNuværendeVolumen(nyVolumeGammeltFad);
            nyFadListView.getSelectionModel().getSelectedItem().setNuværendeVolumen(nyVolumeNytFad);
            lblError.setStyle("-fx-text-fill: green;");
            lblError.setText("Væske omhældt!");

            udtagelsesVolumetxf.clear();
            fadListview.getItems().setAll(Storage.getInstance().getFade());
            nyFadListView.getItems().setAll(Storage.getInstance().getFade());
        }
    }

}
