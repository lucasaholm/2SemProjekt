package Application.Model;

import java.util.ArrayList;

public class Lager {
    private int id;
    private String navn;
    private String lokation;
    private int kapacitet;
    private final ArrayList<Fad> fade;

    public ArrayList<Fad> getFade() {
        return fade;
    }


    public Lager(int id, String navn, String lokation, int kapacitet) {
        this.id = id;
        this.navn = navn;
        this.lokation = lokation;
        this.kapacitet = kapacitet;
        this.fade = new ArrayList<>();
    }

    public void addFad(Fad fad) {
       if(!fade.contains(fad)){
           fade.add(fad);
           fad.setLager(this);
       }
    }

    public void removeFad(Fad fad) {
        if(fade.contains(fad)) {
            fade.remove(fad);
            fad.setLager(null);
        }
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

    public String getLokation() {
        return lokation;
    }

    public void setLokation(String lokation) {
        this.lokation = lokation;
    }

    public int getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(int kapacitet) {
        this.kapacitet = kapacitet;
    }


    @Override
    public String toString() {
        return "" + id + ", " + navn;
    }
}
