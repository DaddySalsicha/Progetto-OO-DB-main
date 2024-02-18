package Controller;

import Model.*;
import PostgresDAO.RegistrazioneImplementazioneDAO;
import PostgresDAO.UtenteImplementazioneDAO;

import java.time.LocalDate;
import java.util.ArrayList;

public class ControllerUtente {

    UtenteImplementazioneDAO user;
    ArrayList<Giocatore> listaGiocatori = new ArrayList<>();
    ArrayList<Campionato> listaCampionati = new ArrayList<>();
    ArrayList<Squadra> listaSquadre = new ArrayList<>();
    ArrayList<TrofeoSquadra> listaTrofeiSquadra = new ArrayList<>();
    ArrayList<TrofeoIndividuale> listaTrofeiIndividuali = new ArrayList<>();
    ArrayList<Militanza> listaMilitanze = new ArrayList<>();

    /**
     * Effettua il login di un utente con il login e la password forniti e costruisce il model dal DB se il login ha avuto successo.
     *
     * @param login Il nome utente per il login.
     * @param password La password associata all'account utente.
     * @return true se il login è riuscito, altrimenti false.
     */

    public boolean login(String login, String password) {
        user = new UtenteImplementazioneDAO(login, password);
        if(user.login()){
            buildModelFromDB();
            return true;
        }else{
            return false;
        }
    }


    /**
     * Registra un nuovo utente nel sistema.
     *
     * @param login Il nome utente scelto dall'utente durante la registrazione.
     * @param password La password scelta dall'utente durante la registrazione.
     * @return True se la registrazione è avvenuta con successo, altrimenti False.
     */
    public boolean signUp(String login, String password) {
        return RegistrazioneImplementazioneDAO.signUp(login, password);
    }

    /**
     * Costruisce il modello dell'applicazione basato sui dati presenti nel database.
     */
    public void buildModelFromDB(){
        buildGiocatoriFromDB();
        buildCampionatiFromDB();
        buildSquadreFromDB();
        buildTrofeiIndividualiFromDB();
        buildTrofeiSquadraFromDB();
        buildMilitanzeFromDB();
    }


    /**
     * Costruisce la lista dei giocatori del modello a partire dai dati presenti nel database.
     */
    public void buildGiocatoriFromDB() {
        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaCognomi = new ArrayList<>();
        ArrayList<String> listaCodFisc = new ArrayList<>();
        ArrayList<String> listaPiedi = new ArrayList<>();
        ArrayList<LocalDate> listaDateNascita = new ArrayList<>();
        ArrayList<LocalDate> listaDateRitiro = new ArrayList<>();
        ArrayList<ArrayList<String>> listaListaCaratteristiche = new ArrayList<>();

        user.fetchGiocatoriFromDB(listaNomi, listaCognomi, listaCodFisc, listaPiedi,
                listaDateNascita, listaDateRitiro, listaListaCaratteristiche);

        for(int i = 0; i < listaCodFisc.size(); i++){
            this.listaGiocatori.add(new Giocatore(listaNomi.get(i), listaCognomi.get(i), listaCodFisc.get(i), listaDateNascita.get(i),
                    listaPiedi.get(i), listaDateRitiro.get(i), listaListaCaratteristiche.get(i)));
        }
    }


    /**
     * Costruisce la lista dei campionati del modello a partire dai dati presenti nel database.
     */
    public void buildCampionatiFromDB() {
        ArrayList<Integer> listaId = new ArrayList<>();
        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaAnni = new ArrayList<>();

        user.fetchCampionatiFromDB(listaId, listaNomi, listaAnni);

        for(int i = 0; i < listaId.size(); i++){
            this.listaCampionati.add(new Campionato(listaNomi.get(i), listaAnni.get(i), listaId.get(i)));
        }
    }


    /**
     * Costruisce la lista delle squadre del modello a partire dai dati presenti nel database.
     */
    public void buildSquadreFromDB() {
        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaNazionalita = new ArrayList<>();
        ArrayList<Integer> listaId = new ArrayList<>();

        user.fetchSquadreFromDB(listaNomi, listaNazionalita, listaId);

        for(int i = 0; i < listaNomi.size(); i++){
            this.listaSquadre.add(new Squadra(listaNomi.get(i), listaNazionalita.get(i), this.getCampionatoFromID(listaId.get(i))));
        }
    }


