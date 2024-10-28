package GUI;

import Application.Model.FadHistorik;
import Application.Model.Spiritus;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DestilleringFadHistorikPopUp {
    private DestilleringFadHistorikHistorie destilleringFadHistorikHistorie;

    static TextArea txaHistorie = new TextArea();
    public static void display(Spiritus spiritus){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Påfyld Fad");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Historie for spiritus");
        titleLabel.setAlignment(Pos.CENTER);
        titleLabel.setStyle("-fx-font-size: 18px;");
        gridPane.add(titleLabel, 0, 0, 3, 1);
        gridPane.add(txaHistorie, 0, 1);

        txaHistorie.setEditable(false);
        txaHistorie.setText(spiritus.udtrækHistorieSpiritus());


        txaHistorie.setPrefWidth(400);
        txaHistorie.setPrefHeight(300);




        HBox fadHBox = new HBox(10, txaHistorie);
        gridPane.add(fadHBox, 0, 1, 2, 1);


        HBox udtagelsesVolumenHBox = new HBox(10);
        gridPane.add(udtagelsesVolumenHBox, 0, 2, 2, 1);

        Button closeButton = new Button("Luk");
        closeButton.setOnAction(e -> window.close());
        gridPane.add(closeButton, 1, 3);

        Scene scene = new Scene(gridPane, 600, 500);
        window.setScene(scene);
        window.showAndWait();
    }

}
