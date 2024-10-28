package GUI;

import Application.Controller.Controller;
import Application.Model.*;
import Storage.Storage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

public class SpiritusGui {

    private GridPane spiritusGrid;
    private TextField spiritusIdField, navnField, volumeField, alkoholProcentField, prisField, vandMængdeField, kildeField;
    private TextArea beskrivelseField;
    private ComboBox<ProduktType> typeField;
    private ComboBox<Fad> fadComboBox;
    private ListView<Spiritus> spiritusListView;
    private Button addButton, deleteButton, clearButton;
    private LagerOgFadGui lagerOgFadGui;
    private Controller controller = new Controller(Storage.getInstance());
    private FadHistorik fadHistorik;





    public SpiritusGui(LagerOgFadGui lagerOgFadGui) {
        this.lagerOgFadGui = lagerOgFadGui;
        createSpiritusGrid();
    }

    private void createSpiritusGrid() {
        spiritusGrid = new GridPane();
        spiritusGrid.setHgap(10);
        spiritusGrid.setVgap(10);
        spiritusGrid.setPadding(new Insets(10, 10, 10, 10));

        spiritusIdField = new TextField();
        navnField = new TextField();
        typeField = new ComboBox<>();
        typeField.getItems().addAll(ProduktType.values());
        volumeField = new TextField();
        alkoholProcentField = new TextField();
        prisField = new TextField();
        vandMængdeField = new TextField();
        kildeField = new TextField();
        beskrivelseField = new TextArea();

        //Initialisér fadComboBox her
        List<String> fadList = new ArrayList<>();
        fadComboBox = new ComboBox<>();
        for(Fad fad : Storage.getInstance().getFade()) {
            ObservableList<Fad> med = FXCollections.observableArrayList();
            med.addAll(Storage.getInstance().getFade());
            fadComboBox.setItems(med);
        }



        spiritusGrid.add(new Label("Spiritus ID:"), 0, 0);
        spiritusGrid.add(spiritusIdField, 1, 0);
        spiritusGrid.add(new Label("Navn:"), 0, 1);
        spiritusGrid.add(navnField, 1, 1);
        spiritusGrid.add(new Label("Type:"), 0, 2);
        spiritusGrid.add(typeField, 1, 2);
        spiritusGrid.add(new Label("Volume:"), 0, 3);
        spiritusGrid.add(volumeField, 1, 3);
        spiritusGrid.add(new Label("Fad:"), 0, 4);
        spiritusGrid.add(fadComboBox, 1, 4);
        spiritusGrid.add(new Label("Alkoholprocent:"), 0, 5);
        spiritusGrid.add(alkoholProcentField, 1, 5);
        spiritusGrid.add(new Label("Pris pr. flaske:"), 0, 6);
        spiritusGrid.add(prisField, 1, 6);
        spiritusGrid.add(new Label("Vandmængde:"), 0, 7);
        spiritusGrid.add(vandMængdeField, 1, 7);
        spiritusGrid.add(new Label("Kilde:"), 0, 8);
        spiritusGrid.add(kildeField, 1, 8);
        spiritusGrid.add(new Label("Beskrivelse:"), 0, 10);
        spiritusGrid.add(beskrivelseField, 1, 10);

        addButton = new Button("Opret");
        addButton.setOnAction(e ->{
            opretSpritus();
        });

        deleteButton = new Button("Slet");
        deleteButton.setOnAction(e -> {
            sletSpriritus();
        });

        clearButton = new Button("Ryd");
        clearButton.setOnAction(e -> {
            clearSpiritus();
        });

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(addButton, deleteButton, clearButton);
        spiritusGrid.add(buttonBox, 1, 9, 2, 1);
        spiritusListView = new ListView<>();
        spiritusGrid.add(spiritusListView, 2, 0, 1, 10);

    }

    private void opretSpritus() {
        Spiritus spiritus = controller.createSpiritus(Integer.parseInt(spiritusIdField.getText()),
                navnField.getText(), typeField.getValue(), Double.parseDouble(volumeField.getText()),
                Double.parseDouble(alkoholProcentField.getText()), Double.parseDouble(prisField.getText()),
                Double.parseDouble(vandMængdeField.getText()), kildeField.getText(), beskrivelseField.getText(),
                fadComboBox.getSelectionModel().getSelectedItem().getFadHistorik());

        spiritusIdField.clear();
        navnField.clear();
        typeField.getItems().clear();
        volumeField.clear();
        alkoholProcentField.clear();
        prisField.clear();
        vandMængdeField.clear();
        kildeField.clear();
        beskrivelseField.clear();

        spiritusListView.getItems().add(spiritus);
    }

    private void sletSpriritus(){
        Spiritus spiritus = spiritusListView.getSelectionModel().getSelectedItem();
        if(spiritus != null){
            spiritusListView.getItems().remove(spiritus);
            Storage.getInstance().removeSpiritus(spiritus);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Ingen spiritus valgt");
            alert.setContentText("Vælg en spiritus fra listen før sletning.");
            alert.showAndWait();
        }
    }

    private void clearSpiritus(){
        spiritusIdField.clear();
        navnField.clear();
        typeField.getItems().clear();
        volumeField.clear();
        alkoholProcentField.clear();
        prisField.clear();
        vandMængdeField.clear();
        kildeField.clear();
        beskrivelseField.clear();
    }

    public GridPane getSpiritusGrid() {
        return spiritusGrid;
    }

}