package Application.Model;


public class Spiritus {
    private int id;
    private String navn;
    private ProduktType type;
    private double volume;
    private double alkoholProcent;
    private double pris;
    private double vandMængde;
    private String kilde;
    private String beskrivelse;
    private FadHistorik fadHistorik;


    public Spiritus(int id, String navn,FadHistorik fadHistorik, ProduktType type, double volume, double alkoholProcent, double pris, double vandMængde, String kilde, String beskrivelse) {
        this.id = id;
        this.navn = navn;
        this.type = type;
        this.volume = volume;
        this.alkoholProcent = alkoholProcent;
        this.pris = pris;
        this.vandMængde = vandMængde;
        this.kilde = kilde;
        this.beskrivelse = beskrivelse;
        this.fadHistorik = fadHistorik;
    }

    public FadHistorik getFadHistorik() {
        return fadHistorik;
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

    public ProduktType getType() {
        return type;
    }

    public void setType(ProduktType type) {
        this.type = type;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public void setAlkoholProcent(double alkoholProcent) {
        this.alkoholProcent = alkoholProcent;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public double getVandMængde() {
        return vandMængde;
    }

    public void setVandMængde(double vandMængde) {
        this.vandMængde = vandMængde;
    }

    public String getKilde() {
        return kilde;
    }

    public void setKilde(String kilde) {
        this.kilde = kilde;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public String udtrækHistorieSpiritus() {
        return fadHistorik.udtrækSpiritusHistorie(this);
    }

    @Override
    public String toString() {
        return "" + id + ", " + navn + ", pris: " + pris;
    }

}
