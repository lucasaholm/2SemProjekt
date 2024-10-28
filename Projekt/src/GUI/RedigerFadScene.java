package GUI;

import Application.Model.Fad;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RedigerFadScene extends Scene {

    private TextField fadIdField, fadTypeField, volumenField, nuværendeVolumenField, antalGangeBrugtField, leverandørField, tidligereIndhold;
    private Button gemButton, annullerButton;
    private Fad fad;


    public RedigerFadScene(Fad fad){
        super(new VBox(), 400, 600);
        this.fad = fad;

        fadIdField = new TextField(String.valueOf(fad.getId()));
        fadTypeField = new TextField(fad.getFadType());
        volumenField = new TextField(String.valueOf(fad.getVolumen()));
        nuværendeVolumenField = new TextField(String.valueOf(fad.getNuværendeVolumen()));
        antalGangeBrugtField = new TextField(String.valueOf(fad.getAntalGangeBrugt()));
        leverandørField = new TextField(String.valueOf(fad.getLeverandør()));
        tidligereIndhold = new TextField(String.valueOf(fad.getTidligereIndhold()));


        gemButton = new Button("Gem");
        gemButton.setOnAction(e -> {
            fad.setId(Integer.parseInt(fadIdField.getText()));
            fad.setFadType(fadTypeField.getText());
            fad.setVolumen(Double.parseDouble(volumenField.getText()));
            fad.setNuværendeVolumen(Double.parseDouble(nuværendeVolumenField.getText()));
            fad.setAntalGangeBrugt(Integer.parseInt(antalGangeBrugtField.getText()));
            fad.setLeverandør(leverandørField.getText());
            fad.setTidligereIndhold(tidligereIndhold.getText());

            // Luk scenen
            ((Stage) gemButton.getScene().getWindow()).close();
        });

        annullerButton = new Button("Annuller");
        annullerButton.setOnAction(e -> {
            // Luk scenen uden at gemme ændringer
            ((Stage) annullerButton.getScene().getWindow()).close();
        });

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(gemButton, annullerButton);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        VBox form = new VBox(10);
        form.getChildren().addAll(
                new Label("ID:"), fadIdField,
                new Label("Fadtype:"), fadTypeField,
                new Label("Volumen:"), volumenField,
                new Label("Nuværende Volumen:"), nuværendeVolumenField,
                new Label("Antal gange brugt:"), antalGangeBrugtField,
                new Label("Leverandør:"), leverandørField,
                new Label("Tidligere indhold"), tidligereIndhold,
                buttonBox
        );
        form.setPadding(new Insets(10));

        setRoot(form);

    }

}
