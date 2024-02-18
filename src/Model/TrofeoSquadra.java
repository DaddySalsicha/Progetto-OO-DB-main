package Model;


import java.time.LocalDate;

public class TrofeoSquadra extends Trofeo{
    private Squadra squadraVincitrice;

    public TrofeoSquadra(String nome, String anno, String merito, Squadra squadraVincitrice){
        super(nome, anno, merito);
        this.squadraVincitrice = squadraVincitrice;
        squadraVincitrice.aggiungiTrofeo(this);
    }

    public Squadra getSquadraVincitrice() {
        return squadraVincitrice;
    }

    public void setSquadraVincitrice(Squadra squadraVincitrice) {
        this.squadraVincitrice = squadraVincitrice;
    }
}
