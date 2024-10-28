package Application.Model;

import Storage.Storage;
import javafx.geometry.Pos;

import java.time.LocalDate;
import java.util.ArrayList;

public class FadHistorik {

    private int id;
    private double udtagelsesVolumen;
    private LocalDate påfyldningsDato;
    private Medarbejder medarbejderDestillering;
    private Destillering destillering;
    private double volumenPåfyldt;
    private final ArrayList<Fad> fadArrayList = new ArrayList<>();
    private final ArrayList<Spiritus> spiritusArrayList = new ArrayList<>();
    private FadHistorik omhældningsFadHistorik;

    public ArrayList<Fad> getFadArrayList() {
        return new ArrayList<>(fadArrayList);
    }

    public ArrayList<Spiritus> getSpiritusArrayList() {
        return new ArrayList<>(spiritusArrayList);
    }

    public FadHistorik(int id, int udtagelsesVolumen, LocalDate påfyldningsDato, Destillering destillering) {
        this.id = id;
        this.udtagelsesVolumen = udtagelsesVolumen;
        this.påfyldningsDato = påfyldningsDato;
        this.destillering = destillering;
        destillering.setMængdeVæske(destillering.getMængdeVæske() - udtagelsesVolumen);
    }

    public String manglendePåfyldning() {
        double tilgængeligFadVolumen = 0;
        for (Fad f : fadArrayList) {
            tilgængeligFadVolumen += f.getVolumen() - f.getNuværendeVolumen();
        }
        if (tilgængeligFadVolumen > udtagelsesVolumen) {
            return "der er en ekstra volumen på " + (tilgængeligFadVolumen - udtagelsesVolumen) + " tilgængelig på fadene";
        } else if (tilgængeligFadVolumen == udtagelsesVolumen) {
            return "Det går lige op";
        } else {
            return "der en ikke nok fade til påfyldning af fadene: " + (tilgængeligFadVolumen) + " mangles af blive påfyldt";
        }
    }

    public void påfyldFade() {
        for (Fad fad : fadArrayList) {
            if (volumenPåfyldt <= udtagelsesVolumen - fad.getVolumen()) {
                volumenPåfyldt += fad.getVolumen();
                fad.setFadHistorik(this);
                fad.setNuværendeVolumen(fad.getVolumen());
            } else if (volumenPåfyldt != udtagelsesVolumen) {
                double sidstePåfyldning = udtagelsesVolumen - volumenPåfyldt;
                volumenPåfyldt += sidstePåfyldning;
                fad.setFadHistorik(this);
                fad.setNuværendeVolumen(sidstePåfyldning);
            }
        }
    }

    public FadHistorik createOmhældning(Fad fad, LocalDate date, Destillering destillering, double udtagelsesVolumen) {
        boolean fadFundet = false;
        for (Fad gammelFad : fadArrayList) {
            if (gammelFad != fad) {
                fadFundet = true;
                gammelFad.setTidligereIndhold(destillering.toString());
                if (gammelFad.getNuværendeVolumen() >= udtagelsesVolumen && gammelFad.getVolumen() >= udtagelsesVolumen && fad.getNuværendeVolumen() + udtagelsesVolumen <= fad.getVolumen()) {
                    setOmhældningsFadHistorik(this);
                    setPåfyldningsDato(date);
                    gammelFad.setNuværendeVolumen(gammelFad.getNuværendeVolumen() - udtagelsesVolumen);
                    fad.setNuværendeVolumen(fad.getNuværendeVolumen() + udtagelsesVolumen);
                } else {
                    throw new RuntimeException("Der er ikke nok volumen i det gamle fad eller det angivne fad har ikke nok kapacitet");
                }
            }
        }
        if (!fadFundet) {
            throw new RuntimeException("Ingen matchende fad fundet");
        }
        return fad.getFadHistorik();
    }


    public Medarbejder getMedarbejder() {
        return medarbejderDestillering;
    }

    public Spiritus createSpiritus(int id, String navn, FadHistorik fadHistorik, ProduktType type, double volume, double alkoholProcent, double pris, double vandMængde, String kilde, String beskrivelse) {
        Spiritus spiritus = new Spiritus(id, navn, this, type, volume, alkoholProcent, pris, vandMængde, kilde, beskrivelse);
        spiritusArrayList.add(spiritus);
        addSpiritus(spiritus);
        return spiritus;
    }

