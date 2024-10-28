package GUI;

import Application.Controller.Controller;
import Application.Model.FadHistorik;
import Storage.Storage;
import Application.Model.Fad;
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

import java.util.ArrayList;

public class DestilleringFadHistorikPåfyld {
    private static FadHistorik fadHistorik;
    static ListView<Fad> fadListview = new ListView<>();

    static TextField udtagelsesVolumenTextField = new TextField();
    static Label lblError = new Label("");


    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Påfyld");


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(lblError, 0, 5);

        Label titleLabel = new Label("Påfyld Fad");
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-font-size: 18px;");
        gridPane.add(titleLabel, 0, 0, 3, 1);
        Label fadLabel = new Label("Fad:");
        fadListview.getItems().setAll(Storage.getInstance().getFade());


        fadListview.setPrefWidth(400);
        fadListview.setPrefHeight(300);
        HBox fadHBox = new HBox(10, fadLabel, fadListview);
        gridPane.add(fadHBox, 0, 1, 2, 1);

        Label udtagelsesVolumenLabel = new Label("Udtagelsesvolumen:");
        HBox udtagelsesVolumenHBox = new HBox(10, udtagelsesVolumenLabel, udtagelsesVolumenTextField);
        gridPane.add(udtagelsesVolumenHBox, 0, 2, 2, 1);

        Button påfyldButton = new Button("Påfyld");
        påfyldButton.setOnAction(e -> {
            påfyldning(fadListview.getSelectionModel().getSelectedItem());
        });
        gridPane.add(påfyldButton, 0, 4);

        Button closeButton = new Button("Luk");

        closeButton.setOnAction(e -> window.close());
        gridPane.add(closeButton, 0, 3);

        Scene scene = new Scene(gridPane, 600, 500);
        window.setScene(scene);
        window.showAndWait();


    }

    public static void påfyldning(Fad fad) {
        if (fadListview.getSelectionModel().isEmpty()) {
            lblError.setStyle("-fx-text-fill: red;");
            lblError.setText("Vælg et fad.");
        }
        else if (udtagelsesVolumenTextField.getText().isEmpty()) {
            lblError.setStyle("-fx-text-fill: red;");
            lblError.setText("Vælg et fad");
        }
        else if (Double.parseDouble(udtagelsesVolumenTextField.getText()) + fad.getNuværendeVolumen() > fad.getVolumen()) {
            lblError.setStyle("-fx-text-fill: red;");
            lblError.setText("Fadet har ikke kapacitet til at tilføje volumen");
        } else if (Double.parseDouble(udtagelsesVolumenTextField.getText()) + fad.getNuværendeVolumen() < fad.getVolumen()) {
            fadListview.getSelectionModel().getSelectedItem().setNuværendeVolumen(Double.parseDouble(udtagelsesVolumenTextField.getText()) + fad.getNuværendeVolumen());
            lblError.setStyle("-fx-text-fill: green;");
            lblError.setText("Fad påfyldt!");
        }
        fadListview.getItems().setAll(Storage.getInstance().getFade());


    }


}
