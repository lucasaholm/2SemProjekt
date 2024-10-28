package GUI;

import Application.Model.*;
import Storage.Storage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DestilleringFadHistorik extends BorderPane {

    private GridPane destilleringGrid;
    private Button påfyldFad, omhældFad, udtrækHistorie, opretDestilleringbtn;
    private LagerOgFadGui lagerOgFadGui;
    Label lblError = new Label("");



    public DestilleringFadHistorik() {
        createDestilleringGrid();
    }


    private void createDestilleringGrid() {
        destilleringGrid = new GridPane();
        destilleringGrid.setHgap(10);
        destilleringGrid.setVgap(10);
        destilleringGrid.setPadding(new Insets(10, 10, 10, 10));
        destilleringGrid.setAlignment(Pos.CENTER);


        HBox listViewBox = new HBox(20); // 20 er afstanden i HBox

        destilleringGrid.add(listViewBox, 0,0,2,1);

        påfyldFad = new Button("Påfyld fad");
        destilleringGrid.add(påfyldFad, 0, 10);
        påfyldFad.setOnAction(e -> { DestilleringFadHistorikPåfyld.display();
        });

        omhældFad = new Button("Omhæld Fad");
        destilleringGrid.add(omhældFad, 1, 10);
        omhældFad.setOnAction(e -> {DestilleringFadHistorikOmhæld.display();});

        udtrækHistorie = new Button("Udtræk historie");
        destilleringGrid.add(udtrækHistorie, 2, 10);
        udtrækHistorie.setOnAction(e -> {DestilleringFadHistorikHistorie.display();
        });

        HBox buttonBox2 = new HBox(10, påfyldFad, omhældFad, udtrækHistorie);
        buttonBox2.setAlignment(Pos.CENTER);
        destilleringGrid.add(buttonBox2, 0, 2, 3, 1);

    }


    private VBox createInputFieldVBox(Label label, Control control) {
        VBox vbox = new VBox(5); // 5 er afstanden mellem børnene i VBox
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(label, control);
        return vbox;
    }



    public GridPane getDestilleringGrid() {
        return destilleringGrid;
    }

}