    public void addSpiritus(Spiritus spiritus) {
        if (!spiritusArrayList.contains(spiritus)) {
            spiritusArrayList.add(spiritus);
        }
    }

    public void removeSpiritus(Spiritus spiritus) {
        if (spiritusArrayList.contains(spiritus)) {
            spiritusArrayList.remove(spiritus);
        }
    }


    public void addFad(Fad fad) {
        if (!fadArrayList.contains(fad)) {
            fadArrayList.add(fad);
            fad.setFadHistorik(this);
        }
    }

    public void removeFad(Fad fad) {
        if (fadArrayList.contains(fad)) {
            fadArrayList.remove(fad);
            fad.setFadHistorik(null);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public LocalDate getPåfyldningsDato() {
        return påfyldningsDato;
    }

    public void setPåfyldningsDato(LocalDate påfyldningsDato) {
        this.påfyldningsDato = påfyldningsDato;
    }

    public Medarbejder getMedarbejdeDestillering() {
        return medarbejderDestillering;
    }

    public void setMedarbejdeDestillering(Medarbejder medarbejdeDestillering) {
        this.medarbejderDestillering = medarbejdeDestillering;
    }

    public Destillering getDestillering() {
        return destillering;
    }

    public void setDestillering(Destillering destillering) {
        this.destillering = destillering;
    }

    public FadHistorik getOmhældningsFadHistorik() {
        return omhældningsFadHistorik;
    }

    public void setOmhældningsFadHistorik(FadHistorik omhældningsFadHistorik) {
        this.omhældningsFadHistorik = omhældningsFadHistorik;
    }

    public void printOmhældninger() {
        System.out.println("Her er alle de historikker fra id: " + id + ", der ingår i blandingen");

        if (omhældningsFadHistorik == null) {
            System.out.println("id: " + id + " er ikke blevet omhældt");
        } else {
            System.out.println("Id: " + omhældningsFadHistorik.getId());
            omhældningsFadHistorik.printOmhældninger();
        }
    }

    public String udtrækSpiritusHistorie(Spiritus spiritus) {
        if (spiritusArrayList.contains(spiritus)) {
            StringBuilder historie = new StringBuilder();
            historie.append("Spiritus ID: ").append(spiritus.getId()).append("\n");
            historie.append("Spiritus Navn: ").append(spiritus.getNavn()).append("\n");
            historie.append("Spiritus Type: ").append(spiritus.getType()).append("\n");
            historie.append("Spiritus Alkoholprocent: ").append(spiritus.getAlkoholProcent()).append("\n");
            historie.append("Spiritus Volume: ").append(spiritus.getVolume()).append("\n");
            historie.append("Spiritus Pris: ").append(spiritus.getPris()).append("\n");
            historie.append("Spiritus Vandmængde: ").append(spiritus.getVandMængde()).append("\n");
            historie.append("Spiritus Kilde: ").append(spiritus.getKilde()).append("\n");
            historie.append("Spiritus Beskrivelse: ").append(spiritus.getBeskrivelse()).append("\n");


            if (Storage.getInstance().getFadHistorikker().contains(this)) {
                setOmhældningsFadHistorik(this);
                historie.append("Fadhistorik:").append(" Denne spiritus er blevet omhældt " + spiritus.getFadHistorik().getOmhældningsFadHistorik() + " gange.").append("\n");
            }



            return historie.toString();
        } else {
            return "Spiritus ikke fundet i denne FadHistorik.";
        }
    }


    @Override
    public String toString() {
        StringBuilder print = new StringBuilder();
        for (Fad f : fadArrayList) {
            print.append(f.getId()).append(", ");
        }
        return "" + id + ", Fade: " + print + " udtagelses mængden: " + (udtagelsesVolumen - volumenPåfyldt);
    }

    public double getUdtagelsesVolumen() {
        return udtagelsesVolumen;
    }

    public void setUdtagelsesVolumen(double udtagelsesVolumen) {
        this.udtagelsesVolumen = udtagelsesVolumen;
    }
}
