package GUI;

import Application.Model.Destillering;
import Application.Model.Fad;
import Application.Model.FadHistorik;
import Application.Model.Spiritus;
import Storage.Storage;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;


public class DestilleringFadHistorikHistorie {
    private static FadHistorik fadHistorik;
    static ListView<Spiritus> spiritusListView = new ListView<>();
    static Label lblError = new Label("");


    public static void display() {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Spiritus");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(lblError, 0,4);

        Label titleLabel = new Label("Udtræk Historie");
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-font-size: 18px;");
        gridPane.add(titleLabel, 0, 0, 3, 1);

        Label fadLabel = new Label("Spiritus:");

        spiritusListView.getItems().setAll(Storage.getInstance().getSpiritusser());




        spiritusListView.setPrefWidth(400);
        spiritusListView.setPrefHeight(300);
        HBox fadHBox = new HBox(10, fadLabel, spiritusListView);
        gridPane.add(fadHBox, 0, 1, 2, 1);


        Button påfyldButton = new Button("Udtræk Historie");
        påfyldButton.setOnAction(e -> {
            isSelectedCheck();
        });
        gridPane.add(påfyldButton, 0, 3);

        Button closeButton = new Button("Luk");
        closeButton.setOnAction(e -> window.close());
        gridPane.add(closeButton, 1, 3);

        Scene scene = new Scene(gridPane, 600, 500);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void isSelectedCheck() {
        if (spiritusListView.getSelectionModel().isEmpty()) {
            lblError.setStyle("-fx-text-fill: red;");
            lblError.setText("Vælg en spiritus");
        } else {
            fadHistorik = spiritusListView.getSelectionModel().getSelectedItem().getFadHistorik();
            DestilleringFadHistorikPopUp.display(spiritusListView.getSelectionModel().getSelectedItem());
            lblError.setText("");
        }
    }




    public void påfyldning(Fad fad) {

    }
}





