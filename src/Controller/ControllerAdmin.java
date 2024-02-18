package Controller;

import Model.*;
import PostgresDAO.AdminImplementazioneDAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class ControllerAdmin {

    AdminImplementazioneDAO admin;
    ArrayList<Giocatore> listaGiocatori = new ArrayList<>();
    ArrayList<Campionato> listaCampionati = new ArrayList<>();
    ArrayList<Squadra> listaSquadre = new ArrayList<>();
    ArrayList<TrofeoSquadra> listaTrofeiSquadra = new ArrayList<>();
    ArrayList<TrofeoIndividuale> listaTrofeiIndividuali = new ArrayList<>();
    ArrayList<Militanza> listaMilitanze = new ArrayList<>();
    ArrayList<String> listaCaratteristiche = new ArrayList<>();

    /**
     * Effettua il login di un utente con il login e la password forniti e costruisce il model dal DB se il login ha avuto successo.
     *
     * @param login Il nome utente per il login.
     * @param password La password associata all'account utente.
     * @return true se il login è riuscito e l'utente è un amministratore, altrimenti false.
     */
    public boolean login(String login, String password) {
        admin = new AdminImplementazioneDAO(login, password);
        if(admin.login()) {
            if (admin.checkAdmin()) {
                buildModelFromDB();
                return true;
            } else {
                return false;
            }
        } else{
            return false;
        }
    }
    /**
     * Costruisce il modello dei dati dell'applicazione dai dati memorizzati nel database utilizzando
     * dei sottometodi per ogni classe del Model.
     */
    public void buildModelFromDB(){
        buildGiocatoriFromDB();
        builCampionatiFromDB();
        buildSquadreFromDB();
        buildTrofeiIndividualiFromDB();
        buildTrofeiSquadraFromDB();
        buildMilitanzeFromDB();
        buildCaratteristicheFromDB();
    }

    /**
     * Riempie la lista delle caratteristiche dal Database.
     */
    public void buildCaratteristicheFromDB(){
        admin.fetchCaratteristicheFromDB(listaCaratteristiche);
    }

    /**
     * Riempie il model con giocatori creati utlizzando liste di attributi
     * ottenute dal DataBase tramite la funzione fetchGiocatoriFromDB.
     */
    public void buildGiocatoriFromDB() {
        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaCognomi = new ArrayList<>();
        ArrayList<String> listaCodFisc = new ArrayList<>();
        ArrayList<String> listaPiedi = new ArrayList<>();
        ArrayList<LocalDate> listaDateNascita = new ArrayList<>();
        ArrayList<LocalDate> listaDateRitiro = new ArrayList<>();
        ArrayList<ArrayList<String>> listaListaCaratteristiche = new ArrayList<>();


        admin.fetchGiocatoriFromDB(listaNomi, listaCognomi, listaCodFisc, listaPiedi,
                listaDateNascita, listaDateRitiro, listaListaCaratteristiche);

        for(int i = 0; i < listaCodFisc.size(); i++){
            this.listaGiocatori.add(new Giocatore(listaNomi.get(i), listaCognomi.get(i), listaCodFisc.get(i), listaDateNascita.get(i),
                    listaPiedi.get(i), listaDateRitiro.get(i), listaListaCaratteristiche.get(i)));
        }
    }

    /**
     * Riempie il model con campionati creati utlizzando liste di attributi
     * ottenute dal DataBase tramite la funzione fetchCampionatFromDB.
     */

    public void builCampionatiFromDB(){
        ArrayList<Integer> listaId = new ArrayList<>();
        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaAnni = new ArrayList<>();

        admin.fetchCampionatiFromDB(listaId, listaNomi, listaAnni);

        for(int i = 0; i < listaId.size(); i++){
            this.listaCampionati.add(new Campionato(listaNomi.get(i), listaAnni.get(i), listaId.get(i)));

        }
    }

    /**
     * Riempie il model con squadre creati utlizzando liste di attributi
     * ottenute dal DataBase tramite la funzione fetchSquadreFromDB.
     */

    public void buildSquadreFromDB(){
        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaNazionalita = new ArrayList<>();
        ArrayList<Integer> listaId = new ArrayList<>();

        admin.fetchSquadreFromDB(listaNomi, listaNazionalita, listaId);

        for(int i = 0; i < listaNomi.size(); i++){
            this.listaSquadre.add(new Squadra(listaNomi.get(i), listaNazionalita.get(i), this.getCampionatoFromID(listaId.get(i))));
        }
    }

    /**
     * Cerca il campionato scorrendo la lista del model
     * @param id L'id del campionato da ottenere
     * @return Restituisce il campionato recuperandolo dalla sua chiave primaria oppure null se non la trova
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
     * Riempie il model con trofei di squadra creati utlizzando liste di attributi
     * ottenute dal DataBase tramite la funzione fetchTrofeiSquadraFromDB.
     */

    public void buildTrofeiSquadraFromDB(){
        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaAnni = new ArrayList<>();
        ArrayList<String> listaMeriti = new ArrayList<>();
        ArrayList<String> listaNomiSquadra = new ArrayList<>();
        ArrayList<String> listaNazionalitaSquadra = new ArrayList<>();

        admin.fetchTrofeiDiSquadraFromDB(listaNomi, listaAnni, listaMeriti, listaNomiSquadra, listaNazionalitaSquadra);

        for(int i = 0; i < listaNomi.size(); i++){
            this.listaTrofeiSquadra.add(new TrofeoSquadra(listaNomi.get(i), listaAnni.get(i),
                    listaMeriti.get(i), getSquadraFromPK(listaNomiSquadra.get(i), listaNazionalitaSquadra.get(i))));
        }

    }

    /**
     * Cerca la squadra scorrendo la lista del model
     * @param nome Il nome della squadra da ottenere
     * @param nazionalita La nazionalita della squadra da ottenere
     * @return Restituisce la squadra recuperandola dalla sua chiave primaria oppure null se non la trova
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
     * Riempie il model con trofei individuali creati utlizzando liste di attributi
     * ottenute dal DataBase tramite la funzione fetchTrofeiIndividiualiFromDB.
     */
    public void buildTrofeiIndividualiFromDB(){
        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaAnni = new ArrayList<>();
        ArrayList<String> listaMeriti = new ArrayList<>();
        ArrayList<String> listaCodFisc = new ArrayList<>();

        admin.fetchTrofeiIndividiualiFromDB(listaNomi, listaAnni, listaMeriti, listaCodFisc);

        for(int i = 0; i < listaNomi.size(); i++){
            this.listaTrofeiIndividuali.add(new TrofeoIndividuale(listaNomi.get(i), listaAnni.get(i),
                    listaMeriti.get(i), getGiocatoreFromPK(listaCodFisc.get(i))));
        }
    }

    /**
     * Cerca il giocatore scorrendo la lista del model
     * @param CodFisc Il codice fiscale del giocatore da ottenere
     * @return Restituisce il giocatore recuperandolo dalla sua chiave primaria oppure null se non lo trova
     */

    public Giocatore getGiocatoreFromPK(String CodFisc){
        for(Giocatore g : listaGiocatori){
            if(g.getCodFisc().equals(CodFisc)){
                return g;
            }
        }
        return null;
    }
    /**
     * Riempie il model con militanze create utlizzando liste di attributi
     * ottenute dal DataBase tramite la funzione fetchMilitanzeFromDB.
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

        admin.fetchMilitanzeFromDB(listaCodFisc, listaNomiSquadra, listaNazionalitaSquadra, listaDateInizio, listaDateFine, listaRuoli,
                listaPartitaGiocate, listaGolEffettuati, listaGolSubiti, listaAmmonizioni, listaEspulsioni);

        for(int i = 0; i < listaCodFisc.size(); i++){
            listaMilitanze.add(new Militanza(getGiocatoreFromPK(listaCodFisc.get(i)), getSquadraFromPK(listaNomiSquadra.get(i),
                    listaNazionalitaSquadra.get(i)), listaRuoli.get(i), listaDateInizio.get(i), listaDateFine.get(i), listaGolEffettuati.get(i),
                    listaGolSubiti.get(i), listaPartitaGiocate.get(i), listaAmmonizioni.get(i), listaEspulsioni.get(i)));
        }
    }

    /**
     * Riempie e restituisce alla GUI delle liste di attributi di giocatori ottenute dal model.
     * @param listaCodFisc Lista di codici fiscali da riempire
     * @param listaNomi Lista di nomi da riempire
     * @param listaCognomi Lista di cognomi da riempire
     * @param listaPiedi Lista di piedi da riempire
     * @param listaCaratteristiche Lista di caratteristiche da riempire
     * @param listaDoB Lista di date di nascita da riempire
     * @param listaDoR Lista di date di ritiro da riempire
     */

    public void visualizzaGiocatori(
            ArrayList<String> listaCodFisc, ArrayList<String> listaNomi, ArrayList<String> listaCognomi, ArrayList<String> listaPiedi,
            ArrayList<String> listaCaratteristiche, ArrayList<LocalDate> listaDoB, ArrayList<LocalDate> listaDoR){
        for(Giocatore g : listaGiocatori){
            listaCodFisc.add(g.getCodFisc());
            listaNomi.add(g.getNome());
            listaCognomi.add(g.getCognome());
            listaPiedi.add(g.getPiede());
            ArrayList<String> listaCaratteristicheGiocatore = g.getListaCaratteristiche();
            String caratteristiche = "";
            if(listaCaratteristicheGiocatore != null && !listaCaratteristicheGiocatore.isEmpty()) {
                for(String x : listaCaratteristicheGiocatore){
                    caratteristiche = caratteristiche.concat(x + ", ");
                }
                caratteristiche = caratteristiche.substring(0, caratteristiche.length() - 2);
            }
            listaCaratteristiche.add(caratteristiche);
            listaDoB.add(g.getDataDiNascita());
            listaDoR.add(g.getDataRitiro());
        }
    }

    /**
     * Riempie e restituisce alla GUI delle liste di attributi di squadre ottenute dal model.
     * @param nomi Lista di nomi da riempire
     * @param nazionalita Lista di nazionalita da riempire
     */

    public void visualizzaSquadre(ArrayList<String> nomi, ArrayList<String> nazionalita){
        for(Squadra g : listaSquadre){
            nomi.add(g.getNome());
            nazionalita.add(g.getNazionalita());
        }
    }

    /**
     * Riempie e restituisce alla GUI delle liste di attributi di militanze ottenute dal model.
     * @param listaCodFisc Lista di codici fiscali da riempire
     * @param listaNomiSquadra Lista di nomi di squadre da riempire
      * @param listaNazionalitaSquadra Lista di nazionalita di squadra da riempire
     * @param listaDateInizio Lista di date inizio da riempire
     * @param listaDateFine Lista di date fine da riempire
     * @param listaRuoli Lista di ruoli da riempire
     * @param listaPartitaGiocate Lista di partite giocate da riempire
     * @param listaGolEffettuati Lista di goal effetuati da riempire
     * @param listaGolSubiti Lissta di gol subiti da riempire
     * @param listaAmmonizioni Lista di ammonizioni da riempire
     * @param listaEspulsioni Lista di espulsioni da riempire
     */

    public void visualizzaMilitanze(ArrayList<String> listaCodFisc, ArrayList<String> listaNomiSquadra, ArrayList<String> listaNazionalitaSquadra,
                                    ArrayList<LocalDate> listaDateInizio, ArrayList<LocalDate> listaDateFine, ArrayList<String> listaRuoli,
                                    ArrayList<Integer> listaPartitaGiocate, ArrayList<Integer> listaGolEffettuati, ArrayList<Integer> listaGolSubiti,
                                    ArrayList<Integer> listaAmmonizioni, ArrayList<Integer> listaEspulsioni){
        for(Militanza m : listaMilitanze){
            listaCodFisc.add(m.getGiocatore().getCodFisc());
            listaNomiSquadra.add(m.getSquadra().getNome());
            listaNazionalitaSquadra.add(m.getSquadra().getNazionalita());
            listaDateInizio.add(m.getDataInizio());
            listaDateFine.add(m.getDataFine());
            listaRuoli.add(m.getRuolo());
            listaPartitaGiocate.add(m.getPartiteGiocate());
            listaGolEffettuati.add(m.getGoalSegnati());
            listaGolSubiti.add(m.getGoalSubiti());
            listaAmmonizioni.add(m.getAmmonizioni());
            listaEspulsioni.add(m.getEspulsioni());
        }
    }

    /**
     * Riempie e restituisce alla GUI una lista di codici fiscali di giocatori ottenuta dal model.
     * @param codFisc Lista di codici fiscali di giocatori da visualizzare.
     */
    public void visualizzaCodFiscGiocatori(ArrayList<String> codFisc){
        for(Giocatore g : listaGiocatori){
            codFisc.add(g.getCodFisc());
        }
    }

    /**
     * Inserisce una militanza nel DataBase e, se a buon fine, la inserisce anche nel modello.
     *
     * @param codFisc Il codice fiscale del giocatore associato alla militanza.
     * @param nomeSquadra Il nome della squadra della militanza.
     * @param nazionalitaSquadra La nazionalità della squadra della militanza.
     * @param dataInizio La data di inizio della militanza.
     * @param dataFine La data di fine della militanza.
     * @param ruolo Il ruolo del giocatore durante la militanza.
     * @param partiteGiocate Il numero di partite giocate durante la militanza.
     * @param golEffettuati Il numero di gol effettuati durante la militanza.
     * @param golSubiti Il numero di gol subiti durante la militanza.
     * @param ammonizioni Il numero di ammonizioni ricevute durante la militanza.
     * @param espulsioni Il numero di espulsioni ricevute durante la militanza.
     * @return True se l'inserimento è andato a buon fine, altrimenti false.
     */
    public boolean inserisciMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, LocalDate dataFine,
                                      String ruolo, int partiteGiocate, int golEffettuati, int golSubiti, int ammonizioni, int espulsioni) {
        if (admin.inserisciMilitanza(codFisc, nomeSquadra, nazionalitaSquadra, dataInizio, dataFine, ruolo, partiteGiocate, golEffettuati, golSubiti, ammonizioni, espulsioni)) {
            listaMilitanze.add(new Militanza(getGiocatoreFromPK(codFisc), getSquadraFromPK(nomeSquadra, nazionalitaSquadra), ruolo, dataInizio, dataFine,
                    partiteGiocate, golEffettuati, golSubiti, ammonizioni, espulsioni));
            return true;
        }
        return false;
    }


    /**
     * Cerca una militanza scorrendo la lista del modello.
     *
     * @param g Il giocatore associato alla militanza.
     * @param s La squadra associata alla militanza.
     * @param dataInizio La data di inizio della militanza.
     * @return Restituisce la militanza recuperandola dalla sua chiave primaria, oppure null se non la trova.
     */
    private Militanza getMilitanzaFromPK(Giocatore g, Squadra s, LocalDate dataInizio) {
        for (Militanza m : listaMilitanze) {
            if (m.getGiocatore().equals(g) && m.getSquadra().equals(s) && m.getDataInizio().equals(dataInizio)) {
                return m;
            }
        }
        return null;
    }


    /**
     * Rimuove una militanza dal Database e, se a buon fine, la rimuove anche dal modello.
     *
     * @param codFisc Il codice fiscale del giocatore associato alla militanza.
     * @param nomeSquadra Il nome della squadra della militanza.
     * @param nazionalitaSquadra La nazionalità della squadra della militanza.
     * @param dataInizio La data di inizio della militanza.
     * @return True se l'operazione è andata a buon fine, altrimenti false.
     */
    public boolean rimuoviMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio) {
        if (admin.rimuoviMilitanza(codFisc, nomeSquadra, nazionalitaSquadra, dataInizio)) {
            Militanza m = getMilitanzaFromPK(getGiocatoreFromPK(codFisc), getSquadraFromPK(nomeSquadra, nazionalitaSquadra), dataInizio);
            listaMilitanze.remove(m);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Prende il nome del giocatore dal model ottenendolo dalla sua chiave primaria
     * @param codFisc La chiave primaria del giocatore
     * @return Il nome del giocatore selezionato
     */
    public String getNomeGiocatore(String codFisc){
        return getGiocatoreFromPK(codFisc).getNome();
    }
    /**
     * Prende il cognome del giocatore dal model ottenendolo dalla sua chiave primaria.
     *
     * @param codFisc La chiave primaria del giocatore di cui si desidera ottenere il cognome.
     * @return Il cognome del giocatore selezionato.
     */
    public String getCognomeGiocatore(String codFisc){
        return getGiocatoreFromPK(codFisc).getCognome();
    }

    /**
     * Prende il piede preferito dal giocatore dal model ottenendolo dalla sua chiave primaria.
     *
     * @param codFisc La chiave primaria del giocatore di cui si desidera ottenere il piede preferito.
     * @return Il piede preferito del giocatore selezionato.
     */
    public String getPiedeGiocatore(String codFisc){
        return getGiocatoreFromPK(codFisc).getPiede();
    }

    /**
     * Prende la data di nascita del giocatore dal model ottenendola dalla sua chiave primaria.
     *
     * @param codFisc La chiave primaria del giocatore di cui si desidera ottenere la data di nascita.
     * @return La data di nascita del giocatore selezionato.
     */
    public LocalDate getDataNascitaGiocatore(String codFisc){
        return getGiocatoreFromPK(codFisc).getDataDiNascita();
    }

    /**
     * Prende la data di ritiro del giocatore dal model ottenendola dalla sua chiave primaria.
     *
     * @param codFisc La chiave primaria del giocatore di cui si desidera ottenere la data di ritiro.
     * @return La data di ritiro del giocatore selezionato.
     */
    public LocalDate getDataRitiroGiocatore(String codFisc){
        return getGiocatoreFromPK(codFisc).getDataRitiro();
    }


    /**
     * Prende il ruolo del giocatore in una militanza dal model ottenendolo dalla sua chiave primaria.
     *
     * @param codFisc La chiave primaria del giocatore.
     * @param nomeSquadra Il nome della squadra.
     * @param nazionalitaSquadra La nazionalità della squadra.
     * @param dataInizio La data di inizio della militanza.
     * @return Il ruolo del giocatore nella militanza selezionata.
     */
    public String getRuoloMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio){
        return getMilitanzaFromPK(getGiocatoreFromPK(codFisc), getSquadraFromPK(nomeSquadra, nazionalitaSquadra), dataInizio).getRuolo();
    }


    /**
     * Prende la data di fine di una militanza dal model ottenendola dalla sua chiave primaria.
     *
     * @param codFisc La chiave primaria del giocatore.
     * @param nomeSquadra Il nome della squadra.
     * @param nazionalitaSquadra La nazionalità della squadra.
     * @param dataInizio La data di inizio della militanza.
     * @return La data di fine della militanza selezionata.
     */
    public LocalDate getDataFineMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio){
        return getMilitanzaFromPK(getGiocatoreFromPK(codFisc), getSquadraFromPK(nomeSquadra, nazionalitaSquadra), dataInizio).getDataFine();
    }

    /**
     * Prende le partite giocate di una militanza dal model ottenendole dalla sua chiave primaria.
     *
     * @param codFisc La chiave primaria del giocatore.
     * @param nomeSquadra Il nome della squadra.
     * @param nazionalitaSquadra La nazionalità della squadra.
     * @param dataInizio La data di inizio della militanza.
     * @return Le partite giocate della militanza selezionata.
     */
    public int getPartiteGiocateMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio){
        return getMilitanzaFromPK(getGiocatoreFromPK(codFisc), getSquadraFromPK(nomeSquadra, nazionalitaSquadra), dataInizio).getPartiteGiocate();
    }

    /**
     * Prende i goal segnati di una militanza dal model ottenendoli dalla sua chiave primaria.
     *
     * @param codFisc La chiave primaria del giocatore.
     * @param nomeSquadra Il nome della squadra.
     * @param nazionalitaSquadra La nazionalità della squadra.
     * @param dataInizio La data di inizio della militanza.
     * @return I goal segnati della militanza selezionata.
     */
    public int getGoalSegnatiMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio){
        return getMilitanzaFromPK(getGiocatoreFromPK(codFisc), getSquadraFromPK(nomeSquadra, nazionalitaSquadra), dataInizio).getGoalSegnati();
    }

    /**
     * Prende i goal subiti di una militanza dal model ottenendoli dalla sua chiave primaria.
     *
     * @param codFisc La chiave primaria del giocatore.
     * @param nomeSquadra Il nome della squadra.
     * @param nazionalitaSquadra La nazionalità della squadra.
     * @param dataInizio La data di inizio della militanza.
     * @return I goal subiti della militanza selezionata.
     */
    public int getGoalSubitiMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio){
        return getMilitanzaFromPK(getGiocatoreFromPK(codFisc), getSquadraFromPK(nomeSquadra, nazionalitaSquadra), dataInizio).getGoalSubiti();
    }

    /**
     * Prende le ammonizioni di una militanza dal model ottenendole dalla sua chiave primaria.
     *
     * @param codFisc La chiave primaria del giocatore.
     * @param nomeSquadra Il nome della squadra.
     * @param nazionalitaSquadra La nazionalità della squadra.
     * @param dataInizio La data di inizio della militanza.
     * @return Le ammonizioni della militanza selezionata.
     */
    public int getAmmonizioniMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio){
        return getMilitanzaFromPK(getGiocatoreFromPK(codFisc), getSquadraFromPK(nomeSquadra, nazionalitaSquadra), dataInizio).getAmmonizioni();
    }

    /**
     * Prende le espulsioni di una militanza dal model ottenendole dalla sua chiave primaria.
     *
     * @param codFisc La chiave primaria del giocatore.
     * @param nomeSquadra Il nome della squadra.
     * @param nazionalitaSquadra La nazionalità della squadra.
     * @param dataInizio La data di inizio della militanza.
     * @return Le espulsioni della militanza selezionata.
     */
    public int getEspulsioniMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio){
        return getMilitanzaFromPK(getGiocatoreFromPK(codFisc), getSquadraFromPK(nomeSquadra, nazionalitaSquadra), dataInizio).getEspulsioni();
    }


    /**
     * Modifica una militanza nel database e, se a buon fine, la modifica nel modello.
     *
     * @param codFisc La chiave primaria del giocatore.
     * @param nomeSquadra Il nome della squadra.
     * @param nazionalitaSquadra La nazionalità della squadra.
     * @param dataInizio La data di inizio della militanza.
     * @param nuovoCodFisc La nuova chiave primaria del giocatore.
     * @param nuovoNomeSquadra Il nuovo nome della squadra.
     * @param nuovaNazionalitaSquadra La nuova nazionalità della squadra.
     * @param nuovaDataInizio La nuova data di inizio della militanza.
     * @param dataFine La data di fine della militanza.
     * @param ruolo Il ruolo del giocatore nella militanza.
     * @param partiteGiocate Il numero di partite giocate.
     * @param goalSegnati Il numero di goal segnati.
     * @param goalSubiti Il numero di goal subiti.
     * @param ammonizioni Il numero di ammonizioni.
     * @param espulsioni Il numero di espulsioni.
     * @return True se la modifica è avvenuta con successo, altrimenti false.
     */
    public boolean modificaMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, String nuovoCodFisc, String nuovoNomeSquadra, String nuovaNazionalitaSquadra, LocalDate nuovaDataInizio, LocalDate dataFine, String ruolo, int partiteGiocate, int goalSegnati, int goalSubiti, int ammonizioni, int espulsioni){
        if(admin.modificaMilitanza(codFisc, nomeSquadra, nazionalitaSquadra, dataInizio, nuovoCodFisc, nuovoNomeSquadra, nuovaNazionalitaSquadra, nuovaDataInizio, dataFine, ruolo, partiteGiocate, goalSegnati, goalSubiti, ammonizioni, espulsioni)){
            Militanza m = getMilitanzaFromPK(getGiocatoreFromPK(codFisc), getSquadraFromPK(nomeSquadra, nazionalitaSquadra), dataInizio);
            m.setGiocatore(getGiocatoreFromPK(nuovoCodFisc));
            m.setSquadra(getSquadraFromPK(nuovoNomeSquadra, nuovaNazionalitaSquadra));
            m.setRuolo(ruolo);
            m.setDataInizio(nuovaDataInizio);
            m.setDataFine(dataFine);
            m.setPartiteGiocate(partiteGiocate);
            m.setGoalSegnati(goalSegnati);
            m.setGoalSubiti(goalSubiti);
            m.setAmmonizioni(ammonizioni);
            m.setEspulsioni(espulsioni);
            return true;
        }
        return false;
    }


    /**
     * Inserisce un giocatore nel database e, se a buon fine, lo inserisce nel modello.
     *
     * @param nome Il nome del giocatore da inserire.
     * @param cognome Il cognome del giocatore da inserire.
     * @param codFisc Il codice fiscale del giocatore da inserire.
     * @param piede Il piede preferito del giocatore da inserire.
     * @param dataDiNascita La data di nascita del giocatore da inserire.
     * @return True se l'inserimento è avvenuto con successo, altrimenti false.
     */
    public boolean inserisciGiocatore(String nome, String cognome, String codFisc, String piede, LocalDate dataDiNascita){
        if(admin.inserisciGiocatore(nome, cognome, codFisc, piede, dataDiNascita)) {
            listaGiocatori.add(new Giocatore(nome, cognome, codFisc, piede, dataDiNascita));
            return true;
        } else {
            return false;
        }
    }


    /**
     * Rimuove un giocatore dal database e, se a buon fine, lo rimuove dal modello.
     *
     * @param codFisc Il codice fiscale del giocatore da rimuovere.
     * @return True se la rimozione è avvenuta con successo, altrimenti false.
     */
    public boolean rimuoviGiocatore(String codFisc){
        if(admin.rimuoviGiocatore(codFisc)){
            listaGiocatori.removeIf(giocatore -> giocatore.getCodFisc().equals(codFisc));
            return true;
        } else {
            return false;
        }
    }


    /**
     * Modifica un giocatore nel database e controlla se la sua data di ritiro è stata inserita.
     * Se a buon fine, lo modifica anche nel modello.
     *
     * @param vecchiocodfisc Il vecchio codice fiscale del giocatore.
     * @param nuovocodfisc Il nuovo codice fiscale del giocatore.
     * @param nome Il nome del giocatore.
     * @param cognome Il cognome del giocatore.
     * @param piede Il piede preferito del giocatore.
     * @param datanascita La data di nascita del giocatore.
     * @param dataritiro La data di ritiro del giocatore (se presente).
     * @return True se la modifica è avvenuta con successo, altrimenti false.
     */
    public boolean updateGiocatore(String vecchiocodfisc, String nuovocodfisc, String nome, String cognome, String piede, LocalDate datanascita, LocalDate dataritiro){
        Giocatore g = getGiocatoreFromPK(vecchiocodfisc);
        if(dataritiro.getYear() == 0){
            if(admin.updateGiocatoreSenzaDataRitiro(vecchiocodfisc, nuovocodfisc, nome, cognome, piede, datanascita)){
                g.setCodFisc(nuovocodfisc);
                g.setCognome(cognome);
                g.setNome(nome);
                g.setDataDiNascita(datanascita);
                g.setPiede(piede);
                return true;
            }
        } else {
            if(admin.updateGiocatoreConDataRitiro(vecchiocodfisc, nuovocodfisc, nome, cognome, piede, datanascita, dataritiro)){
                g.setCodFisc(nuovocodfisc);
                g.setCognome(cognome);
                g.setNome(nome);
                g.setDataDiNascita(datanascita);
                g.setPiede(piede);
                g.setDataRitiro(dataritiro);
                return true;
            }
        }
        return false;
    }


    /**
     * Svuota il model dai dati
     */

    public void emptyModel() {
        listaGiocatori.clear();
        listaCampionati.clear();
        listaSquadre.clear();
        listaTrofeiSquadra.clear();
        listaTrofeiIndividuali.clear();
        listaMilitanze.clear();
        listaCaratteristiche.clear();
    }

    /**
     * Rimuove una squadra dal database e, se a buon fine, la rimuove anche dal modello.
     *
     * @param nome Il nome della squadra da rimuovere.
     * @param nazionalita La nazionalità della squadra da rimuovere.
     * @return True se la rimozione è avvenuta con successo, altrimenti false.
     */
    public boolean rimuoviSquadra(String nome, String nazionalita) {
        if(admin.rimuoviSquadra(nome, nazionalita)){
            Squadra s = getSquadraFromPK(nome, nazionalita);
            listaSquadre.remove(s);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Aggiorna una squadra nel database e, se a buon fine, la modifica anche nel modello.
     *
     * @param nomevecchio Il nome attuale della squadra.
     * @param nazionalitavecchio La nazionalità attuale della squadra.
     * @param nomenuovo Il nuovo nome della squadra.
     * @param nazionalitanuovo La nuova nazionalità della squadra.
     * @return True se l'aggiornamento è avvenuto con successo, altrimenti false.
     */
    public boolean updateSquadra(String nomevecchio, String nazionalitavecchio, String nomenuovo, String nazionalitanuovo) {
        if(admin.updateSquadra(nomevecchio, nazionalitavecchio, nomenuovo, nazionalitanuovo)){
            Squadra s = getSquadraFromPK(nomevecchio, nazionalitavecchio);
            s.setNome(nomenuovo);
            s.setNazionalita(nazionalitanuovo);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Riempie liste di attributi di campionati ottenendoli dal modello.
     *
     * @param nomi Lista dove inserire i nomi dei campionati.
     * @param id Lista dove inserire gli ID dei campionati.
     * @param anni Lista dove inserire gli anni dei campionati.
     */
    public void getCampionati(ArrayList<String> nomi, ArrayList<Integer> id, ArrayList<String> anni) {
        for(Campionato c: listaCampionati){
            nomi.add(c.getNome());
            id.add(c.getIdCampionato());
            anni.add(c.getAnno());
        }
    }


    /**
     * Inserisce una squadra nel database e, se a buon fine, la inserisce anche nel modello.
     *
     * @param nome Il nome della squadra da inserire.
     * @param nazionalita La nazionalità della squadra da inserire.
     * @param id L'ID del campionato a cui la squadra appartiene.
     * @return True se l'inserimento è avvenuto con successo, altrimenti false.
     */
    public boolean inserisciSquadra(String nome, String nazionalita, int id) {
        if(admin.inserisciSquadra(nome, nazionalita, id)){
            listaSquadre.add(new Squadra(nome, nazionalita, getCampionatoFromID(id)));
            return true;
        } else {
            return false;
        }
    }


    /**
     * Rimuove un campionato dal database e, se a buon fine, lo rimuove dal modello.
     *
     * @param id L'ID del campionato da rimuovere.
     * @return True se la rimozione è avvenuta con successo, altrimenti false.
     */
    public boolean rimuoviCampionato(int id) {
        if(admin.rimuoviCampionato(id)){
            Campionato c = getCampionatoFromID(id);
            listaCampionati.remove(c);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Inserisce un campionato nel database e, se a buon fine, anche nel modello.
     *
     * @param id L'ID del campionato da inserire.
     * @param nome Il nome del campionato da inserire.
     * @param anno L'anno del campionato da inserire.
     * @return True se l'inserimento è avvenuto con successo, altrimenti false.
     */
    public boolean inserisciCampionato(int id, String nome, String anno) {
        if(admin.insertCampionato(id, nome, anno)){
            Campionato c = new Campionato(nome, anno, id);
            listaCampionati.add(c);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Modifica un campionato nel database e, se a buon fine, lo modifica nel modello.
     *
     * @param id L'ID del campionato da modificare.
     * @param nome Il nuovo nome del campionato.
     * @param anno Il nuovo anno del campionato.
     * @return True se la modifica è avvenuta con successo, altrimenti false.
     */
    public boolean modificaCampionato(int id, String nome, String anno) {
        if(admin.modificaAnnoCampionato(id, anno) && admin.modificaNomeCampionato(id, nome)){
            Campionato c = new Campionato(nome, anno, id);
            listaCampionati.set(id, c);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Chiama una funzione che prende attributi dei trofei individuali dal database.
     *
     * @param nomi Lista dove inserire i nomi dei trofei.
     * @param anni Lista dove inserire gli anni dei trofei.
     * @param meriti Lista dove inserire i meriti dei trofei.
     * @param codf Lista dove inserire i codici fiscali associati ai trofei.
     * @return True se il recupero dei trofei individuali è avvenuto con successo, altrimenti false.
     */
    public boolean visualizzaTrofeiIndividuali(ArrayList<String> nomi, ArrayList<String> anni, ArrayList<String> meriti, ArrayList<String> codf) {
        return admin.fetchTrofeiIndividiualiFromDB(nomi, anni, meriti, codf);
    }


    /**
     * Rimuove un trofeo individuale dal database e, se a buon fine, lo rimuove dal modello.
     *
     * @param nomeSelezionato Il nome del trofeo individuale da rimuovere.
     * @param annoSelezionato L'anno del trofeo individuale da rimuovere.
     * @return True se la rimozione è avvenuta con successo, altrimenti false.
     */
    public boolean rimuoviTrofeoIndividuale(String nomeSelezionato, String annoSelezionato) {
        if(admin.rimuoviTrofeoIndividuale(nomeSelezionato, annoSelezionato)){
            listaTrofeiIndividuali.removeIf(t -> (Objects.equals(t.getNome(), nomeSelezionato)) && (Objects.equals(t.getAnno(), annoSelezionato)));
            return true;
        } else {
            return false;
        }
    }


    /**
     * Rimuove un trofeo di squadra dal database e, se a buon fine, lo rimuove dal modello.
     *
     * @param nomeSelezionato Il nome del trofeo di squadra da rimuovere.
     * @param annoSelezionato L'anno del trofeo di squadra da rimuovere.
     * @return True se la rimozione è avvenuta con successo, altrimenti false.
     */
    public boolean rimuoviTrofeoDiSquadra(String nomeSelezionato, String annoSelezionato) {
        if(admin.rimuoviTrofeoSquadra(nomeSelezionato, annoSelezionato)){
            listaTrofeiSquadra.removeIf(t -> (Objects.equals(t.getNome(), nomeSelezionato)) && (Objects.equals(t.getAnno(), annoSelezionato)));
            return true;
        } else {
            return false;
        }
    }


    /**
     * Aggiorna un trofeo individuale nel database e, se a buon fine, lo aggiorna nel modello.
     *
     * @param nuovocodf Il nuovo codice fiscale del giocatore vincitore.
     * @param nuovonometrofeo Il nuovo nome del trofeo individuale.
     * @param nuovoanno Il nuovo anno del trofeo individuale.
     * @param nuovomerito Il nuovo merito del trofeo individuale.
     * @param codf Il codice fiscale attuale del giocatore vincitore del trofeo.
     * @param nometrofeo Il nome attuale del trofeo individuale.
     * @param anno L'anno attuale del trofeo individuale.
     * @param merito Il merito attuale del trofeo individuale.
     * @return True se la modifica è avvenuta con successo, altrimenti false.
     */
    public boolean modificaTrofeoIndividuale(String nuovocodf, String nuovonometrofeo, String nuovoanno, String nuovomerito, String codf, String nometrofeo, String anno, String merito) {
        if(admin.modificaTrofeoIndividuale(codf, nometrofeo, anno, merito, nuovocodf, nuovonometrofeo, nuovoanno, nuovomerito)){
            TrofeoIndividuale t = new TrofeoIndividuale(nometrofeo, anno, merito, getGiocatoreFromPK(codf));
            t.setNome(nuovonometrofeo);
            t.setGiocatoreVincitore(getGiocatoreFromPK(nuovocodf));
            t.setAnno(nuovoanno);
            t.setMerito(nuovomerito);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Inserisce un trofeo individuale nel database e, se a buon fine, nel modello.
     *
     * @param codf Il codice fiscale del giocatore vincitore del trofeo.
     * @param nometrofeo Il nome del trofeo individuale da inserire.
     * @param anno L'anno del trofeo individuale da inserire.
     * @param merito Il merito del trofeo individuale da inserire.
     * @return True se l'inserimento è avvenuto con successo, altrimenti false.
     */
    public boolean inserisciTrofeoIndividuale(String codf, String nometrofeo, String anno, String merito) {
        if(admin.inserisciTrofeoIndividuale(codf, nometrofeo, anno, merito)){
            TrofeoIndividuale t = new TrofeoIndividuale(nometrofeo, anno, merito, getGiocatoreFromPK(codf));
            return true;
        } else {
            return false;
        }
    }


    /**
     * Aggiorna un trofeo di squadra nel database e, se a buon fine, lo aggiorna nel modello.
     *
     * @param nomesquadra Il nome attuale della squadra vincitrice del trofeo.
     * @param nazionalita La nazionalità attuale della squadra vincitrice del trofeo.
     * @param nometrofeo Il nome attuale del trofeo di squadra.
     * @param anno L'anno attuale del trofeo di squadra.
     * @param merito Il merito attuale del trofeo di squadra.
     * @param nuovasquadra Il nuovo nome della squadra vincitrice del trofeo.
     * @param nuovanazionalita La nuova nazionalità della squadra vincitrice del trofeo.
     * @param nuovonometrofeo Il nuovo nome del trofeo di squadra.
     * @param nuovoanno Il nuovo anno del trofeo di squadra.
     * @param nuovomerito Il nuovo merito del trofeo di squadra.
     * @return True se la modifica è avvenuta con successo, altrimenti false.
     */
    public boolean modificaTrofeoDiSquadra(String nomesquadra, String nazionalita, String nometrofeo, String anno, String merito, String nuovasquadra, String nuovanazionalita, String nuovonometrofeo, String nuovoanno, String nuovomerito) {
        if(admin.modificaTrofeoDiSquadra(nometrofeo, anno, nuovasquadra, nuovanazionalita, nuovonometrofeo, nuovoanno, nuovomerito)){
            TrofeoSquadra t = new TrofeoSquadra(nometrofeo, anno, merito, getSquadraFromPK(nomesquadra, nazionalita));
            t.setAnno(nuovoanno);
            t.setMerito(nuovomerito);
            t.setNome(nuovonometrofeo);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Chiama una funzione che riempie le liste di attributi di trofei di squadra dal database.
     *
     * @param nomi Lista in cui verranno inseriti i nomi dei trofei di squadra ottenuti dal database.
     * @param anni Lista in cui verranno inseriti gli anni dei trofei di squadra ottenuti dal database.
     * @param meriti Lista in cui verranno inseriti i meriti dei trofei di squadra ottenuti dal database.
     * @param squadre Lista in cui verranno inseriti i nomi delle squadre vincitrici dei trofei di squadra ottenuti dal database.
     * @param nazionalita Lista in cui verranno inserite le nazionalità delle squadre vincitrici dei trofei di squadra ottenuti dal database.
     * @return True se il recupero dei trofei di squadra è avvenuto con successo, altrimenti false.
     */
    public boolean visualizzaTrofeiSquadra(ArrayList<String> nomi, ArrayList<String> anni, ArrayList<String> meriti, ArrayList<String> squadre, ArrayList<String> nazionalita) {
        return admin.fetchTrofeiDiSquadraFromDB(nomi, anni, meriti, squadre, nazionalita);
    }


    /**
     * Inserisce un trofeo di squadra nel database e, se a buon fine, nel modello.
     *
     * @param nomesquadra Il nome della squadra vincitrice del trofeo di squadra da inserire.
     * @param nazionalita La nazionalità della squadra vincitrice del trofeo di squadra da inserire.
     * @param nometrofeo Il nome del trofeo di squadra da inserire.
     * @param anno L'anno del trofeo di squadra da inserire.
     * @param merito Il merito del trofeo di squadra da inserire.
     * @return True se l'inserimento è avvenuto con successo, altrimenti false.
     */
    public boolean inserisciTrofeoDiSquadra(String nomesquadra, String nazionalita, String nometrofeo, String anno, String merito) {
        if(admin.inserisciTrofeoDiSquadra(nomesquadra, nazionalita, nometrofeo, anno, merito)){
            TrofeoSquadra t = new TrofeoSquadra(nometrofeo, anno, merito, getSquadraFromPK(nomesquadra, nazionalita));
            listaTrofeiSquadra.add(t);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Restituisce la lista di caratteristiche conservate nel modello.
     *
     * @return La lista di caratteristiche conservate nel modello.
     */
    public ArrayList<String> getListaCaratteristiche() {
        return listaCaratteristiche;
    }


    /**
     * Inserisce una caratteristica nel database e, se a buon fine, nel modello.
     *
     * @param caratteristica La caratteristica da inserire.
     * @return True se l'inserimento è avvenuto con successo, altrimenti false.
     */
    public boolean inserisciCaratteristica(String caratteristica) {
        if(admin.aggiungiCaratteristica(caratteristica)){
            listaCaratteristiche.add(caratteristica);
            return true;
        } else {
            return false;
        }
    }


    /**
     * Modifica una caratteristica nel database e, se a buon fine, nel modello.
     *
     * @param caratteristicaSelezionata La caratteristica da modificare.
     * @param caratteristica La nuova caratteristica.
     * @return True se la modifica è avvenuta con successo, altrimenti false.
     */
    public boolean modificaCaratteristica(String caratteristicaSelezionata, String caratteristica) {
        if(admin.modificaCaratteristica(caratteristicaSelezionata, caratteristica)){
            listaCaratteristiche.removeIf(caratteristicaAttuale -> caratteristicaAttuale.equals(caratteristicaSelezionata));
            listaCaratteristiche.add(caratteristica);
            for(Giocatore g : listaGiocatori){
                for(String s : g.getListaCaratteristiche()) {
                    if(s.equals(caratteristicaSelezionata)){
                        g.getListaCaratteristiche().remove(caratteristicaSelezionata);
                        g.getListaCaratteristiche().add(caratteristica);
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }


    /**
     * Rimuove una caratteristica dal database e, se a buon fine, dal modello.
     *
     * @param caratteristica La caratteristica da rimuovere.
     * @return True se la rimozione è avvenuta con successo, altrimenti false.
     */
    public boolean rimuoviCaratteristica(String caratteristica) {
        if(admin.rimuoviCaratteristica(caratteristica)){
            listaCaratteristiche.remove(caratteristica);
            for(Giocatore g : listaGiocatori){
                g.getListaCaratteristiche().remove(caratteristica);
            }
            return true;
        } else {
            return false;
        }
    }


    /**
     * Cerca tutte le caratteristiche che un giocatore non possiede.
     *
     * @param codFisc Il codice fiscale del giocatore.
     * @return Una lista di caratteristiche non possedute dal giocatore.
     */
    public ArrayList<String> getCaratteristicheDaAggiungereGiocatore(String codFisc) {
        ArrayList<String> daRitornare = new ArrayList<>();
        Giocatore g = getGiocatoreFromPK(codFisc);
        for(String s : listaCaratteristiche){
            if(!(g.getListaCaratteristiche().contains(s))) {
                daRitornare.add(s);
            }
        }
        return daRitornare;
    }


    /**
     * Aggiunge una caratteristica al giocatore nel database e, se a buon fine, nel modello.
     *
     * @param codFisc Il codice fiscale del giocatore.
     * @param caratteristica La caratteristica da aggiungere.
     * @return True se l'aggiunta è avvenuta con successo, altrimenti false.
     */
    public boolean aggiungiCaratteristicaGiocatore(String codFisc, String caratteristica) {
        if(admin.aggiungiCaratteristicaGiocatore(codFisc, caratteristica)){
            Giocatore g = getGiocatoreFromPK(codFisc);
            g.getListaCaratteristiche().add(caratteristica);
            return true;
        }
        return false;
    }


    /**
     * Prende la lista di caratteristiche possedute da un giocatore.
     *
     * @param codFisc Il codice fiscale del giocatore.
     * @return La lista di caratteristiche possedute dal giocatore.
     */
    public ArrayList<String> getCaratteristicheGiocatore(String codFisc) {
        Giocatore g = getGiocatoreFromPK(codFisc);
        return g.getListaCaratteristiche();
    }


    /**
     * Rimuove una caratteristica da un giocatore nel database e, se a buon fine, nel model.
     *
     * @param codFisc Il codice fiscale del giocatore.
     * @param caratteristica La caratteristica da rimuovere.
     * @return True se l'operazione ha successo, altrimenti false.
     */
    public boolean rimuoviCaratteristicaGiocatore(String codFisc, String caratteristica) {
        if (admin.rimuoviCaratteristicaGiocatore(codFisc, caratteristica)) {
            Giocatore g = getGiocatoreFromPK(codFisc);
            g.getListaCaratteristiche().remove(caratteristica);
            return true;
        }
        return false;
    }
}