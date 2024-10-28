package GUI;

import Application.Controller.Controller;
import Application.Model.Fad;
import Application.Model.Kornsort;
import Application.Model.Lager;
import Application.Model.ProduktType;
import Storage.Storage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LagerOgFadGui extends Application {
    private TextField lagerIdField, lagerNavnField,lagerLokationField, lagerKapacitetField;
    private TextField fadIdField, fadVolumenField, fadNuværendeVolumenField, fadAntalGangeBrugtField, leverandørField, tidligereIndholdField, typeField;
    private ListView<Lager> lagerListView;
    private ListView<Fad> fadListView;
    private Button tilføjLagerButton, sletLagerButton, redigerLagerButton, clearLagerButton;
    private Button tilføjFadButton, sletFadButton, redigerFadButton, clearFadButton;
    private Controller controller = new Controller(Storage.getInstance());

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane grid = getGrid();
        Scene scene = new Scene(grid, 1000, 600);
        primaryStage.setTitle("Sall Whisky");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public GridPane getGrid(){
        GridPane grid = new GridPane();

        //Lager input fields
        lagerIdField = new TextField();
        lagerNavnField = new TextField();
        lagerLokationField = new TextField();
        lagerKapacitetField = new TextField();

        //Fad input fields
        fadIdField = new TextField();
        typeField = new TextField();
        fadVolumenField = new TextField();
        fadNuværendeVolumenField = new TextField();
        fadAntalGangeBrugtField = new TextField();
        leverandørField = new TextField();
        tidligereIndholdField = new TextField();


        //Lager og fad listview
        lagerListView = new ListView<>();
        lagerListView.getItems().addAll(Storage.getInstance().getLagere());
        fadListView = new ListView<>();
        fadListView.getItems().addAll(Storage.getInstance().getFade());


        //Lager buttons
        tilføjLagerButton = new Button("Tilføj Lager");
        sletLagerButton = new Button("Slet Lager");
        redigerLagerButton = new Button("Rediger Lager");
        clearLagerButton = new Button("Clear Lager");

        //Fad buttons
        tilføjFadButton = new Button("Tilføj Fad");
        sletFadButton = new Button("Slet Fad");
        redigerFadButton = new Button("Rediger Fad");
        clearFadButton = new Button("Clear Fad");


        tilføjLagerButton.setOnAction(e -> {
            tilføjLager();
        });

        sletLagerButton.setOnAction(e -> {
            sletLager();
        });

        //Rediger Lager knap action: (Åbner ny Scene):
        redigerLagerButton.setOnAction(e -> {
            redigerLager();
        });

        //Clear Lager knap:
        clearLagerButton.setOnAction(e -> {
           clearLager();
        });

        //Fad action knap: (Tilføjer fad til lager)
        tilføjFadButton.setOnAction(e -> {
           tilføjFad();
        });

        //Slet Fad knap action:
        sletFadButton.setOnAction(e -> {
           sletFad();
        });

        //Rediger fad knap action: åbner ny scene
        redigerFadButton.setOnAction(event -> {
            redigerFad();
        });

        //Clear Fad knap action:
        clearFadButton.setOnAction(e -> {
            clearFad();
        });

        //layout
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 10, 10, 10));

        grid.add(new Label("Lager ID:"), 0, 0);
        grid.add(lagerIdField, 1, 0);
        grid.add(new Label("Lager Navn:"), 0, 1);
        grid.add(lagerNavnField, 1, 1);
        grid.add(new Label("Lager Lokation:"), 0, 2);
        grid.add(lagerLokationField, 1, 2);
        grid.add(new Label("Lager Kapacitet:"), 0, 3);
        grid.add(lagerKapacitetField, 1, 3);
        grid.add(new Label("Fad ID:"), 2, 0);
        grid.add(fadIdField, 3, 0);
        grid.add(new Label("Fad Type:"), 2, 1);
        grid.add(typeField, 3, 1);
        grid.add(new Label("Fad Volumen:"), 2, 2);
        grid.add(fadVolumenField, 3, 2);
        grid.add(new Label("Nuværende Volumen:"), 2, 3);
        grid.add(fadNuværendeVolumenField, 3, 3);
        grid.add(new Label("Antal Gange Brugt:"), 2, 4);
        grid.add(fadAntalGangeBrugtField, 3, 4);
        grid.add(new Label("Leverandør:"), 2, 5);
        grid.add(leverandørField, 3, 5);
        grid.add(new Label("Tidligere Indhold:"), 2, 6);
        grid.add(tidligereIndholdField, 3, 6);



        HBox lagerButtons = new HBox(10);
        lagerButtons.getChildren().addAll(tilføjLagerButton, redigerLagerButton, sletLagerButton, clearLagerButton);

        HBox fadButtons = new HBox(10);
        fadButtons.getChildren().addAll(tilføjFadButton, redigerFadButton, sletFadButton, clearFadButton);

        VBox listViews = new VBox(10);
        listViews.getChildren().addAll(lagerListView, fadListView);

        grid.add(lagerButtons, 1, 4);
        grid.add(fadButtons, 3, 7);
        grid.add(listViews, 0, 5, 2, 8);

        return grid;
    }


    private void tilføjLager(){
        Lager lager = new Lager(Integer.parseInt(lagerIdField.getText()),
                lagerNavnField.getText(),
                lagerLokationField.getText(),
                Integer.parseInt(lagerKapacitetField.getText()));
        lagerListView.getItems().add(lager);

        //Clear Lager input fields, gør at efter man har tilføjet et lager bliver felterne ryddet.
        lagerIdField.clear();
        lagerNavnField.clear();
        lagerLokationField.clear();
        lagerKapacitetField.clear();
    }

    private void sletLager(){
        Lager selectedLager = lagerListView.getSelectionModel().getSelectedItem();
        lagerListView.getItems().remove(selectedLager);
    }

    private void redigerLager(){
        Lager selectedLager = lagerListView.getSelectionModel().getSelectedItem();
        if (selectedLager != null) {
            RedigerLagerSceneGui redigerLagerScene = new RedigerLagerSceneGui(selectedLager);

            Stage redigerLagerStage = new Stage();
            redigerLagerStage.setTitle("Rediger Lager");
            redigerLagerStage.setScene(redigerLagerScene);
            redigerLagerStage.showAndWait();
            lagerListView.refresh();
        }
    }
    private void clearLager(){
        lagerIdField.clear();
        lagerNavnField.clear();
        lagerLokationField.clear();
        lagerKapacitetField.clear();
    }

    private void tilføjFad(){
        Lager selectedLager = lagerListView.getSelectionModel().getSelectedItem();
        if(selectedLager != null){
            Fad fad = controller.createFad(Integer.parseInt(fadIdField.getText()),
                    typeField.getText(),
                    Double.parseDouble(fadVolumenField.getText()),
                    Double.parseDouble(fadNuværendeVolumenField.getText()),
                    Integer.parseInt(fadAntalGangeBrugtField.getText()),
                    leverandørField.getText(),
                    tidligereIndholdField.getText(),
                    selectedLager);

            selectedLager.addFad(fad);
            fadListView.getItems().addAll(selectedLager.getFade());

            // Clear vores fields
            fadIdField.clear();
            typeField.clear();
            fadVolumenField.clear();
            fadNuværendeVolumenField.clear();
            fadAntalGangeBrugtField.clear();
            leverandørField.clear();
            tidligereIndholdField.clear();
        } else {
            // viser en error message hvis intet lager er valgt
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ikke valgt et lager");
            alert.setContentText("Vælg et lager fra listen før tilføjelse af fad.");
            alert.showAndWait();
        }
    }

    private void sletFad(){
        Fad selectedFad = fadListView.getSelectionModel().getSelectedItem();
        if (selectedFad != null) {
            Lager selectedLager = selectedFad.getLager();
            selectedLager.removeFad(selectedFad);
            fadListView.getItems().remove(selectedFad);
        }
    }

    private void redigerFad(){
        Fad selectedFad = fadListView.getSelectionModel().getSelectedItem();
        Lager selectedLager = lagerListView.getSelectionModel().getSelectedItem();
        if (selectedFad != null && selectedLager != null) {
            RedigerFadScene redigerFadScene = new RedigerFadScene(selectedFad);
            Stage redigerFadStage = new Stage();
            redigerFadStage.setTitle("Rediger Fad");
            redigerFadStage.setScene(redigerFadScene);
            redigerFadStage.showAndWait();

            //opdaterer vores listview med det redigerede fad
            fadListView.refresh();
        }
    }
    private void clearFad(){
        fadIdField.clear();
        typeField.clear();
        fadVolumenField.clear();
        fadNuværendeVolumenField.clear();
        fadAntalGangeBrugtField.clear();
        leverandørField.clear();
        tidligereIndholdField.clear();
    }






}
