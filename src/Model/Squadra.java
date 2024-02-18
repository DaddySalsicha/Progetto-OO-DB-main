package Model;

import java.util.ArrayList;

public class Squadra {
    private String nome;
    private String nazionalita;
    private ArrayList<Trofeo> listaTrofei;
    private Campionato campionatoAppartenenza;

    public Squadra(String nome, String nazione, Campionato campionatoAppartenenza){
        this.nome = nome;
        this.nazionalita = nazione;
        this.campionatoAppartenenza = campionatoAppartenenza;
        campionatoAppartenenza.aggiungiSquadra(this);
        listaTrofei = new ArrayList<>();
    }

    public void aggiungiTrofeo(TrofeoSquadra t){
        listaTrofei.add(t);
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public void setNazionalita(String nazionalita) {
        this.nazionalita = nazionalita;
    }

    public ArrayList<Trofeo> getListaTrofei() {
        return listaTrofei;
    }

    public void setListaTrofei(ArrayList<Trofeo> listaTrofei) {
        this.listaTrofei = listaTrofei;
    }

    public Campionato getCampionatoAppartenenza() {
        return campionatoAppartenenza;
    }

    public void setCampionatoAppartenenza(Campionato campionatoAppartenenza) {
        this.campionatoAppartenenza = campionatoAppartenenza;
    }
}
