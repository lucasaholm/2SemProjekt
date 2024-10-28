package Application.Model;


public class Medarbejder {
    private int id;
    private String navn;
    private String stilling;


    public Medarbejder(int id, String navn, String stilling) {
        this.id = id;
        this.navn = navn;
        this.stilling = stilling;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNavn() {
        return this.navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getStilling() {
        return stilling;
    }

    public void setStilling(String stilling) {
        this.stilling = stilling;
    }

    @Override
    public String toString() {
        return "" + navn;
    }
}
