package Application.Model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Destillering {
private int id;
private String navn;
private double produktionsMængde;
private LocalDate startDato;
private LocalDate SlutDato;
private double alkoholProcent;
private String maltBatch;
private double mængdeVæske;
private boolean rygeMateriale;
private String kommentar;
private Kornsort kornsort;
private FadHistorik fadHistorik;
private Medarbejder medarbejder;

private final ArrayList<FadHistorik> fade = new ArrayList<>();

    public ArrayList<FadHistorik> getFade() {
        return new ArrayList<>(fade);
    }

    public Destillering(int id, String navn, double produktionsMængde, LocalDate startDato, LocalDate slutDato, double alkoholProcent, String maltBatch, boolean rygeMateriale, String kommentar, Kornsort kornsort, Medarbejder medarbejder) {
        this.id = id;
        this.navn = navn;
        this.produktionsMængde = produktionsMængde;
        this.startDato = startDato;
        this.SlutDato = slutDato;
        this.alkoholProcent = alkoholProcent;
        this.maltBatch = maltBatch;
        this.mængdeVæske = produktionsMængde;
        this.rygeMateriale = rygeMateriale;
        this.kommentar = kommentar;
        this.kornsort = kornsort;
        this.medarbejder = medarbejder;
    }

    public FadHistorik createFadHistorik(int id, int udtagelsesVolumen, LocalDate påfyldningsdato, Destillering destillering, Fad fad) {
        FadHistorik fadhistorik = new FadHistorik(id, udtagelsesVolumen, påfyldningsdato, destillering);
        fade.add(fadhistorik);
        return fadhistorik;
    }

    public void addFadhistorik(FadHistorik fadHistorik) {
        if(!fade.contains(fadHistorik)) {
            fade.add(fadHistorik);
        }
    }


    public void removeFadhistorik(FadHistorik fadHistorik) {
        fade.remove(fadHistorik);
    }

    public Medarbejder getMedarbejder() {
        return medarbejder;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public double getProduktionsMængde() {
        return produktionsMængde;
    }

    public void setProduktionsMængde(double produktionsMængde) {
        this.produktionsMængde = produktionsMængde;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public void setStartDato(LocalDate startDato) {
        this.startDato = startDato;
    }

    public LocalDate getSlutDato() {
        return SlutDato;
    }

    public void setSlutDato(LocalDate slutDato) {
        SlutDato = slutDato;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public void setAlkoholProcent(double alkoholProcent) {
        this.alkoholProcent = alkoholProcent;
    }

    public String getMaltBatch() {
        return maltBatch;
    }

    public void setMaltBatch(String maltBatch) {
        this.maltBatch = maltBatch;
    }

    public double getMængdeVæske() {
        return mængdeVæske;
    }

    public void setMængdeVæske(double mængdeVæske) {
        this.mængdeVæske = mængdeVæske;
    }

    public boolean isRygeMateriale() {
        return rygeMateriale;
    }

    public void setRygeMateriale(boolean rygeMateriale) {
        this.rygeMateriale = rygeMateriale;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    public Kornsort getKornsort() {
        return kornsort;
    }

    public void setKornsort(Kornsort kornsort) {
        this.kornsort = kornsort;
    }

    public FadHistorik getFadHistorik() {
        return fadHistorik;
    }

    public void setFadHistorik(FadHistorik fadHistorikDestillering) {
        this.fadHistorik = fadHistorikDestillering;
    }

    @Override
    public String toString() {
        return "" + id + ", " + navn + ", mængde: " + mængdeVæske;

    }
}
