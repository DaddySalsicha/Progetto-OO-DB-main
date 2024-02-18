package Model;


import java.time.LocalDate;

public class TrofeoIndividuale extends Trofeo {
    private Giocatore giocatoreVincitore;

    public TrofeoIndividuale(String nome, String anno, String merito, Giocatore g){
        super(nome, anno, merito);
        this.giocatoreVincitore = g;
        g.aggiungiTrofeo(this);
    }

    public Giocatore getGiocatoreVincitore() {
        return giocatoreVincitore;
    }

    public void setGiocatoreVincitore(Giocatore giocatoreVincitore) {
        this.giocatoreVincitore = giocatoreVincitore;
    }
}