    /**
     * Ottiene un Campionato dal modello utilizzando l'ID fornito.
     * @param id L'ID del campionato da cercare.
     * @return L'oggetto Campionato corrispondente all'ID fornito, null se non trovato.
     */
    private Campionato getCampionatoFromID(int id){
        for(Campionato c : listaCampionati){
            if(c.getIdCampionato() == id){
                return c;
            }
        }
        return null;
    }


    /**
     * Costruisce e popola la lista dei trofei di squadra dal database.
     */
    public void buildTrofeiSquadraFromDB(){
        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaAnni = new ArrayList<>();
        ArrayList<String> listaMeriti = new ArrayList<>();
        ArrayList<String> listaNomiSquadra = new ArrayList<>();
        ArrayList<String> listaNazionalitaSquadra = new ArrayList<>();

        user.fetchTrofeiDiSquadraFromDB(listaNomi, listaAnni, listaMeriti, listaNomiSquadra, listaNazionalitaSquadra);

        for(int i = 0; i < listaNomi.size(); i++){
            this.listaTrofeiSquadra.add(new TrofeoSquadra(listaNomi.get(i), listaAnni.get(i),
                    listaMeriti.get(i), getSquadraFromPK(listaNomiSquadra.get(i), listaNazionalitaSquadra.get(i))));
        }
    }


    /**
     * Ottiene una squadra dalla lista delle squadre utilizzando il nome e la nazionalità come chiavi primarie.
     * @param nome Il nome della squadra
     * @param nazionalita La nazionalità della squadra
     * @return La squadra corrispondente alle chiavi primarie fornite, null se non trovata.
     */
    private Squadra getSquadraFromPK(String nome, String nazionalita) {
        for(Squadra s : listaSquadre){
            if(s.getNome().equals(nome) && s.getNazionalita().equals(nazionalita)){
                return s;
            }
        }
        return null;
    }


    /**
     * Costruisce la lista dei trofei individuali dal database.
     */
    public void buildTrofeiIndividualiFromDB(){
        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaAnni = new ArrayList<>();
        ArrayList<String> listaMeriti = new ArrayList<>();
        ArrayList<String> listaCodFisc = new ArrayList<>();

        user.fetchTrofeiIndividiualiFromDB(listaNomi, listaAnni, listaMeriti, listaCodFisc);

        for(int i = 0; i < listaNomi.size(); i++){
            this.listaTrofeiIndividuali.add(new TrofeoIndividuale(listaNomi.get(i), listaAnni.get(i),
                    listaMeriti.get(i), getGiocatoreFromPK(listaCodFisc.get(i))));
        }
    }


    /**
     * Restituisce un giocatore dalla lista in base al codice fiscale fornito.
     *
     * @param CodFisc Il codice fiscale del giocatore da cercare.
     * @return Il giocatore corrispondente al codice fiscale fornito, null se non trovato.
     */
    private Giocatore getGiocatoreFromPK(String CodFisc){
        for(Giocatore g : listaGiocatori){
            if(g.getCodFisc().equals(CodFisc)){
                return g;
            }
        }
        return null;
    }


    /**
     * Costruisce le militanze dei giocatori dal database e le aggiunge alla lista del model.
     */
    public void buildMilitanzeFromDB(){
        ArrayList<String> listaCodFisc = new ArrayList<>();
        ArrayList<String> listaNomiSquadra = new ArrayList<>();
        ArrayList<String> listaNazionalitaSquadra = new ArrayList<>();
        ArrayList<LocalDate> listaDateInizio = new ArrayList<>();
        ArrayList<LocalDate> listaDateFine = new ArrayList<>();
        ArrayList<String> listaRuoli = new ArrayList<>();
        ArrayList<Integer> listaPartitaGiocate = new ArrayList<>();
        ArrayList<Integer> listaGolEffettuati = new ArrayList<>();
        ArrayList<Integer> listaGolSubiti = new ArrayList<>();
        ArrayList<Integer> listaAmmonizioni = new ArrayList<>();
        ArrayList<Integer> listaEspulsioni = new ArrayList<>();

        user.fetchMilitanzeFromDB(listaCodFisc, listaNomiSquadra, listaNazionalitaSquadra, listaDateInizio, listaDateFine, listaRuoli,
                listaPartitaGiocate, listaGolEffettuati, listaGolSubiti, listaAmmonizioni, listaEspulsioni);

        for(int i = 0; i < listaCodFisc.size(); i++){
            listaMilitanze.add(new Militanza(getGiocatoreFromPK(listaCodFisc.get(i)), getSquadraFromPK(listaNomiSquadra.get(i),
                    listaNazionalitaSquadra.get(i)), listaRuoli.get(i), listaDateInizio.get(i), listaDateFine.get(i), listaGolEffettuati.get(i),
                    listaGolSubiti.get(i), listaPartitaGiocate.get(i), listaAmmonizioni.get(i), listaEspulsioni.get(i)));
        }
    }


