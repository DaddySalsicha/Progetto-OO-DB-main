package DAO;

import java.time.LocalDate;

public interface AdminDAO extends UtenteDAO {
    /**
     * Interfaccia del metodo per controllare se l'utente è admin o meno
     * @return true se è un amministratore, false altrimenti
     */
    public boolean checkAdmin();

    /**
     * Interfaccia del metodo per inserire un giocatore nel database
     * @param nome Nome del giocatore
     * @param cognome Cognome del giocatore
     * @param codFisc Codice fiscale del giocatore
     * @param piede Piede dominante del giocatore
     * @param dataDiNascita Data di nascita del giocatore
     * @return true se l'inserimento ha avuto successo, altrimenti false
     */
    public boolean inserisciGiocatore(String nome, String cognome, String codFisc, String piede, LocalDate dataDiNascita);
    /**
     * Interfaccia del metodo per inserire un trofeo individuale nel database
     * @param codF Codice fiscale del giocatore vincitore del trofeo
     * @param nome Nome del trofeo individuale
     * @param anno Anno del trofeo individuale
     * @param merito Merito associato al trofeo individuale
     * @return true se l'inserimento nel database ha avuto successo, altrimenti false
     */
    public boolean inserisciTrofeoIndividuale(String nome, String anno, String merito, String codF);
    /**
     * Interfaccia del metodo per inserire una squadra nel database
     * @param nome Nome della squadra da inserire
     * @param nazionalita Nazionalità della squadra da inserire
     * @param idCampionato ID del campionato a cui appartiene la squadra
     * @return true se l'inserimento nel database ha avuto successo, altrimenti false
     */
    public boolean inserisciSquadra(String nome, String nazionalita, int idCampionato);
    /**
     * Interfaccia del metodo per inserire una caratteristica nel database
     * @param tipoCaratteristica La nuova caratteristica da aggiungere
     * @return true se l'aggiunta nel database ha avuto successo, altrimenti false
     */
    public boolean aggiungiCaratteristica(String tipoCaratteristica);
    /**
     * Interfaccia del metodo per aggiungere una nuova caratteristica al giocatore nel database
     * @param codFisc Il codice fiscale del giocatore a cui aggiungere la caratteristica
     * @param caratteristica La nuova caratteristica da aggiungere al giocatore
     * @return true se l'aggiunta nel database ha avuto successo, altrimenti false
     */
    public boolean aggiungiCaratteristicaGiocatore(String codFisc, String caratteristica);
    /**
     * Interfaccia del metodo per inserire un nuovo campionato nel database.
     * @param idCampionato L'ID del campionato da inserire
     * @param nome Il nome del campionato da inserire
     * @param anno L'anno del campionato da inserire
     * @return true se l'inserimento nel database ha avuto successo, altrimenti false
     */
    public boolean insertCampionato(int idCampionato, String nome, String anno);
    /**
     * Interfaccia del metodo per modificare il nome di un campionato nel database.
     * @param idCampionato L'ID del campionato da modificare
     * @param nuovoNome Il nuovo nome da assegnare al campionato
     * @return true se la modifica nel database ha avuto successo, altrimenti false
     */
    public boolean modificaNomeCampionato(int idCampionato, String nuovoNome);
    /**
     * Interfaccia del metodo per modificare l'anno di un campionato nel database.
     * @param idCampionato L'ID del campionato da modificare
     * @param nuovoAnno Il nuovo anno da assegnare al campionato
     * @return true se la modifica nel database ha avuto successo, altrimenti false
     */
    public boolean modificaAnnoCampionato(int idCampionato, String nuovoAnno);
    /**
     * Interfaccia del metodo per inserire una militanza di un giocatore nel database.
     * @param codFisc Il codice fiscale del giocatore
     * @param nomeSquadra Il nome della squadra in cui il giocatore milita
     * @param nazionalitaSquadra La nazionalità della squadra
     * @param dataInizio La data di inizio della militanza
     * @param dataFine La data di fine della militanza
     * @param ruolo Il ruolo giocato dal giocatore nella squadra
     * @param partiteGiocate Il numero di partite giocate dal giocatore nella squadra
     * @param golEffettuati Il numero di gol effettuati dal giocatore nella squadra
     * @param golSubiti Il numero di gol subiti dal giocatore nella squadra
     * @param ammonizioni Il numero di ammonizioni ricevute dal giocatore nella squadra
     * @param espulsioni Il numero di espulsioni ricevute dal giocatore nella squadra
     * @return true se l'inserimento nel database ha avuto successo, altrimenti false
     */
    public boolean inserisciMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio,
                                      LocalDate dataFine, String ruolo, int partiteGiocate, int golEffettuati, int golSubiti,
                                      int ammonizioni, int espulsioni);
    /**
     * Interfaccia del metodo per rimuovere un giocatore dal database.
     * @param codFisc Il codice fiscale del giocatore da rimuovere
     * @return true se la rimozione dal database ha avuto successo, altrimenti false
     */
    public boolean rimuoviGiocatore(String codFisc);
    /**
     * Interfaccia del metodo per rimuovere una caratteristica associata a un giocatore dal database.
     * @param codFisc Il codice fiscale del giocatore
     * @param caratteristica La caratteristica da rimuovere
     * @return true se la rimozione dal database ha avuto successo, altrimenti false
     */
    public boolean rimuoviCaratteristicaGiocatore(String codFisc, String caratteristica);
    /**
     * Interfaccia del metodo per modificare una caratteristica nel database.
     * @param caratteristica La caratteristica da modificare
     * @param nuovaCaratteristica La nuova caratteristica
     * @return true se la modifica nel database ha avuto successo, altrimenti false
     */
    public boolean modificaCaratteristica(String caratteristica, String nuovaCaratteristica);
    /**
     * Interfaccia del metodo per rimuovere una caratteristica dal database.
     * @param tipoCaratteristica La caratteristica da rimuovere
     * @return true se la rimozione dal database ha avuto successo, altrimenti false
     */
    public boolean rimuoviCaratteristica(String tipoCaratteristica);
    /**
     * Interfaccia del metodo per rimuovere una militanza dal database.
     * @param codFisc Il codice fiscale del giocatore associato alla militanza
     * @param nomeSquadra Il nome della squadra associata alla militanza
     * @param nazionalitaSquadra La nazionalità della squadra associata alla militanza
     * @param dataInizio La data di inizio della militanza
     * @return true se la rimozione dal database ha avuto successo, altrimenti false
     */
    public boolean rimuoviMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio);
    /**
     * Interfaccia del metodo per modificare una militanza nel database.
     * @param codFisc Il codice fiscale del giocatore associato alla militanza da modificare
     * @param nomeSquadra Il nome della squadra associata alla militanza da modificare
     * @param nazionalitaSquadra La nazionalità della squadra associata alla militanza da modificare
     * @param dataInizio La data di inizio della militanza da modificare
     * @param nuovoCodFisc Il nuovo codice fiscale del giocatore associato alla militanza
     * @param nuovoNomeSquadra Il nuovo nome della squadra associata alla militanza
     * @param nuovaNazionalitaSquadra La nuova nazionalità della squadra associata alla militanza
     * @param nuovaDataInizio La nuova data di inizio della militanza
     * @param dataFine La data di fine della militanza
     * @param ruolo Il ruolo del giocatore nella militanza
     * @param partiteGiocate Il numero di partite giocate nella militanza
     * @param goalSegnati Il numero di goal segnati nella militanza
     * @param goalSubiti Il numero di goal subiti nella militanza
     * @param ammonizioni Il numero di ammonizioni ricevute nella militanza
     * @param espulsioni Il numero di espulsioni ricevute nella militanza
     * @return true se la modifica nel database ha avuto successo, altrimenti false
     */
    public boolean modificaMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, String nuovoCodFisc, String nuovoNomeSquadra, String nuovaNazionalitaSquadra, LocalDate nuovaDataInizio, LocalDate dataFine, String ruolo, int partiteGiocate, int goalSegnati, int goalSubiti, int ammonizioni, int espulsioni);
    /**
     * Interfaccia del metodo per rimuovere una squadra dal database.
     * @param nomeSquadra Il nome della squadra da rimuovere
     * @param nazionalitaSquadra La nazionalità della squadra da rimuovere
     * @return true se la rimozione dal database ha avuto successo, altrimenti false
     */
    public boolean rimuoviSquadra(String nomeSquadra, String nazionalitaSquadra);
    /**
     * Interfaccia del metodo per rimuovere un trofeo di squadra dal database.
     * @param nome Il nome del trofeo di squadra da rimuovere
     * @param anno L'anno del trofeo di squadra da rimuovere
     * @return true se la rimozione dal database ha avuto successo, altrimenti false
     */
    public boolean rimuoviTrofeoSquadra(String nome, String anno);
    /**
     * Interfaccia del metodo per rimuovere un trofeo individuale dal database.
     * @param nome Il nome del trofeo individuale da rimuovere
     * @param anno L'anno del trofeo individuale da rimuovere
     * @return true se la rimozione dal database ha avuto successo, altrimenti false
     */
    public boolean rimuoviTrofeoIndividuale(String nome, String anno);
    /**
     * Interfaccia del metodo per aggiornare i dati di un giocatore nel database, inclusa la data di ritiro.
     * @param vecchioCodFisc Il vecchio codice fiscale del giocatore da aggiornare
     * @param nuovoCodFisc Il nuovo codice fiscale del giocatore
     * @param nuovoNome Il nuovo nome del giocatore
     * @param nuovoCognome Il nuovo cognome del giocatore
     * @param nuovoPiede Il nuovo piede dominante del giocatore
     * @param nuovaDataNascita La nuova data di nascita del giocatore
     * @param nuovaDataRitiro La nuova data di ritiro del giocatore
     * @return true se l'aggiornamento ha avuto successo, altrimenti false
     */
    public boolean updateGiocatoreConDataRitiro(String vecchioCodFisc, String nuovoCodFisc, String nuovoNome, String nuovoCognome, String nuovoPiede, LocalDate nuovaDataNascita, LocalDate nuovaDataRitiro);
    /**
     * Interfaccia del metodo per aggiornare i dati di un giocatore nel database, escludendo la data di ritiro.
     * @param vecchioCodFisc Il vecchio codice fiscale del giocatore da aggiornare
     * @param nuovoCodFisc Il nuovo codice fiscale del giocatore
     * @param nuovoNome Il nuovo nome del giocatore
     * @param nuovoCognome Il nuovo cognome del giocatore
     * @param nuovoPiede Il nuovo piede dominante del giocatore
     * @param nuovaDataNascita La nuova data di nascita del giocatore
     * @return true se l'aggiornamento ha avuto successo, altrimenti false
     */
    public boolean updateGiocatoreSenzaDataRitiro(String vecchioCodFisc, String nuovoCodFisc, String nuovoNome, String nuovoCognome, String nuovoPiede, LocalDate nuovaDataNascita);
    /**
     * Interfaccia del metodo per aggiornare i dati di una squadra nel database.
     * @param vecchioNome Il nome attuale della squadra da aggiornare
     * @param vecchiaNazionalita La nazionalità attuale della squadra da aggiornare
     * @param nuovoNome Il nuovo nome della squadra
     * @param nuovaNazionalita La nuova nazionalità della squadra
     * @return true se l'aggiornamento ha avuto successo, altrimenti false
     */
    public boolean updateSquadra(String vecchioNome, String vecchiaNazionalita, String nuovoNome, String nuovaNazionalita);
    /**
     * Interfaccia del metodo per rimuovere un campionato dal database insieme a tutte le squadre a esso associate.
     * @param id L'identificativo del campionato da rimuovere
     * @return true se la rimozione ha avuto successo, altrimenti false
     */
    public boolean rimuoviCampionato(int id);
    /**
     * Interfaccia del metodo per la modifica un trofeo individuale nel database.
     * @param codF Il codice fiscale del giocatore a cui è associato il trofeo individuale
     * @param nomeTrofeo Il nome del trofeo individuale da modificare
     * @param anno L'anno del trofeo individuale da modificare
     * @param merito Il merito del trofeo individuale da modificare
     * @param nuovoCodF Il nuovo codice fiscale del giocatore
     * @param nuovoNomeTrofeo Il nuovo nome del trofeo individuale
     * @param nuovoAnno Il nuovo anno del trofeo individuale
     * @param nuovoMerito Il nuovo merito del trofeo individuale
     * @return true se la modifica ha avuto successo, altrimenti false
     */
    public boolean modificaTrofeoIndividuale(String codF, String nomeTrofeo, String anno, String merito, String nuovoCodF, String nuovoNomeTrofeo, String nuovoAnno, String nuovoMerito);
    /**
     * Interfaccia del metodo per modificare un trofeo di squadra nel database.
     * @param nomeTrofeo Il nome del trofeo di squadra da modificare
     * @param anno L'anno del trofeo di squadra da modificare
     * @param nuovoNomeSquadra Il nome della nuova squadra associata al trofeo di squadra
     * @param nuovaNazionalitaSquadra La nuova nazionalità della squadra associata al trofeo di squadra
     * @param nuovoNomeTrofeo Il nuovo nome del trofeo di squadra
     * @param nuovoAnno Il nuovo anno del trofeo di squadra
     * @param nuovoMerito Il nuovo merito del trofeo di squadra
     * @return true se la modifica ha avuto successo, altrimenti false
     */
    public boolean modificaTrofeoDiSquadra(String nomeTrofeo, String anno, String nuovoNomeSquadra, String nuovaNazionalitaSquadra, String nuovoNomeTrofeo, String nuovoAnno, String nuovoMerito);
    /**
     * Interfaccia del metodo per inserire un nuovo trofeo di squadra nel database.
     * @param nomeSquadra Il nome della squadra associata al trofeo di squadra
     * @param nazionalita La nazionalità della squadra associata al trofeo di squadra
     * @param nomeTrofeo Il nome del nuovo trofeo di squadra da inserire
     * @param anno L'anno del nuovo trofeo di squadra da inserire
     * @param merito Il merito del nuovo trofeo di squadra da inserire
     * @return true se l'inserimento ha avuto successo, altrimenti false
     */
    public boolean inserisciTrofeoDiSquadra(String nomeSquadra, String nazionalita, String nomeTrofeo, String anno, String merito);
}