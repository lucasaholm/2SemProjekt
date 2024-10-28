package GUI;

import Application.Model.Destillering;
import Application.Model.Fad;
import Application.Model.FadHistorik;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class DestilleringOmhældGui {

    public static void display(FadHistorik fadHistorik, ObservableList<Fad> fadList) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Omhæld destillering");


        Label destilleringLabel = new Label("Vælg Destillering");
        ComboBox<Destillering> destilleringComboBox = new ComboBox<>();
        //Her skal gøres sådan at destilleringBoxen udfyldes med destillernger

        Label fraFadLabel = new Label("Vælg fra Fad");
        ComboBox<Fad> FraFadComboBox = new ComboBox<>();

        Label tilfadLabel = new Label("Vælg til fad");
        ComboBox<Fad> TilfadComboBox = new ComboBox<>();

        Label datoLabel = new Label("Vælg dato");
        DatePicker datePicker = new DatePicker();

        Label udtagelsesVolumenLabel = new Label("UdtagelsesVolumnen");
        TextField udtagelsvolumenField = new TextField();

        Button omhældBtn = new Button("Omhæld destillering");
        omhældBtn.setOnAction(e -> {
            Fad fraFad = FraFadComboBox.getValue();
            Fad tilFad = TilfadComboBox.getValue();
            Destillering destillering = destilleringComboBox.getValue();
            double udtagelsesVolumen = Double.parseDouble(udtagelsvolumenField.getText());

        });

    }
}