    /**
     * Ottiene le informazioni sulle carriere dei giocatori dal database e le aggiunge alle liste fornite.
     *
     * @param listaCodFisc Elenco dei codici fiscali dei giocatori
     * @param listaNomi Elenco dei nomi dei giocatori
     * @param listaCognomi Elenco dei cognomi dei giocatori
     * @param listaDateNascita Elenco delle date di nascita dei giocatori
     * @param listaEta Elenco delle età dei giocatori
     * @param listaPiedi Elenco dei piedi dominanti dei giocatori
     * @param listaNomiSquadra Elenco dei nomi delle squadre di appartenenza dei giocatori
     * @param listaRuoli Elenco dei ruoli giocati dai giocatori
     * @param listaCaratteristiche Elenco delle caratteristiche dei giocatori
     * @param listaPartiteGiocate Elenco delle partite giocate dai giocatori
     * @param listaGolEffettuati Elenco dei gol effettuati dai giocatori
     * @param listaGolSubiti Elenco dei gol subiti dai giocatori
     * @param listaAmmonizioni Elenco delle ammonizioni ricevute dai giocatori
     * @param listaEspulsioni Elenco delle espulsioni subite dai giocatori
     */
    public void visualizzaCarriere(ArrayList<String> listaCodFisc, ArrayList<String> listaNomi, ArrayList<String> listaCognomi,
                                   ArrayList<LocalDate> listaDateNascita, ArrayList<Integer> listaEta, ArrayList<String> listaPiedi, ArrayList<String> listaNomiSquadra, ArrayList<String> listaRuoli,
                                   ArrayList<String> listaCaratteristiche, ArrayList<Integer> listaPartiteGiocate, ArrayList<Integer> listaGolEffettuati, ArrayList<Integer> listaGolSubiti,
                                   ArrayList<Integer> listaAmmonizioni, ArrayList<Integer> listaEspulsioni){

        user.getCarriereFromDB(listaCodFisc, listaNomi, listaCognomi, listaDateNascita, listaEta, listaPiedi, listaNomiSquadra, listaRuoli,
                listaCaratteristiche, listaPartiteGiocate, listaGolEffettuati, listaGolSubiti, listaAmmonizioni, listaEspulsioni);

    }


    /**
     * Ottiene le informazioni sulle squadre dal model e le aggiunge alle liste fornite.
     *
     * @param listaNomi Elenco dei nomi delle squadre
     * @param listaNazionalita Elenco delle nazionalità delle squadre
     */
    public void visualizzaSquadre(ArrayList<String> listaNomi, ArrayList<String> listaNazionalita){
        for(Squadra s: listaSquadre){
            listaNomi.add(s.getNome());
            listaNazionalita.add(s.getNazionalita());
        }
    }


    /**
     * Ottiene le informazioni sui trofei di una squadra dal model e le aggiunge alle liste fornite.
     *
     * @param nome Nome della squadra
     * @param nazionalita Nazionalità della squadra
     * @param listaNomiTrofeo Elenco dei nomi dei trofei della squadra
     * @param listaAnniTrofeo Elenco degli anni dei trofei della squadra
     * @param listaMeritiTrofeo Elenco dei meriti dei trofei della squadra
     */
    public void visualizzaTrofeiSquadra(String nome, String nazionalita, ArrayList<String> listaNomiTrofeo, ArrayList<String> listaAnniTrofeo, ArrayList<String> listaMeritiTrofeo){
        Squadra s = getSquadraFromPK(nome, nazionalita);
        ArrayList<Trofeo> lista = s.getListaTrofei();
        for(Trofeo t: lista){
            listaNomiTrofeo.add(t.getNome());
            listaAnniTrofeo.add(t.getAnno());
            listaMeritiTrofeo.add(t.getMerito());
        }
    }


    /**
     * Ottiene le informazioni di un giocatore dal model e le aggiunge alla lista fornita.
     *
     * @param codFisc Codice fiscale del giocatore
     * @param listaAttributi Lista in cui aggiungere gli attributi del giocatore
     */
    public void visualizzaGiocatore(String codFisc, ArrayList<String> listaAttributi){
        Giocatore g = getGiocatoreFromPK(codFisc);
        listaAttributi.add(codFisc);
        listaAttributi.add(g.getNome());
        listaAttributi.add(g.getCognome());
        listaAttributi.add(g.getPiede());
    }


