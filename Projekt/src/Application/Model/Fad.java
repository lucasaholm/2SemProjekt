package Application.Model;



public class Fad {
    private int id;
    private String fadType;
    private double volumen;
    private double nuværendeVolumen;
    private int antalGangeBrugt;
    private Lager lager;

    private String leverandør;
    private String tidligereIndhold;
    private FadHistorik fadHistorik;

    public FadHistorik getFadHistorik() {
        return fadHistorik;
    }

    public Fad(int id, String fadType, double volumen, double nuværendeVolumen, int antalGangeBrugt, String leverandør, String tidligereIndhold, Lager lager) {
        this.id = id;
        this.fadType = fadType;
        this.volumen = volumen;
        this.nuværendeVolumen = nuværendeVolumen;
        this.antalGangeBrugt = antalGangeBrugt;
        this.lager = lager;
        this.leverandør = leverandør;
        this.tidligereIndhold = tidligereIndhold;
    }

    public void setLager(Lager lager) {
        if(this.lager != lager) {
            Lager gammeltLager = this.lager;
            if(gammeltLager != null) {
                gammeltLager.removeFad(this);
            }
            this.lager = lager;
            if(lager != null){
                lager.addFad(this);
            }
        }
    }

    public void setFadHistorik(FadHistorik fadHistorik) {
        if (this.fadHistorik != fadHistorik) {
            FadHistorik gammelFadhistorik = this.fadHistorik;
            if(gammelFadhistorik != null) {
                gammelFadhistorik.removeFad(this);
            }
            this.fadHistorik = fadHistorik;
            if (fadHistorik != null) {
                fadHistorik.addFad(this);
            }
        }
    }



    public String getLeverandør() {
        return leverandør;
    }

    public void setLeverandør(String leverandør) {
        this.leverandør = leverandør;
    }

    public String getTidligereIndhold() {
        return tidligereIndhold;
    }

    public void setTidligereIndhold(String tidligereIndhold) {
        this.tidligereIndhold = tidligereIndhold;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFadType() {
        return fadType;
    }

    public void setFadType(String fadType) {
        this.fadType = fadType;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }

    public double getNuværendeVolumen() {
        return nuværendeVolumen;
    }

    public void setNuværendeVolumen(double nuværendeVolumen) {
        this.nuværendeVolumen = nuværendeVolumen;
    }

    public int getAntalGangeBrugt() {
        return antalGangeBrugt;
    }

    public void setAntalGangeBrugt(int antalGangeBrugt) {
        this.antalGangeBrugt = antalGangeBrugt;
    }

    public Lager getLager() {
        return lager;
    }


    @Override
    public String toString() {
        return "" + id + ", " + fadType + ", nuværende volumen: " + nuværendeVolumen + ", Lager " + lager;
    }
}
