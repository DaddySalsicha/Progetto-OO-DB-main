package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Militanza {
    private Giocatore giocatore;
    private Squadra squadra;
    private String ruolo;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private int goalSegnati;
    private int goalSubiti;
    private int partiteGiocate;
    private int ammonizioni;
    private int espulsioni;


    public Militanza(Giocatore giocatore, Squadra squadra, String ruolo, LocalDate  dataInizio,
                     LocalDate dataFine, int goalSegnati, int goalSubiti, int partiteGiocate, int ammonizioni, int espulsioni){
        this.giocatore = giocatore;
        giocatore.aggiungiMilitanza(this);
        this.squadra = squadra;
        setRuolo(ruolo);
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.goalSegnati = goalSegnati;
        this.goalSubiti = goalSubiti;
        this.partiteGiocate = partiteGiocate;
        this.ammonizioni = ammonizioni;
        this.espulsioni = espulsioni;
    }

    public Squadra getSquadra() {
        return squadra;
    }

    public void setSquadra(Squadra squadra) {
        this.squadra = squadra;
    }

    public String getRuolo() {
        return ruolo;
    }

    public boolean checkRuolo(String ruolo) {
        return ruolo.equals("Portiere") || ruolo.equals("Difensore") || ruolo.equals("Centrocampista") || ruolo.equals("Attaccante");
    }

    public void setRuolo(String ruolo){
        if(checkRuolo(ruolo)) {
            this.ruolo = ruolo;
        }
    }

    public Giocatore getGiocatore() {
        return giocatore;
    }

    public void setGiocatore(Giocatore giocatore) {
        this.giocatore = giocatore;
    }

    public int getAmmonizioni() {
        return ammonizioni;
    }

    public void setAmmonizioni(int ammonizioni) {
        this.ammonizioni = ammonizioni;
    }

    public int getEspulsioni() {
        return espulsioni;
    }

    public void setEspulsioni(int espulsioni) {
        this.espulsioni = espulsioni;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public int getGoalSegnati() {
        return goalSegnati;
    }

    public void setGoalSegnati(int goalSegnati) {
        this.goalSegnati = goalSegnati;
    }

    public int getGoalSubiti() {
        return goalSubiti;
    }

    public void setGoalSubiti(int goalSubiti) {
        this.goalSubiti = goalSubiti;
    }

    public int getPartiteGiocate() {
        return partiteGiocate;
    }

    public void setPartiteGiocate(int partiteGiocate) {
        this.partiteGiocate = partiteGiocate;
    }
}
