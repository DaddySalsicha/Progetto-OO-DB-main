package Model;
import java.time.LocalDate;
import java.util.ArrayList;

public class Giocatore {
    private String nome;
    private String cognome;
    private String codFisc;
    private String piede;
    private LocalDate dataDiNascita;
    private LocalDate dataRitiro;
    private ArrayList<String> listaCaratteristiche;
    private ArrayList<Trofeo> listaTrofei;
    private ArrayList<Militanza> listaMilitanze;

    public Giocatore(String nome, String cognome, String codFisc, LocalDate dataDiNascita,
                     String piede, LocalDate dataRitiro, ArrayList<String> listaCaratteristiche) {
        this.nome = nome;
        this.cognome = cognome;
        this.codFisc = codFisc;
        this.piede = piede;
        this.setDataDiNascita(dataDiNascita);
        this.setDataRitiro(dataRitiro);
        this.listaCaratteristiche = listaCaratteristiche;
        this.listaTrofei = new ArrayList<>();
        this.listaMilitanze = new ArrayList<>();
    }
    public Giocatore(String nome, String cognome, String codFisc,
                     String piede, LocalDate dataDiNascita){
        this.nome = nome;
        this.cognome = cognome;
        this.codFisc = codFisc;
        this.piede = piede;
        this.setDataDiNascita(dataDiNascita);
        this.listaTrofei = new ArrayList<>();
        this.listaMilitanze = new ArrayList<>();
    }

    public Giocatore(String nome, String cognome, String codFisc, String piede, LocalDate dataDiNascita, LocalDate dataDiRitiro) {
        this.nome = nome;
        this.cognome = cognome;
        this.codFisc = codFisc;
        this.piede = piede;
        this.setDataDiNascita(dataDiNascita);
        this.setDataDiNascita(dataDiRitiro);
        this.listaTrofei = new ArrayList<>();
        this.listaMilitanze = new ArrayList<>();
    }

    public void aggiungiMilitanza(Militanza m){
        this.listaMilitanze.add(m);
    }

    public LocalDate getDataRitiro() {
        return dataRitiro;
    }
    public void aggiungiTrofeo(TrofeoIndividuale t){
        listaTrofei.add(t);
        t.setGiocatoreVincitore(this);
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getCodFisc() {
        return codFisc;
    }
    public void setCodFisc(String codFisc) {
        this.codFisc = codFisc;
    }
    public String getPiede() {
        return piede;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public void setDataRitiro(LocalDate dataRitiro) {
        this.dataRitiro = dataRitiro;
    }

    public void setPiede(String piede) {
        this.piede = piede;
    }
    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }
    public ArrayList<String> getListaCaratteristiche() {
        return listaCaratteristiche;
    }
    public void setListaCaratteristiche(ArrayList<String> listaCaratteristiche) {
        this.listaCaratteristiche = listaCaratteristiche;
    }
    public ArrayList<Trofeo> getListaTrofei() {
        return listaTrofei;
    }
    public void setListaTrofei(ArrayList<Trofeo> listaTrofei) {
        this.listaTrofei = listaTrofei;
    }
    public ArrayList<Militanza> getListaMilitanze() {
        return listaMilitanze;
    }
    public void setListaMilitanze(ArrayList<Militanza> listaMilitanze) {
        this.listaMilitanze = listaMilitanze;
    }
}
