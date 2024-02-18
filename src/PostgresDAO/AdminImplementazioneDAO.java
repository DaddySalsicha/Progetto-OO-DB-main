package PostgresDAO;

import DAO.AdminDAO;
import Database.ConnessioneDB;
import Database.LoginFailedException;

import java.sql.*;
import java.time.LocalDate;

public class AdminImplementazioneDAO extends UtenteImplementazioneDAO implements AdminDAO{
    private String login;
    private String password;
    private Connection connection;

    public AdminImplementazioneDAO(String login, String password) {
        super(login, password);
        this.login = login;
        this.password = password;
    }

    @Override
    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Metodo per controllare se l'utente è un admin
     * @return truse se è un admin, false altrimenti
     */

    public boolean checkAdmin(){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            CallableStatement callableStatement = connection.prepareCall("SELECT * FROM progetto.getRuoloUtente()");
            ResultSet rs = callableStatement.executeQuery();
            rs.next();
            if(rs.getString(1).equals("amministratore")){
                callableStatement.close();
                rs.close();
                connection.close();
                return false;
            }else{
                callableStatement.close();
                rs.close();
                connection.close();
                return true;
            }
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Inserisce un nuovo giocatore nel database
     * @param nome Nome del giocatore
     * @param cognome Cognome del giocatore
     * @param codFisc Codice fiscale del giocatore
     * @param piede Piede dominante del giocatore
     * @param dataDiNascita Data di nascita del giocatore
     * @return true se l'inserimento ha avuto successo, altrimenti false
     */
    public boolean inserisciGiocatore(String nome, String cognome, String codFisc, String piede, LocalDate dataDiNascita) {
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            CallableStatement callableStatement = connection.prepareCall("call progetto.inserisciGiocatore(?, ?, ?, ?, ?)");
            callableStatement.setString(1, codFisc);
            callableStatement.setString(2, nome);
            callableStatement.setString(3, cognome);
            callableStatement.setObject(4, dataDiNascita);
            callableStatement.setString(5, piede);
            callableStatement.execute();
            callableStatement.close();
            connection.close();
            return true;
        } catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Inserisce un trofeo individuale nel database
     *
     * @param codF Codice fiscale del giocatore vincitore del trofeo
     * @param nome Nome del trofeo individuale
     * @param anno Anno del trofeo individuale
     * @param merito Merito associato al trofeo individuale
     * @return true se l'inserimento nel database ha avuto successo, altrimenti false
     */
    public boolean inserisciTrofeoIndividuale(String codF, String nome, String anno, String merito){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO progetto.TROFEOINDIVIDUALE VALUES (?, ?, ?, ?)");
            insertStatement.setString(1, nome);
            insertStatement.setString(2, anno);
            insertStatement.setString(3, merito);
            insertStatement.setString(4, codF);
            insertStatement.executeUpdate();
            insertStatement.close();
            connection.close();
            return true;
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Inserisce una squadra nel database
     *
     * @param nome Nome della squadra da inserire
     * @param nazionalita Nazionalità della squadra da inserire
     * @param idCampionato ID del campionato a cui appartiene la squadra
     * @return true se l'inserimento nel database ha avuto successo, altrimenti false
     */
    public boolean inserisciSquadra(String nome, String nazionalita, int idCampionato){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO progetto.SQUADRA VALUES (?, ?, ?)");
            insertStatement.setString(1, nome);
            insertStatement.setString(2, nazionalita);
            insertStatement.setInt(3, idCampionato);
            insertStatement.executeUpdate();
            insertStatement.close();
            connection.close();
            return true;
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Aggiunge una nuova caratteristica nel database
     *
     * @param tipoCaratteristica La nuova caratteristica da aggiungere
     * @return true se l'aggiunta nel database ha avuto successo, altrimenti false
     */
    public boolean aggiungiCaratteristica(String tipoCaratteristica){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO progetto.CARATTERISTICA VALUES (?)");
            insertStatement.setString(1, tipoCaratteristica);
            insertStatement.executeUpdate();
            insertStatement.close();
            connection.close();
            return true;
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Aggiunge una nuova caratteristica al giocatore nel database 
     *
     * @param codFisc Il codice fiscale del giocatore a cui aggiungere la caratteristica
     * @param caratteristica La nuova caratteristica da aggiungere al giocatore
     * @return true se l'aggiunta nel database ha avuto successo, altrimenti false
     */
    public boolean aggiungiCaratteristicaGiocatore(String codFisc, String caratteristica){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO progetto.POSSIEDE VALUES (?, ?)");
            insertStatement.setString(1, codFisc);
            insertStatement.setString(2, caratteristica);
            insertStatement.executeUpdate();
            insertStatement.close();
            connection.close();
            return true;
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Inserisce un nuovo campionato nel database.
     * @param idCampionato L'ID del campionato da inserire
     * @param nome Il nome del campionato da inserire
     * @param anno L'anno del campionato da inserire
     * @return true se l'inserimento nel database ha avuto successo, altrimenti false
     */
    public boolean insertCampionato(int idCampionato, String nome, String anno){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO progetto.CAMPIONATO VALUES (?, ?, ?)");
            insertStatement.setInt(1, idCampionato);
            insertStatement.setString(2, nome);
            insertStatement.setString(3, anno);
            insertStatement.executeUpdate();
            insertStatement.close();
            connection.close();
            return true;
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Modifica il nome di un campionato nel database.
     * @param idCampionato L'ID del campionato da modificare
     * @param nuovoNome Il nuovo nome da assegnare al campionato
     * @return true se la modifica nel database ha avuto successo, altrimenti false
     */
    public boolean modificaNomeCampionato(int idCampionato, String nuovoNome){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.CAMPIONATO SET NOME = ? WHERE IDCAMPIONATO = ?");
            updateStatement.setString(1, nuovoNome);
            updateStatement.setInt(2, idCampionato);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
            return true;
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Modifica l'anno di un campionato nel database.
     * @param idCampionato L'ID del campionato da modificare
     * @param nuovoAnno Il nuovo anno da assegnare al campionato
     * @return true se la modifica nel database ha avuto successo, altrimenti false
     */
    public boolean modificaAnnoCampionato(int idCampionato, String nuovoAnno){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE progetto.CAMPIONATO SET ANNO = ? WHERE IDCAMPIONATO = ?");
            updateStatement.setString(1, nuovoAnno);
            updateStatement.setInt(2, idCampionato);
            updateStatement.executeUpdate();
            updateStatement.close();
            connection.close();
            return true;
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Inserisce una militanza di un giocatore nel database.
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
    public boolean inserisciMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, LocalDate dataFine,
                                      String ruolo, int partiteGiocate, int golEffettuati, int golSubiti, int ammonizioni, int espulsioni) {

        try {
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO progetto.MILITA VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, codFisc);
            preparedStatement.setString(2, nomeSquadra);
            preparedStatement.setString(3, nazionalitaSquadra);
            preparedStatement.setObject(4, dataInizio);
            preparedStatement.setObject(5, dataFine);
            preparedStatement.setString(6, ruolo);
            preparedStatement.setInt(7, partiteGiocate);
            preparedStatement.setInt(8, golEffettuati);
            preparedStatement.setInt(9, golSubiti);
            preparedStatement.setInt(10, ammonizioni);
            preparedStatement.setInt(11, espulsioni);
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Rimuove un giocatore dal database.
     * @param codFisc Il codice fiscale del giocatore da rimuovere
     * @return true se la rimozione dal database ha avuto successo, altrimenti false
     */
    public boolean rimuoviGiocatore(String codFisc){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement removeStatement = connection.prepareStatement("DELETE FROM progetto.GIOCATORE WHERE CODFISC LIKE ?");
            removeStatement.setString(1, codFisc);
            removeStatement.executeUpdate();
            removeStatement.close();
            connection.close();
            return true;
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Rimuove una caratteristica associata a un giocatore dal database.
     * @param codFisc Il codice fiscale del giocatore
     * @param caratteristica La caratteristica da rimuovere
     * @return true se la rimozione dal database ha avuto successo, altrimenti false
     */
    public boolean rimuoviCaratteristicaGiocatore(String codFisc, String caratteristica){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement removeStatement = connection.prepareStatement("DELETE FROM progetto.POSSIEDE WHERE CODFISC LIKE ? AND CARATTERISTICA LIKE ?");
            removeStatement.setString(1, codFisc);
            removeStatement.setString(2, caratteristica);
            removeStatement.executeUpdate();
            removeStatement.close();
            connection.close();
            return true;
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Rimuove una caratteristica dal database.
     * @param tipoCaratteristica La caratteristica da rimuovere
     * @return true se la rimozione dal database ha avuto successo, altrimenti false
     */
    public boolean rimuoviCaratteristica(String tipoCaratteristica){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement removeStatement = connection.prepareStatement("DELETE FROM progetto.CARATTERISTICA WHERE TIPOCARATTERISTICA LIKE ?");
            removeStatement.setString(1, tipoCaratteristica);
            removeStatement.executeUpdate();
            removeStatement.close();
            connection.close();
            return true;
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Modifica una caratteristica nel database.
     * @param caratteristica La caratteristica da modificare
     * @param nuovaCaratteristica La nuova caratteristica
     * @return true se la modifica nel database ha avuto successo, altrimenti false
     */
    public boolean modificaCaratteristica(String caratteristica, String nuovaCaratteristica){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE progetto.Caratteristica SET tipocaratteristica = ? WHERE tipocaratteristica LIKE ?");
            preparedStatement.setString(1, nuovaCaratteristica);
            preparedStatement.setString(2, caratteristica);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Rimuove una militanza dal database.
     * @param codFisc Il codice fiscale del giocatore associato alla militanza
     * @param nomeSquadra Il nome della squadra associata alla militanza
     * @param nazionalitaSquadra La nazionalità della squadra associata alla militanza
     * @param dataInizio La data di inizio della militanza
     * @return true se la rimozione dal database ha avuto successo, altrimenti false
     */
    public boolean rimuoviMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement removeStatement = connection.prepareStatement("DELETE FROM progetto.MILITA WHERE CODFISC LIKE ? AND NOMESQUADRA LIKE ? AND NAZIONALITASQUADRA LIKE ? AND DATAINIZIO = ?");
            removeStatement.setString(1, codFisc);
            removeStatement.setString(2, nomeSquadra);
            removeStatement.setString(3, nazionalitaSquadra);
            removeStatement.setObject(4, dataInizio);
            removeStatement.executeUpdate();
            removeStatement.close();
            connection.close();
            return true;
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Modifica una militanza nel database.
     *
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
    public boolean modificaMilitanza(String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio, String nuovoCodFisc, String nuovoNomeSquadra, String nuovaNazionalitaSquadra, LocalDate nuovaDataInizio, LocalDate dataFine, String ruolo, int partiteGiocate, int goalSegnati, int goalSubiti, int ammonizioni, int espulsioni){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE progetto.MILITA SET codfisc=?, nomesquadra=?, nazionalitasquadra=?, datainizio=?, datafine=?, ruolo=?, partitegiocate=?, goleffettuati=?, golsubiti=?, ammonizioni=?, espulsioni=? WHERE codfisc LIKE ? AND nomesquadra LIKE ? AND nazionalitasquadra LIKE ? AND datainizio = ?");
            preparedStatement.setString(1, nuovoCodFisc);
            preparedStatement.setString(2, nuovoNomeSquadra);
            preparedStatement.setString(3, nuovaNazionalitaSquadra);
            preparedStatement.setObject(4, nuovaDataInizio);
            preparedStatement.setObject(5, dataFine);
            preparedStatement.setString(6, ruolo);
            preparedStatement.setInt(7, partiteGiocate);
            preparedStatement.setInt(8, goalSegnati);
            preparedStatement.setInt(9, goalSubiti);
            preparedStatement.setInt(10, ammonizioni);
            preparedStatement.setInt(11, espulsioni);
            preparedStatement.setString(12, codFisc);
            preparedStatement.setString(13, nomeSquadra);
            preparedStatement.setString(14, nazionalitaSquadra);
            preparedStatement.setObject(15, dataInizio);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Rimuove una squadra dal database.
     * @param nomeSquadra Il nome della squadra da rimuovere
     * @param nazionalitaSquadra La nazionalità della squadra da rimuovere
     * @return true se la rimozione dal database ha avuto successo, altrimenti false
     */
    public boolean rimuoviSquadra(String nomeSquadra, String nazionalitaSquadra){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement removeStatement = connection.prepareStatement("DELETE FROM progetto.SQUADRA WHERE NOME LIKE ? AND NAZIONALITA LIKE ?");
            removeStatement.setString(1, nomeSquadra);
            removeStatement.setObject(2, nazionalitaSquadra);
            removeStatement.executeUpdate();
            removeStatement.close();
            connection.close();
            return true;
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Rimuove un trofeo di squadra dal database.
     * @param nome Il nome del trofeo di squadra da rimuovere
     * @param anno L'anno del trofeo di squadra da rimuovere
     * @return true se la rimozione dal database ha avuto successo, altrimenti false
     */
    public boolean rimuoviTrofeoSquadra(String nome, String anno){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement removeStatement = connection.prepareStatement("DELETE FROM progetto.TROFEODISQUADRA WHERE NOME LIKE ? AND ANNO LIKE ?");
            removeStatement.setString(1, nome);
            removeStatement.setObject(2, anno);
            removeStatement.executeUpdate();
            removeStatement.close();
            connection.close();
            return true;
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Rimuove un trofeo individuale dal database.
     * @param nome Il nome del trofeo individuale da rimuovere
     * @param anno L'anno del trofeo individuale da rimuovere
     * @return true se la rimozione dal database ha avuto successo, altrimenti false
     */
    public boolean rimuoviTrofeoIndividuale(String nome, String anno){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement removeStatement = connection.prepareStatement("DELETE FROM progetto.TROFEOINDIVIDUALE WHERE NOME LIKE ? AND ANNO LIKE ?");
            removeStatement.setString(1, nome);
            removeStatement.setObject(2, anno);
            removeStatement.executeUpdate();
            removeStatement.close();
            connection.close();
            return true;
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Aggiorna i dati di un giocatore nel database, inclusa la data di ritiro.
     * @param vecchioCodFisc Il vecchio codice fiscale del giocatore da aggiornare
     * @param nuovoCodFisc Il nuovo codice fiscale del giocatore
     * @param nuovoNome Il nuovo nome del giocatore
     * @param nuovoCognome Il nuovo cognome del giocatore
     * @param nuovoPiede Il nuovo piede dominante del giocatore
     * @param nuovaDataNascita La nuova data di nascita del giocatore
     * @param nuovaDataRitiro La nuova data di ritiro del giocatore
     * @return true se l'aggiornamento ha avuto successo, altrimenti false
     */
    public boolean updateGiocatoreConDataRitiro(String vecchioCodFisc, String nuovoCodFisc, String nuovoNome, String nuovoCognome, String nuovoPiede, LocalDate nuovaDataNascita, LocalDate nuovaDataRitiro){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updategiocatore = connection.prepareStatement("UPDATE progetto.Giocatore SET codfisc=?, nome=?, cognome=?, piede=?, datanascita=?, dataritiro=? WHERE CodFisc LIKE ?");
            updategiocatore.setString(1, nuovoCodFisc);
            updategiocatore.setString(2, nuovoNome);
            updategiocatore.setString(3, nuovoCognome);
            updategiocatore.setString(4, nuovoPiede);
            updategiocatore.setDate(5, Date.valueOf(nuovaDataNascita));
            updategiocatore.setDate(6, Date.valueOf(nuovaDataRitiro));
            updategiocatore.setString(7, vecchioCodFisc);
            updategiocatore.executeUpdate();
            updategiocatore.close();
            connection.close();
            return true;
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Aggiorna i dati di un giocatore nel database, escludendo la data di ritiro.
     * @param vecchioCodFisc Il vecchio codice fiscale del giocatore da aggiornare
     * @param nuovoCodFisc Il nuovo codice fiscale del giocatore
     * @param nuovoNome Il nuovo nome del giocatore
     * @param nuovoCognome Il nuovo cognome del giocatore
     * @param nuovoPiede Il nuovo piede dominante del giocatore
     * @param nuovaDataNascita La nuova data di nascita del giocatore
     * @return true se l'aggiornamento ha avuto successo, altrimenti false
     */
    public boolean updateGiocatoreSenzaDataRitiro(String vecchioCodFisc, String nuovoCodFisc, String nuovoNome, String nuovoCognome, String nuovoPiede, LocalDate nuovaDataNascita){
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updategiocatore = connection.prepareStatement("UPDATE progetto.Giocatore SET codfisc=?, nome=?, cognome=?, piede=?, datanascita=? WHERE CodFisc LIKE ?");
            updategiocatore.setString(1, nuovoCodFisc);
            updategiocatore.setString(2, nuovoNome);
            updategiocatore.setString(3, nuovoCognome);
            updategiocatore.setString(4, nuovoPiede);
            updategiocatore.setDate(5, Date.valueOf(nuovaDataNascita));
            updategiocatore.setString(6, vecchioCodFisc);
            updategiocatore.executeUpdate();
            updategiocatore.close();
            connection.close();
            return true;
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Aggiorna i dati di una squadra nel database.
     * @param vecchioNome Il nome attuale della squadra da aggiornare
     * @param vecchiaNazionalita La nazionalità attuale della squadra da aggiornare
     * @param nuovoNome Il nuovo nome della squadra
     * @param nuovaNazionalita La nuova nazionalità della squadra
     * @return true se l'aggiornamento ha avuto successo, altrimenti false
     */
    public boolean updateSquadra(String vecchioNome, String vecchiaNazionalita, String nuovoNome, String nuovaNazionalita) {
        try {
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updatesquadra = connection.prepareStatement("UPDATE progetto.Squadra SET nome=?, nazionalita=? WHERE Nome LIKE ? AND Nazionalita LIKE ?");
            updatesquadra.setString(1, nuovoNome);
            updatesquadra.setString(2, nuovaNazionalita);
            updatesquadra.setString(3, vecchioNome);
            updatesquadra.setString(4, vecchiaNazionalita);
            updatesquadra.executeUpdate();
            updatesquadra.close();
            connection.close();
            return true;
        } catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Rimuove un campionato dal database insieme a tutte le squadre ad esso associate.
     * @param id L'identificativo del campionato da rimuovere
     * @return true se la rimozione ha avuto successo, altrimenti false
     */
    public boolean rimuoviCampionato(int id) {
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement removeStatement = connection.prepareStatement("DELETE FROM progetto.Squadra WHERE idcampionato = ?");
            removeStatement.setInt(1, id);
            removeStatement.executeUpdate();
            removeStatement.close();
            connection.close();
            return true;
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Modifica un trofeo individuale nel database.
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
    public boolean modificaTrofeoIndividuale(String codF, String nomeTrofeo, String anno, String merito, String nuovoCodF, String nuovoNomeTrofeo, String nuovoAnno, String nuovoMerito) {
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updatesquadra = connection.prepareStatement("UPDATE progetto.Trofeoindividuale SET codf=?, nome=?, anno=?, merito=? WHERE Nome LIKE ? AND Anno LIKE ?");
            updatesquadra.setString(1, nuovoCodF);
            updatesquadra.setString(2, nuovoNomeTrofeo);
            updatesquadra.setString(3, nuovoAnno);
            updatesquadra.setString(4, nuovoMerito);
            updatesquadra.setString(5, nomeTrofeo);
            updatesquadra.setString(6, anno);
            updatesquadra.executeUpdate();
            updatesquadra.close();
            connection.close();
            return true;
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Modifica un trofeo di squadra nel database.
     * @param nomeTrofeo Il nome del trofeo di squadra da modificare
     * @param anno L'anno del trofeo di squadra da modificare
     * @param nuovoNomeSquadra Il nome della nuova squadra associata al trofeo di squadra
     * @param nuovaNazionalitaSquadra La nuova nazionalità della squadra associata al trofeo di squadra
     * @param nuovoNomeTrofeo Il nuovo nome del trofeo di squadra
     * @param nuovoAnno Il nuovo anno del trofeo di squadra
     * @param nuovoMerito Il nuovo merito del trofeo di squadra
     * @return true se la modifica ha avuto successo, altrimenti false
     */
    public boolean modificaTrofeoDiSquadra(String nomeTrofeo, String anno, String nuovoNomeSquadra, String nuovaNazionalitaSquadra, String nuovoNomeTrofeo, String nuovoAnno, String nuovoMerito) {
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateSquadra = connection.prepareStatement("UPDATE progetto.TrofeoDiSquadra SET nome=?, anno=?, merito=?, nomesquadra=?, nazionalitasquadra=? WHERE Nome LIKE ? AND Anno LIKE ?");
            updateSquadra.setString(1, nuovoNomeTrofeo);
            updateSquadra.setString(2, nuovoAnno);
            updateSquadra.setString(3, nuovoMerito);
            updateSquadra.setString(4, nuovoNomeSquadra);
            updateSquadra.setString(5, nuovaNazionalitaSquadra);
            updateSquadra.setString(6, nomeTrofeo);
            updateSquadra.setString(7, anno);
            updateSquadra.executeUpdate();
            updateSquadra.close();
            connection.close();
            return true;
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Inserisce un nuovo trofeo di squadra nel database.
     * @param nomeSquadra Il nome della squadra associata al trofeo di squadra
     * @param nazionalita La nazionalità della squadra associata al trofeo di squadra
     * @param nomeTrofeo Il nome del nuovo trofeo di squadra da inserire
     * @param anno L'anno del nuovo trofeo di squadra da inserire
     * @param merito Il merito del nuovo trofeo di squadra da inserire
     * @return true se l'inserimento ha avuto successo, altrimenti false
     */
    public boolean inserisciTrofeoDiSquadra(String nomeSquadra, String nazionalita, String nomeTrofeo, String anno, String merito) {
        try{
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement updateSquadra = connection.prepareStatement("INSERT INTO progetto.TrofeoDiSquadra VALUES(?, ?, ?, ?, ?)");
            updateSquadra.setString(1, nomeTrofeo);
            updateSquadra.setString(2, anno);
            updateSquadra.setString(3, merito);
            updateSquadra.setString(4, nomeSquadra);
            updateSquadra.setString(5, nazionalita);
            updateSquadra.executeUpdate();
            updateSquadra.close();
            connection.close();
            return true;
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
