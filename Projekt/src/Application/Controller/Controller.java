package Application.Controller;

import Application.Model.*;
import Storage.Storage;
import Storage.StorageInterface;
import java.time.LocalDate;
import java.util.List;

public class Controller {
    private final StorageInterface storage;

    public Controller(StorageInterface storage) {
        this.storage = storage;
    }
    public void clearLagerListe() {
        storage.clearLagerListe();
    }
    public void addFadTilLager(Lager lager, Fad fad){
        lager.addFad(fad);
    }

   public void addLager(Lager lager){
        storage.addLager(lager);
   }

    public void updateFad(Fad fad, int id, String fadType, int antalGangeBrugt, String leverandør, String tidligereIndhold, Lager lager){
        fad.setId(id);
        fad.setFadType(fadType);
        fad.setAntalGangeBrugt(antalGangeBrugt);
        fad.setLeverandør(leverandør);
        fad.setTidligereIndhold(tidligereIndhold);
        fad.setLager(lager);
    }

    public void deleteFad(Lager lager, Fad fad){
        lager.removeFad(fad);
    }

    public void addDestillering(Destillering destillering){
        storage.addDestillering(destillering);
    }

    public void removeDestillering(Destillering destillering){
        storage.addDestillering(destillering);
    }

    public List<Fad> getFadeFraLager(Lager lager){
        return lager.getFade();
    }


    public void addFadtilFadHistorik(Fad fad, FadHistorik fadHistorik) {
        if(!fadHistorik.getFadArrayList().contains(fad)) {
            storage.addFadHistorik(fadHistorik);
            storage.addFad(fad);
        }
    }


    public Destillering createDestillering(int id, String navn, double produktionsMængde, LocalDate startDato, LocalDate slutDato, double alkoholProcent, String maltBatch, boolean rygeMateriale, String kommentar, Kornsort kornsort, Medarbejder medarbejder) {
        Destillering destillering = new Destillering(id, navn, produktionsMængde, startDato, slutDato, alkoholProcent, maltBatch, rygeMateriale, kommentar,  kornsort, medarbejder);
        storage.addDestillering(destillering);
        return destillering;
    }

    public Fad createFad(int id, String fadType, double volumen, double nuværendeVolumen, int antalGangeBrugt, String leverandør, String tidligereIndhold, Lager lager) {
        Fad fad = new Fad(id, fadType, volumen,  nuværendeVolumen, antalGangeBrugt, leverandør, tidligereIndhold, lager);
        storage.addFad(fad);
        return fad;
    }

    public FadHistorik createFadhistorik (int id, int udtagelsesVolumen, LocalDate påfyldningsDato, Destillering destillering) {
        FadHistorik fadHistorik = new FadHistorik(id, udtagelsesVolumen, påfyldningsDato, destillering);
        storage.addFadHistorik(fadHistorik);
        return fadHistorik;
    }
    public Lager createLager(int id, String navn, String lokation, int kapacitet){
        Lager lager = new Lager(id, navn, lokation,kapacitet);
        storage.addLager(lager);
        return lager;
    }
    public Medarbejder createMedarbejder(int id, String navn, String stilling) {
        Medarbejder medarbejder = new Medarbejder(id, navn, stilling);
        storage.addMedarbejder(medarbejder);
        return medarbejder;
    }

    public Spiritus createSpiritus(int id, String navn, ProduktType type, double volume, double alkoholProcent, double pris, double vandMængde, String kilde, String beskrivelse, FadHistorik fadHistorik) {
        Spiritus s = fadHistorik.createSpiritus(id, navn,fadHistorik, type, volume, alkoholProcent, pris, vandMængde, kilde, beskrivelse);
        storage.addSpiritus(s);
        return s;
    }

    public void getCreateOmhældning(FadHistorik fadHistorik, Fad fad) {
        fadHistorik.createOmhældning(fad,fad.getFadHistorik().getPåfyldningsDato(), fadHistorik.getDestillering(), fadHistorik.getUdtagelsesVolumen());
    }


    public List<Destillering> getDestillering() {
        return storage.getDestilleringer();
    }

    public void inItStorage() {
        Medarbejder medarbejder = createMedarbejder(1,"Bent", "Destillatør");
        Medarbejder medarbejder1 = createMedarbejder(2,"Søren", "Maltblander");
        Medarbejder medarbejder2 = createMedarbejder(3,"Finn", "Operatør");

        Destillering destillering = createDestillering(1, "årgang 2023", 200, LocalDate.of(2023,01,01), LocalDate.of(2026,01,01), 75, "single", true, "Årgang 2023 er destilleret uden problemer", Kornsort.EVERGREEN,medarbejder);
        Destillering destillering1 = createDestillering(2, "årgang 2023", 300, LocalDate.of(2023,02,01), LocalDate.of(2026,02,01), 50, "Blended", false, "Årgang 2023 er destilleret uden problemer", Kornsort.STAINWAY,medarbejder1);
        Destillering destillering2 = createDestillering(3, "årgang 2023", 400, LocalDate.of(2023,03,01), LocalDate.of(2026,03,01), 52.5, "single", true, "Årgang 2023 er destilleret uden problemer", Kornsort.IRINA,medarbejder2);


        Lager lager = createLager(1,"Sall lager", "Salls", 200);
        Lager lager1 = createLager(2,"Aarhus Lager", "Aarhus", 500);
        Lager lager2 = createLager(3,"Aalborg lager", "Aalborg", 450);


        Fad fad = createFad(1, "Burbon fad", 100, 0, 0, "Evergreen APS", "Burbon",lager);
        Fad fad1 = createFad(2, "Whisky fad", 100, 0, 3, "Evergreen APS", "Blended cask",lager1);
        Fad fad2 = createFad(3, "Egefad", 100, 0, 4, "Evergreen APS", "Single cask",lager2);

        FadHistorik fadHistorik = createFadhistorik(1, 20,LocalDate.of(2023,01,01), destillering);
        FadHistorik fadHistorik1 = createFadhistorik(2, 20,LocalDate.of(2023,02,01), destillering1);
        FadHistorik fadHistorik2 = createFadhistorik(3, 20,LocalDate.of(2023,03,01), destillering2);
        fadHistorik.addFad(fad);

        Spiritus spiritus = createSpiritus(1, "Årgang 2023", ProduktType.WHISKY, 50, 52.5, 1250, 10, "Salls Vandløb", "Denne spiritus er lavet med kærlighed", fadHistorik);
        Spiritus spiritus1 = createSpiritus(2, "Årgang 2023", ProduktType.WHISKY, 50, 52.5, 1250, 10, "Salls Vandløb", "Denne spiritus er lavet med kærlighed", fadHistorik1);
        Spiritus spiritus2 = createSpiritus(3, "Årgang 2023", ProduktType.WHISKY, 50, 52.5, 1250, 10, "Salls Vandløb", "Denne spiritus er lavet med kærlighed", fadHistorik2);
    }






}
