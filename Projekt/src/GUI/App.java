package GUI;

import Application.Controller.Controller;
import Storage.Storage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;



public class App extends Application {


    private Controller controller = new Controller(Storage.getInstance());
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        controller.inItStorage();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Lager Management System");

        TabPane tabPane = new TabPane();
        LagerOgFadGui lagerOgFadGui = new LagerOgFadGui();

        Tab LagerOgFadTab = new Tab("Lager og Fad");
        lagerOgFadGui.start(primaryStage);
        LagerOgFadTab.setContent(lagerOgFadGui.getGrid());
        LagerOgFadTab.setClosable(false);
        tabPane.getTabs().add(LagerOgFadTab);

        DestilleringGui destilleringGui = new DestilleringGui();
        Tab destilleringTab = new Tab("Destillering");
        destilleringTab.setContent(destilleringGui.getDestilleringGrid());
        destilleringTab.setClosable(false);
        tabPane.getTabs().add(destilleringTab);

        SpiritusGui spiritusGui = new SpiritusGui(lagerOgFadGui);
        Tab spiritusTab = new Tab("Spiritus");
        spiritusTab.setContent(spiritusGui.getSpiritusGrid());
        spiritusTab.setClosable(false);
        tabPane.getTabs().add(spiritusTab);

        DestilleringFadHistorik destilleringFadHistorik = new DestilleringFadHistorik();
        Tab destilleringFadHistorikTab = new Tab("Praktisk");
        destilleringFadHistorikTab.setContent(destilleringFadHistorik.getDestilleringGrid());
        destilleringFadHistorikTab.setClosable(false);
        tabPane.getTabs().add(destilleringFadHistorikTab);

        FadHistorikGui fadHistorikGui = new FadHistorikGui();
        Tab fadhistorikTab = new Tab("FadHistorik");
        fadhistorikTab.setContent(fadHistorikGui.getFadHistorikGrid());
        fadhistorikTab.setClosable(false);
        tabPane.getTabs().add(fadhistorikTab);


        Scene scene = new Scene(tabPane, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