    /**
     * Ottiene la data di nascita di un giocatore dal model.
     *
     * @param codFisc Codice fiscale del giocatore
     * @return La data di nascita del giocatore
     */
    public LocalDate getDoBGiocatore(String codFisc){
        Giocatore g = getGiocatoreFromPK(codFisc);
        return g.getDataDiNascita();
    }


    /**
     * Ottiene la data di ritiro di un giocatore dal model.
     *
     * @param codFisc Codice fiscale del giocatore
     * @return La data di ritiro del giocatore
     */
    public LocalDate getDoRGiocatore(String codFisc){
        Giocatore g = getGiocatoreFromPK(codFisc);
        return g.getDataRitiro();
    }


    /**
     * Ottiene la lista delle caratteristiche di un giocatore dal model.
     *
     * @param codFisc Codice fiscale del giocatore
     * @return Una stringa contenente la lista delle caratteristiche del giocatore, separate da virgole
     */
    public String getListaCaratteristicheGiocatore(String codFisc){
        Giocatore g = getGiocatoreFromPK(codFisc);
        ArrayList<String> listaCaratteristiche = g.getListaCaratteristiche();
        String caratteristiche = "";
        if(listaCaratteristiche != null && !listaCaratteristiche.isEmpty()) {
            for(String x : listaCaratteristiche){
                caratteristiche = caratteristiche.concat(x + ", ");
            }
            caratteristiche = caratteristiche.substring(0, caratteristiche.length() - 2);
        }
        return caratteristiche;
    }


    /**
     * Visualizza i trofei vinti da un giocatore.
     *
     * @param codFisc Codice fiscale del giocatore
     * @param listaNomiTrofeo Lista dove inserire i nomi dei trofei
     * @param listaAnniTrofeo Lista dove inserire gli anni dei trofei
     * @param listaMeritiTrofeo Lista dove inserire i meriti dei trofei
     */
    public void visualizzaTrofeiGiocatore(String codFisc, ArrayList<String> listaNomiTrofeo, ArrayList<String> listaAnniTrofeo, ArrayList<String> listaMeritiTrofeo){
        Giocatore g = getGiocatoreFromPK(codFisc);
        ArrayList<Trofeo> lista = g.getListaTrofei();
        for(Trofeo t: lista){
            listaNomiTrofeo.add(t.getNome());
            listaAnniTrofeo.add(t.getAnno());
            listaMeritiTrofeo.add(t.getMerito());
        }
    }

    /**
     * Visualizza le militanze di un giocatore.
     *
     * @param codFisc Codice fiscale del giocatore
     * @param listaNomiSquadra Lista dove inserire i nomi delle squadre
     * @param listaNazionalitaSquadra Lista dove inserire le nazionalità delle squadre
     * @param listaRuoli Lista dove inserire i ruoli giocati
     * @param listaDateInizio Lista dove inserire le date di inizio delle militanze
     * @param listaDateFine Lista dove inserire le date di fine delle militanze
     * @param listaGoalSegnati Lista dove inserire il numero di goal segnati
     * @param listaGoalSubiti Lista dove inserire il numero di goal subiti
     * @param listaPartiteGiocate Lista dove inserire il numero di partite giocate
     * @param listaAmmonizioni Lista dove inserire il numero di ammonizioni ricevute
     * @param listaEspulsioni Lista dove inserire il numero di espulsioni ricevute
     */
    public void visualizzaMilitanzeGiocatore(String codFisc, ArrayList<String> listaNomiSquadra, ArrayList<String> listaNazionalitaSquadra, ArrayList<String> listaRuoli,
                                             ArrayList<LocalDate> listaDateInizio, ArrayList<LocalDate> listaDateFine,ArrayList<Integer> listaGoalSegnati, ArrayList<Integer> listaGoalSubiti,
                                             ArrayList<Integer> listaPartiteGiocate,ArrayList<Integer> listaAmmonizioni,ArrayList<Integer> listaEspulsioni){
        Giocatore g = getGiocatoreFromPK(codFisc);
        ArrayList<Militanza> lista = g.getListaMilitanze();
        if(lista != null && !lista.isEmpty()){
            for(Militanza m : lista){
                listaNomiSquadra.add(m.getSquadra().getNome());
                listaNazionalitaSquadra.add(m.getSquadra().getNazionalita());
                listaRuoli.add(m.getRuolo());
                listaDateInizio.add(m.getDataInizio());
                listaDateFine.add(m.getDataFine());
                listaGoalSegnati.add(m.getGoalSegnati());
                listaGoalSubiti.add(m.getGoalSubiti());
                listaPartiteGiocate.add(m.getPartiteGiocate());
                listaAmmonizioni.add(m.getAmmonizioni());
                listaEspulsioni.add(m.getEspulsioni());
            }
        }
    }


