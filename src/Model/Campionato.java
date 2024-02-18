package Model;

import java.util.ArrayList;

public class Campionato {
    private String nome;
    private String anno;
    private int idCampionato;
    private ArrayList<Squadra> listaSquadre;
    public Campionato(String nome, String anno, int idCampionato, ArrayList<Squadra> listaSquadre){
        this.nome = nome;
        this.anno = anno;
        this.idCampionato = idCampionato;
        this.listaSquadre = listaSquadre;
    }

    public Campionato(String nome, String anno, Integer idCampionato) {
        this.nome = nome;
        this.anno = anno;
        this.idCampionato = idCampionato;
        listaSquadre = new ArrayList<>();
    }

    public int getIdCampionato() {
        return idCampionato;
    }

    public void setIdCampionato(int idCampionato) {
        this.idCampionato = idCampionato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public ArrayList<Squadra> getListaSquadre() {
        return listaSquadre;
    }

    public void setListaSquadre(ArrayList<Squadra> listaSquadre) {
        this.listaSquadre = listaSquadre;
    }

    public void aggiungiSquadra(Squadra s){
        listaSquadre.add(s);
    }
    public void rimuoviSquadra(Squadra s)  { listaSquadre.remove(s); }
}
