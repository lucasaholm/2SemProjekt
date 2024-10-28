package Storage;

import Application.Model.*;

import java.util.ArrayList;
import java.util.List;

public class Storage implements StorageInterface{
    private static Storage instance;
    private Storage() {
        // Privat constructor
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    private final static List<Lager> lagere = new ArrayList<>();
    private final static List<Destillering> destilleringer = new ArrayList<>();
    private final static List<Medarbejder> medarbejderer = new ArrayList<>();
    private final static List<FadHistorik> fadHistorikker = new ArrayList<>();
    private final static List<Fad> fade = new ArrayList<>();
    private final static List<Spiritus> spiritusser = new ArrayList<>();




    // ------------------------------------------------------------------------- Lager
    public List<Lager> getLagere() {
        return lagere;
    }

    public void addLager(Lager lager){
        lagere.add(lager);
    }

    public void removeLager(Lager lager){
        lagere.remove(lager);
    }

    public void clearLagerListe(){
        lagere.clear();
    }

    // ------------------------------------------------------------------------- Destillering
    public List<Destillering> getDestilleringer() {return destilleringer;}

    public void addDestillering(Destillering destillering) {
        destilleringer.add(destillering);
    }

    public void removeDestillering(Destillering destillering) {
        destilleringer.remove(destillering);
    }
    // ------------------------------------------------------------------------- Medarbejder
    public List<Medarbejder> getMedarbejderer() {
        return medarbejderer;
    }

    public void addMedarbejder(Medarbejder medarbejder) {
        medarbejderer.add(medarbejder);
    }

    public void removeMedarbejder(Medarbejder medarbejder) {
        medarbejderer.remove(medarbejder);
    }
    // ------------------------------------------------------------------------- FadHistorik
    public List<FadHistorik> getFadHistorikker() {
        return fadHistorikker;
    }

    public void addFadHistorik(FadHistorik fadHistorik) {
        fadHistorikker.add(fadHistorik);
    }

    public void removeFadHistorik(FadHistorik fadHistorik) {
        fadHistorikker.remove(fadHistorik);
    }
    // ------------------------------------------------------------------------- Fad
    public List<Fad> getFade() {
        return fade;
    }

    public void addFad(Fad fad) {
        fade.add(fad);
    }

    public void removeFad(Fad fad) {
        fade.remove(fad);
    }
    // ------------------------------------------------------------------------- Spiritus
    public List<Spiritus> getSpiritusser() {
        return spiritusser;
    }

    public void addSpiritus(Spiritus spiritus) {
        spiritusser.add(spiritus);
    }

    public void removeSpiritus(Spiritus spiritus) {
        spiritusser.remove(spiritus);
    }


    // -------------------------------------------------------------------------


}