    /**
     * Visualizza i campionati presenti nel model.
     *
     * @param listaNomi Lista dove inserire i nomi dei campionati
     * @param listaAnni Lista dove inserire gli anni dei campionati
     * @param listaId Lista dove inserire gli ID dei campionati
     */
    public void visualizzaCampionati(ArrayList<String> listaNomi, ArrayList<String> listaAnni, ArrayList<Integer> listaId){
        for(Campionato c : listaCampionati){
            listaNomi.add(c.getNome());
            listaAnni.add(c.getAnno());
            listaId.add(c.getIdCampionato());
        }
    }
    /**
     * Effettua una ricerca con i seguenti parametri:
     *
     * @param listaCodFisc          Lista dei codici fiscali dei giocatori
     * @param listaNomi             Lista dei nomi dei giocatori
     * @param listaCognomi          Lista dei cognomi dei giocatori
     * @param listaDateNascita      Lista delle date di nascita dei giocatori
     * @param listaEta              Lista delle età dei giocatori
     * @param listaPiedi            Lista di piede dei giocatori
     * @param listaNomiSquadra      Lista dei nomi delle squadre
     * @param listaRuoli            Lista dei ruoli dei giocatori
     * @param listaCaratteristiche  Lista delle caratteristiche
     * @param listaPartiteGiocate   Lista del numero di partite
     * @param listaGolEffettuati    Lista del numero di gol effettuati dai giocatori
     * @param listaGolSubiti        Lista del numero di gol subiti dai giocatori
     * @param listaAmmonizioni      Lista del numero di ammonizioni ricevute dai giocatori
     * @param listaEspulsioni       Lista del numero di espulsioni ricevute dai giocatori
     * @param nome                  Nome del giocatore da cercare
     * @param etaricerca            Età del giocatore da cercare
     * @param ruolo                 Ruolo del giocatore da cercare
     * @param squadra               Squadra del giocatore da cercare
     * @param piede                 Piede del giocatore da cercare
     * @param ordgolsegnati         Ordine per gol segnati
     * @param ordgolsubiti          Ordine per gol subiti
     * @param ordeta                Ordine per età
     * @param segnogolsegnati       Segno per gol segnati
     * @param segnogolsubiti        Segno per gol subiti
     * @param segnoeta              Segno per età
     * @param golsubitiricerca      Numero di gol subiti da cercare
     * @param golsegnatiricerca     Numero di gol segnati da cercare
     */
    public void ricerca(ArrayList<String> listaCodFisc, ArrayList<String> listaNomi, ArrayList<String> listaCognomi,
                        ArrayList<LocalDate> listaDateNascita, ArrayList<Integer> listaEta, ArrayList<String> listaPiedi, ArrayList<String> listaNomiSquadra, ArrayList<String> listaRuoli,
                        ArrayList<String> listaCaratteristiche, ArrayList<Integer> listaPartiteGiocate, ArrayList<Integer> listaGolEffettuati, ArrayList<Integer> listaGolSubiti,
                        ArrayList<Integer> listaAmmonizioni, ArrayList<Integer> listaEspulsioni, String nome, int etaricerca, String ruolo, String squadra, String piede, String ordgolsegnati, String ordgolsubiti,
                        String ordeta, String segnogolsegnati, String segnogolsubiti, String segnoeta, int golsubitiricerca, int golsegnatiricerca){

        user.fetchRicerca(listaCodFisc, listaNomi, listaCognomi,
                listaDateNascita, listaEta, listaPiedi, listaNomiSquadra, listaRuoli,
                listaCaratteristiche, listaPartiteGiocate, listaGolEffettuati, listaGolSubiti,
                listaAmmonizioni,listaEspulsioni, nome, etaricerca, ruolo, squadra, piede, ordgolsegnati, ordgolsubiti, ordeta, segnogolsegnati, segnogolsubiti, segnoeta,
                golsubitiricerca, golsegnatiricerca);

    }
    /**
     * Svuota il modello, cancellando tutti gli elementi dalle liste.
     */

    public void emptyModel() {
        listaGiocatori.clear();
        listaCampionati.clear();
        listaSquadre.clear();
        listaTrofeiSquadra.clear();
        listaTrofeiIndividuali.clear();
        listaMilitanze.clear();
    }
}