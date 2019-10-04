
package javafx_predmetcrud;




public class Predmet {

    private int predmet_id;

    private String predmet_sifra;
 
    private String predmet_naziv;

    public Predmet() {
    }
    
    

    public Predmet(int predmet_id, String predmet_sifra, String predmet_naziv) {
        this.predmet_id = predmet_id;
        this.predmet_sifra = predmet_sifra;
        this.predmet_naziv = predmet_naziv;
    }

    public Predmet(String predmet_sifra, String predmet_naziv) {
        this.predmet_sifra = predmet_sifra;
        this.predmet_naziv = predmet_naziv;
    }
    
    

    public int getPredmet_id() {
        return predmet_id;
    }

    public void setPredmet_id(int predmet_id) {
        this.predmet_id = predmet_id;
    }

    public String getPredmet_sifra() {
        return predmet_sifra;
    }

    public void setPredmet_sifra(String predmet_sifra) {
        this.predmet_sifra = predmet_sifra;
    }

    public String getPredmet_naziv() {
        return predmet_naziv;
    }

    public void setPredmet_naziv(String predmet_naziv) {
        this.predmet_naziv = predmet_naziv;
    }

 
    @Override
    public String toString() {
        return "Predmet{" + "predmet_id=" + predmet_id + ", predmet_sifra=" + predmet_sifra + ", predmet_naziv=" + predmet_naziv + '}';
    }
    
    
    
}
