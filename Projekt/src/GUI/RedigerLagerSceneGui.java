package GUI;

import Application.Model.Lager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RedigerLagerSceneGui extends Scene {

    private TextField lagerIdField, lagerNavnField, lagerLokationField, lagerKapacitetField;

    private Lager lager;

    public RedigerLagerSceneGui(Lager lager){
        super(new VBox(),400, 300);
        this.lager = lager;
        lagerIdField = new TextField(String.valueOf(lager.getId()));
        lagerNavnField = new TextField(lager.getNavn());
        lagerLokationField = new TextField(lager.getLokation());
        lagerKapacitetField = new TextField(String.valueOf(lager.getKapacitet()));

        Button gemButton = new Button("Gem");
        gemButton.setOnAction(e -> {
            lager.setId(Integer.parseInt(lagerIdField.getText()));
            lager.setNavn(lagerNavnField.getText());
            lager.setLokation(lagerLokationField.getText());
            lager.setKapacitet(Integer.parseInt(lagerKapacitetField.getText()));

            // Luk scenen
            ((Stage) gemButton.getScene().getWindow()).close();
        });

        Button annullerButton = new Button("Annuller");
        annullerButton.setOnAction(e -> {
            // Luk scenen uden at gemme ændringer
            ((Stage) annullerButton.getScene().getWindow()).close();
        });

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(gemButton, annullerButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        VBox form = new VBox(10);
        form.getChildren().addAll(
                new Label("ID:"), lagerIdField,
                new Label("Navn:"), lagerNavnField,
                new Label("Lokation:"), lagerLokationField,
                new Label("Kapacitet:"), lagerKapacitetField, buttonBox

        );
        form.setPadding(new Insets(10));
        setRoot(form);
    }
}


