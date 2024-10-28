package GUI;

import Application.Controller.Controller;
import Application.Model.*;
import Storage.Storage;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class DestilleringGui extends BorderPane {

    private Controller controller = new Controller(Storage.getInstance());

    private GridPane destilleringGrid;
    private Medarbejder medarbejder;
    private TextField idTextField, navnTextField, produktionsMængdeTextField;
    private TextField alkoholProcentTextField, maltBatchTextField, kommentarTextField;
    private DatePicker startDatePicker, endDatePicker;
    private CheckBox rygeMaterialeCheckBox;
    private ComboBox<Kornsort> kornsortComboBox;
    private ComboBox<FadHistorik> fadHistorikComboBox;
    private ComboBox<Medarbejder> medarbejderComboBox;
    private Button opretDestilleringbtn, sletDestilleringbtn;
    private ListView<Destillering> lvwDestilleringer;
    private TextArea txaDestilleringDetaljer;

    private Storage storage;
    private LagerOgFadGui lagerOgFadGui;
    Label lblError;


    public DestilleringGui() {
        createDestilleringGrid();
    }


    private void createDestilleringGrid() {
        destilleringGrid = new GridPane();
        destilleringGrid.setHgap(10);
        destilleringGrid.setVgap(10);
        destilleringGrid.setPadding(new Insets(10, 10, 10, 10));
        destilleringGrid.setAlignment(Pos.CENTER);


        Label idLabel = new Label("ID:");
        destilleringGrid.add(idLabel, 0, 0);
        idTextField = new TextField();
        destilleringGrid.add(idTextField, 1, 0);

        Label navnLabel = new Label("Navn:");
        destilleringGrid.add(navnLabel, 0, 1);
        navnTextField = new TextField();
        destilleringGrid.add(navnTextField, 1, 1);

        Label produktionsMængdeLabel = new Label("Produktionsmængde:");
        destilleringGrid.add(produktionsMængdeLabel, 0, 2);
        produktionsMængdeTextField = new TextField();
        destilleringGrid.add(produktionsMængdeTextField, 1, 2);

        Label startDatoLabel = new Label("Startdato:");
        destilleringGrid.add(startDatoLabel, 0, 3);
        startDatePicker = new DatePicker();
        destilleringGrid.add(startDatePicker, 1, 3);

        Label slutDatoLabel = new Label("Slutdato:");
        destilleringGrid.add(slutDatoLabel, 0, 4);
        endDatePicker = new DatePicker();
        destilleringGrid.add(endDatePicker, 1, 4);

        Label alkoholProcentLabel = new Label("Alkoholprocent:");
        destilleringGrid.add(alkoholProcentLabel, 0, 5);
        alkoholProcentTextField = new TextField();
        destilleringGrid.add(alkoholProcentTextField, 1, 5);

        Label maltBatchLabel = new Label("Maltbatch:");
        destilleringGrid.add(maltBatchLabel, 0, 6);
        maltBatchTextField = new TextField();
        destilleringGrid.add(maltBatchTextField, 1, 6);

        Label rygeMaterialeLabel = new Label("Rygemateriale:");
        destilleringGrid.add(rygeMaterialeLabel, 1, 1);
        rygeMaterialeCheckBox = new CheckBox();
        destilleringGrid.add(rygeMaterialeCheckBox, 0, 1);

        Label kommentarLabel = new Label("Kommentar:");
        destilleringGrid.add(kommentarLabel, 0, 9);
        kommentarTextField = new TextField();
        destilleringGrid.add(kommentarTextField, 1, 9);

        Label kornsortLabel = new Label("Kornsort:");
        destilleringGrid.add(kornsortLabel, 0, 10);
        kornsortComboBox = new ComboBox<>();
        destilleringGrid.add(kornsortComboBox, 1, 10);
        kornsortComboBox.getItems().addAll(Kornsort.EVERGREEN, Kornsort.IRINA, Kornsort.STAINWAY);




        Label medarbejderDestillering = new Label("Medarbejder:");
        destilleringGrid.add(medarbejderDestillering, 2, 10);
        medarbejderComboBox = new ComboBox<>();
        destilleringGrid.add(medarbejderComboBox, 1, 10);
        ObservableList<Medarbejder> med = FXCollections.observableArrayList();
        med.addAll(Storage.getInstance().getMedarbejderer());
        medarbejderComboBox.setItems(med);


        // Tilføj hvert medarbejdernavn til ComboBoxen
        HBox inputFieldsHBox1 = new HBox(20); // 20 er afstanden i HBox
        inputFieldsHBox1.setAlignment(Pos.CENTER);
        inputFieldsHBox1.getChildren().addAll(
                createInputFieldVBox(idLabel, idTextField),
                createInputFieldVBox(navnLabel, navnTextField),
                createInputFieldVBox(produktionsMængdeLabel, produktionsMængdeTextField),
                createInputFieldVBox(startDatoLabel, startDatePicker),
                createInputFieldVBox(slutDatoLabel, endDatePicker),
                createInputFieldVBox(alkoholProcentLabel, alkoholProcentTextField)
        );


        opretDestilleringbtn = new Button("Opret Destillering");
        sletDestilleringbtn = new Button("Slet Destillering");


        HBox buttonBox = new HBox(10, opretDestilleringbtn, sletDestilleringbtn);
        buttonBox.setAlignment(Pos.BASELINE_LEFT);
        destilleringGrid.add(buttonBox, 0, 8, 3, 1);


        HBox inputFieldsHBox2 = new HBox(10); // 20 er afstanden mellem børnene i HBox
        inputFieldsHBox2.setAlignment(Pos.CENTER);
        inputFieldsHBox2.getChildren().addAll(
                createInputFieldVBox(maltBatchLabel, maltBatchTextField),
                createInputFieldVBox(kommentarLabel, kommentarTextField),
                createInputFieldVBox(kornsortLabel, kornsortComboBox),
                createInputFieldVBox(medarbejderDestillering, medarbejderComboBox)
        );



        VBox inputFieldsVBox = new VBox(10); // 10 er afstanden mellem børnene i VBox
        inputFieldsVBox.setAlignment(Pos.CENTER);
        inputFieldsVBox.getChildren().addAll(inputFieldsHBox1, inputFieldsHBox2);


        destilleringGrid.add(inputFieldsVBox, 0, 0, 3, 1);

        lvwDestilleringer = new ListView<>();
        lvwDestilleringer.setPrefWidth(200);
        lvwDestilleringer.setPrefHeight(650);
        lvwDestilleringer.getItems().setAll(controller.getDestillering());

        txaDestilleringDetaljer = new TextArea();
        txaDestilleringDetaljer.setEditable(false);
        txaDestilleringDetaljer.setMaxWidth(500);
        txaDestilleringDetaljer.setPrefHeight(650);

        ChangeListener<Destillering> listener = (ov, oldCompany, newCompany) -> this.selectedDestilleringChanged();
        lvwDestilleringer.getSelectionModel().selectedItemProperty().addListener(listener);

        // Opret en HBox for at holde fadeTableView og destilleringTableView
        HBox tableViewHBox = new HBox(10);
        tableViewHBox.setAlignment(Pos.CENTER);
        tableViewHBox.getChildren().addAll(lvwDestilleringer, txaDestilleringDetaljer);

        // Tilføj tableViewsHBox til destilleringGrid
        destilleringGrid.add(tableViewHBox,0,12,2,1);

        lblError = new Label();
        lblError.setStyle("-fx-text-fill: red;");
        destilleringGrid.add(lblError,0, 7, 4, 1);

        //actions
        opretDestilleringbtn.setOnAction(event -> createDestilleringGui());
        sletDestilleringbtn.setOnAction(event -> sletDestillering());
    }


    private void createDestilleringGui() {
        lblError.setText("");
        if (Integer.parseInt(idTextField.getText()) <= 0) {
            lblError.setText("Udfyld et gyldigt ID over 0");
        } else if (navnTextField.getText().isEmpty()) {
            lblError.setText("Udfyld et gyldigt navn");
        } else if (Double.parseDouble(produktionsMængdeTextField.getText()) < 0) {
            lblError.setText("Udfyld en gyldig produktionsmængde");
        } else if (startDatePicker.getValue().isAfter(endDatePicker.getValue())) {
            lblError.setText("Startdatoen er efter slutdatoen");
        } else if (endDatePicker.getValue().isBefore(startDatePicker.getValue())) {
            lblError.setText("Slutdatoen er før startdatoen");
        } else if (Double.parseDouble(alkoholProcentTextField.getText()) < 0) {
            lblError.setText("Udfyld en gyldig alkoholProcent");
        } else if (kommentarTextField.getText().isEmpty()) {
            lblError.setText("Skriv en kommentar");
        } else if (maltBatchTextField.getText().isEmpty()) {
            lblError.setText("Udfyld en gyldig maltbatch");
        } else if (kornsortComboBox.selectionModelProperty().getValue() == null) {
            lblError.setText("Vælg en kornsort");
        } else {
            Destillering destillering = controller.createDestillering(Integer.parseInt(idTextField.getText()), navnTextField.getText(), Double.parseDouble(produktionsMængdeTextField.getText()),
                    startDatePicker.getValue(), endDatePicker.getValue(), Double.parseDouble(alkoholProcentTextField.getText()), maltBatchTextField.getText(),
                    rygeMaterialeCheckBox.isAllowIndeterminate(), kommentarTextField.getText(), kornsortComboBox.getValue(), medarbejder);
            lblError.setStyle("-fx-text-fill: green;");
            lblError.setText("Destillering oprettet");


            //Clear Lager input fields, gør at efter man har tilføjet et lager bliver felterne ryddet.
            idTextField.clear();
            navnTextField.clear();
            produktionsMængdeTextField.clear();
            startDatePicker.getEditor().clear();
            endDatePicker.getEditor().clear();
            alkoholProcentTextField.clear();
            maltBatchTextField.clear();
            rygeMaterialeCheckBox.setSelected(false);
            kommentarTextField.clear();
            kornsortComboBox.valueProperty().set(null);
            medarbejderComboBox.valueProperty().set(null);

            //Opdaterer vores listview med den nye destillering
            lvwDestilleringer.getItems().add(destillering);
        }
    }

    private void sletDestillering(){
        Destillering valgtDestillering = lvwDestilleringer.getSelectionModel().getSelectedItem();
        if(valgtDestillering != null){
            controller.removeDestillering(valgtDestillering);
            lvwDestilleringer.getItems().remove(valgtDestillering);
            txaDestilleringDetaljer.clear();
            lblError.setStyle("-fx-text-fill: green;");
            lblError.setText("Destillering slettet");
        } else {
            lblError.setStyle("-fx-text-fill: red;");
            lblError.setText("Vælg en destillering for at slette");
        }
    }


    private void selectedDestilleringChanged() {
        this.updateControls();
    }

    public void updateControls() {
        Destillering destillering = lvwDestilleringer.getSelectionModel().getSelectedItem();
        if (destillering != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Id: " + destillering.getId() + "\n");
            sb.append("Navn: " + destillering.getNavn() + "\n");
            sb.append("Produktions mængde: " + destillering.getProduktionsMængde() + "\n");
            sb.append("Startdato: " + destillering.getStartDato().toString() + "\n");
            sb.append("Slutdato: " + destillering.getSlutDato().toString() + "\n");
            sb.append("Alkoholprocent: " + destillering.getAlkoholProcent() + "\n");
            sb.append("Malt batch: " + destillering.getMaltBatch() + "\n");
            sb.append("Væske mængde: " + destillering.getMængdeVæske() + "\n");
            sb.append("Kommentar: " + destillering.getKommentar() + "\n");
            sb.append("Kornsort: " + destillering.getKornsort() + "\n");
            sb.append("Medarbejder for destilleringen" + destillering.getMedarbejder() + "\n");
            if (destillering.isRygeMateriale()) {
                sb.append("Rygemateriale: true" + "\n");
            } else {
                sb.append("Rygemateriale: false" + "\n");
            }
            txaDestilleringDetaljer.setText(sb.toString());


        }
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
