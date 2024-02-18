package PostgresDAO;

import DAO.UtenteDAO;
import Database.*;

import java.sql.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class UtenteImplementazioneDAO implements UtenteDAO {

    private String login;
    private String password;
    private Connection connection;


    public UtenteImplementazioneDAO(String login, String password) {
        this.login = login;
        this.password = password;
    }
    /**
     * Crea una connessione e fa il login con i dati forniti dall'utente
     * @return truse se ha successo, false altrimenti
     */
    @Override
    public boolean login() {
        try {
            connection = ConnessioneDB.getInstance(login, password).connection;
            return true;
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Recupera i dati dei giocatori dal database e li memorizza nelle liste
     * @param listaNomi Lista dei nomi dei giocatori
     * @param listaCognomi Lista dei cognomi dei giocatori
     * @param listaCodFisc Lista dei codici fiscali dei giocatori
     * @param listaPiedi Lista dei piedi dominanti dei giocatori
     * @param listaDateNascita Lista delle date di nascita dei giocatori
     * @param listaDateRitiro Lista delle date di ritiro dei giocatori
     * @param listaListaCaratteristiche Lista delle liste di caratteristiche dei giocatori
     */
    public void fetchGiocatoriFromDB(ArrayList<String> listaNomi, ArrayList<String> listaCognomi, ArrayList<String> listaCodFisc,
                                     ArrayList<String> listaPiedi, ArrayList<LocalDate> listaDateNascita, ArrayList<LocalDate> listaDateRitiro,
                                     ArrayList<ArrayList<String>> listaListaCaratteristiche) {
        try {
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement getGiocatoriFromDB = connection.prepareStatement("SELECT * FROM progetto.giocatore");
            ResultSet rs = getGiocatoriFromDB.executeQuery();
            while (rs.next()) {
                listaCodFisc.add(rs.getString(1));
                listaNomi.add(rs.getString(2));
                listaCognomi.add(rs.getString(3));
                listaDateNascita.add(rs.getObject(4, LocalDate.class));
                listaPiedi.add(rs.getString(5));
                listaDateRitiro.add(rs.getObject(6, LocalDate.class));

                ArrayList<String> listaCaratteristiche = new ArrayList<>();

                try {
                    PreparedStatement getCaratteristiche = connection.prepareStatement("SELECT * FROM progetto.getCaratteristiche(?)");
                    getCaratteristiche.setString(1, rs.getString(1));
                    ResultSet caratteristiche = getCaratteristiche.executeQuery();

                    while (caratteristiche.next()) {
                        listaCaratteristiche.add(caratteristiche.getString(1));
                    }
                    listaListaCaratteristiche.add(listaCaratteristiche);
                    caratteristiche.close();
                    getCaratteristiche.close();
                } catch(SQLException ex) {
                    ex.printStackTrace();
                }
            }
            rs.close();
            getGiocatoriFromDB.close();
            connection.close();
        } catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
        }
    }


    /**
     * Recupera le caratteristiche dal database e le memorizza nella lista
     * @param listaCaratteristiche Lista delle caratteristiche da popolare
     */
    public void fetchCaratteristicheFromDB(ArrayList<String> listaCaratteristiche) {
        try {
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement getCaratteristicheFromDB = connection.prepareStatement("SELECT * FROM progetto.CARATTERISTICA");
            ResultSet rs = getCaratteristicheFromDB.executeQuery();

            while(rs.next()) {
                listaCaratteristiche.add(rs.getString(1));
            }
            rs.close();
            getCaratteristicheFromDB.close();
            connection.close();
        } catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
        }
    }


    /**
     * Recupera le squadre dal database e le memorizza nelle liste
     * @param listaNomi Lista dei nomi delle squadre da popolare
     * @param listaNazionalita Lista delle nazionalità delle squadre da popolare
     * @param listaIdCampionati Lista degli ID dei campionati delle squadre da popolare
     */
    public void fetchSquadreFromDB(ArrayList<String> listaNomi, ArrayList<String> listaNazionalita, ArrayList<Integer> listaIdCampionati) {
        try {
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement getSquadreFromDB = connection.prepareStatement("SELECT * FROM progetto.SQUADRA");
            ResultSet rs = getSquadreFromDB.executeQuery();

            while (rs.next()) {
                listaNomi.add(rs.getString(1));
                listaNazionalita.add(rs.getString(2));
                listaIdCampionati.add(rs.getInt(3));
            }
            rs.close();
            getSquadreFromDB.close();
            connection.close();
        } catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Recupera i trofei vinti dalle squadre dal database
     * * @param listaNomi La lista da popolare con i nomi dei trofei.
     * @param listaAnni La lista da popolare con gli anni dei trofei.
     * @param listaMeriti La lista da popolare con i meriti dei trofei.
     * @param listaNomiSquadra La lista da popolare con i nomi delle squadre.
     * @param listaNazionalitaSquadra La lista da popolare con le nazionalità delle squadre.
     * @return True se l'operazione ha successo, false altrimenti.
     */
    public boolean fetchTrofeiDiSquadraFromDB(ArrayList<String> listaNomi, ArrayList<String> listaAnni, ArrayList<String> listaMeriti,
                                              ArrayList<String> listaNomiSquadra, ArrayList<String> listaNazionalitaSquadra) {
        try {
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement getTrofeiDiSquadraFromDB = connection.prepareStatement("SELECT * FROM progetto.TROFEODISQUADRA");
            ResultSet rs = getTrofeiDiSquadraFromDB.executeQuery();

            while (rs.next()) {
                listaNomi.add(rs.getString(1));
                listaAnni.add(rs.getString(2));
                listaMeriti.add(rs.getString(3));
                listaNomiSquadra.add(rs.getString(4));
                listaNazionalitaSquadra.add(rs.getString(5));
            }
            rs.close();
            getTrofeiDiSquadraFromDB.close();
            connection.close();
            return true;
        } catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Recupera i trofei individuali dal database
     * @param listaNomi La lista da popolare con i nomi dei trofei individuali.
     * @param listaAnni La lista da popolare con gli anni dei trofei individuali.
     * @param listaMeriti La lista da popolare con i meriti dei trofei individuali.
     * @param listaCodFisc La lista da popolare con i codici fiscali dei giocatori associati ai trofei.
     * @return True se l'operazione ha successo, false altrimenti.
     */
    public boolean fetchTrofeiIndividiualiFromDB(ArrayList<String> listaNomi, ArrayList<String> listaAnni, ArrayList<String> listaMeriti, ArrayList<String> listaCodFisc) {
        try {
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement getTrofeiIndividualiFromDB = connection.prepareStatement("SELECT * FROM progetto.TROFEOINDIVIDUALE");
            ResultSet rs = getTrofeiIndividualiFromDB.executeQuery();

            while (rs.next()) {
                listaNomi.add(rs.getString(1));
                listaAnni.add(rs.getString(2));
                listaMeriti.add(rs.getString(3));
                listaCodFisc.add(rs.getString(4));
            }
            rs.close();
            getTrofeiIndividualiFromDB.close();
            connection.close();
            return true;
        } catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
    }



    /**
     * Recupera i campionati dal database
     * @param listaId La lista da popolare con gli identificatori dei campionati.
     * @param listaNomi La lista da popolare con i nomi dei campionati.
     * @param listaAnni La lista da popolare con gli anni dei campionati.
     */
    public void fetchCampionatiFromDB(ArrayList<Integer> listaId, ArrayList<String> listaNomi, ArrayList<String> listaAnni) {
        try {
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement getCampionatiFromDB = connection.prepareStatement("SELECT * FROM progetto.CAMPIONATO");
            ResultSet rs = getCampionatiFromDB.executeQuery();

            while (rs.next()) {
                listaId.add(rs.getInt(1));
                listaNomi.add(rs.getString(2));
                listaAnni.add(rs.getString(3));
            }
            rs.close();
            getCampionatiFromDB.close();
            connection.close();
        } catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
        }
    }


    /**
     * Recupera le militanze dal database
     * @param listaCodFisc La lista da popolare con i codici fiscali dei giocatori.
     * @param listaNomiSquadra La lista da popolare con i nomi delle squadre.
     * @param listaNazionalitaSquadra La lista da popolare con le nazionalità delle squadre.
     * @param listaDateInizio La lista da popolare con le date di inizio delle militanze.
     * @param listaDateFine La lista da popolare con le date di fine delle militanze.
     * @param listaRuoli La lista da popolare con i ruoli dei giocatori.
     * @param listaPartitaGiocate La lista da popolare con il numero di partite giocate.
     * @param listaGolEffettuati La lista da popolare con il numero di gol effettuati.
     * @param listaGolSubiti La lista da popolare con il numero di gol subiti.
     * @param listaAmmonizioni La lista da popolare con il numero di ammonizioni.
     * @param listaEspulsioni La lista da popolare con il numero di espulsioni.
     */
    public void fetchMilitanzeFromDB(ArrayList<String> listaCodFisc, ArrayList<String> listaNomiSquadra, ArrayList<String> listaNazionalitaSquadra,
                                     ArrayList<LocalDate> listaDateInizio, ArrayList<LocalDate> listaDateFine, ArrayList<String> listaRuoli,
                                     ArrayList<Integer> listaPartitaGiocate, ArrayList<Integer> listaGolEffettuati, ArrayList<Integer> listaGolSubiti,
                                     ArrayList<Integer> listaAmmonizioni, ArrayList<Integer> listaEspulsioni) {

        try {
            connection = ConnessioneDB.getInstance(login, password).connection;
            PreparedStatement getMilitanzeFromDB = connection.prepareStatement("SELECT * FROM progetto.Milita");
            ResultSet rs = getMilitanzeFromDB.executeQuery();

            while (rs.next()) {
                listaCodFisc.add(rs.getString(1));
                listaNomiSquadra.add(rs.getString(2));
                listaNazionalitaSquadra.add(rs.getString(3));
                listaDateInizio.add(rs.getObject(4, LocalDate.class));
                listaDateFine.add(rs.getObject(5, LocalDate.class));
                listaRuoli.add(rs.getString(6));
                listaPartitaGiocate.add(rs.getInt(7));
                listaGolEffettuati.add(rs.getInt(8));
                listaGolSubiti.add(rs.getInt(9));
                listaAmmonizioni.add(rs.getInt(10));
                listaEspulsioni.add(rs.getInt(11));
            }
            rs.close();
            getMilitanzeFromDB.close();
            connection.close();
        } catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
        }
    }


    /**
     * Recupera le informazioni sulle carriere dei giocatori dal database
     * @param listaCodFisc La lista da popolare con i codici fiscali dei giocatori.
     * @param listaNomi La lista da popolare con i nomi dei giocatori.
     * @param listaCognomi La lista da popolare con i cognomi dei giocatori.
     * @param listaDateNascita La lista da popolare con le date di nascita dei giocatori.
     * @param listaEta La lista da popolare con le età dei giocatori.
     * @param listaPiedi La lista da popolare con il piede dominante dei giocatori.
     * @param listaNomiSquadra La lista da popolare con i nomi delle squadre in cui i giocatori hanno militato.
     * @param listaRuoli La lista da popolare con i ruoli giocati dai giocatori.
     * @param listaCaratteristiche La lista da popolare con le caratteristiche dei giocatori.
     * @param listaPartiteGiocate La lista da popolare con il numero di partite giocate dai giocatori.
     * @param listaGolEffettuati La lista da popolare con il numero di gol effettuati dai giocatori.
     * @param listaGolSubiti La lista da popolare con il numero di gol subiti dai giocatori.
     * @param listaAmmonizioni La lista da popolare con il numero di ammonizioni ricevute dai giocatori.
     * @param listaEspulsioni La lista da popolare con il numero di espulsioni ricevute dai giocatori.
     */
    public void getCarriereFromDB(ArrayList<String> listaCodFisc, ArrayList<String> listaNomi, ArrayList<String> listaCognomi,
                                  ArrayList<LocalDate> listaDateNascita, ArrayList<Integer> listaEta, ArrayList<String> listaPiedi,
                                  ArrayList<String> listaNomiSquadra, ArrayList<String> listaRuoli, ArrayList<String> listaCaratteristiche,
                                  ArrayList<Integer> listaPartiteGiocate, ArrayList<Integer> listaGolEffettuati, ArrayList<Integer> listaGolSubiti,
                                  ArrayList<Integer> listaAmmonizioni, ArrayList<Integer> listaEspulsioni) {
        try {
            connection = ConnessioneDB.getInstance(login, password).connection;
            CallableStatement callableStatement = connection.prepareCall("{call progetto.carrieragiocatoriall()}");
            metodoEstratto(listaCodFisc, listaNomi, listaCognomi, listaDateNascita, listaEta, listaPiedi, listaNomiSquadra, listaRuoli,
                    listaCaratteristiche, listaPartiteGiocate, listaGolEffettuati, listaGolSubiti, listaAmmonizioni, listaEspulsioni, callableStatement);
        } catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo privato estratto da codice ripetuto, riempie il callable statement con gli argomenti
     * @param listaCodFisc  lista da popolare con i codici fiscali dei giocatori.
     * @param listaNomi La lista da popolare con i nomi dei giocatori.
     * @param listaCognomi La lista da popolare con i cognomi dei giocatori.
     * @param listaDateNascita La lista da popolare con le date di nascita dei giocatori.
     * @param listaEta La lista da popolare con le età dei giocatori.
     * @param listaPiedi La lista da popolare con il piede dominante dei giocatori.
     * @param listaNomiSquadra La lista da popolare con i nomi delle squadre in cui i giocatori hanno militato.
     * @param listaRuoli La lista da popolare con i ruoli giocati dai giocatori.
     * @param listaCaratteristiche La lista da popolare con le caratteristiche dei giocatori.
     * @param listaPartiteGiocate La lista da popolare con il numero di partite giocate dai giocatori.
     * @param listaGolEffettuati La lista da popolare con il numero di gol effettuati dai giocatori.
     * @param listaGolSubiti La lista da popolare con il numero di gol subiti dai giocatori.
     * @param listaAmmonizioni La lista da popolare con il numero di ammonizioni ricevute dai giocatori.
     * @param listaEspulsioni La lista da popolare con il numero di espulsioni ricevute dai giocatori.
     * @param callableStatement La chiamata di stored procedure utilizzata per recuperare i dati dal database.
     * @throws SQLException Se si verifica un errore durante l'interazione con il database.
     */
    private void metodoEstratto(ArrayList<String> listaCodFisc, ArrayList<String> listaNomi, ArrayList<String> listaCognomi,
                                ArrayList<LocalDate> listaDateNascita, ArrayList<Integer> listaEta, ArrayList<String> listaPiedi,
                                ArrayList<String> listaNomiSquadra, ArrayList<String> listaRuoli, ArrayList<String> listaCaratteristiche,
                                ArrayList<Integer> listaPartiteGiocate, ArrayList<Integer> listaGolEffettuati, ArrayList<Integer> listaGolSubiti,
                                ArrayList<Integer> listaAmmonizioni, ArrayList<Integer> listaEspulsioni, CallableStatement callableStatement) throws SQLException {
        ResultSet rs = callableStatement.executeQuery();
        while(rs.next()) {
            listaCodFisc.add(rs.getString(1));
            listaNomi.add(rs.getString(2));
            listaCognomi.add(rs.getString(3));
            listaDateNascita.add(rs.getObject(4, LocalDate.class));
            listaEta.add(rs.getInt(5));
            listaPiedi.add(rs.getString(6));
            listaNomiSquadra.add(rs.getString(7));
            listaRuoli.add(rs.getString(8));
            listaCaratteristiche.add(rs.getString(9));
            listaPartiteGiocate.add(rs.getInt(10));
            listaGolEffettuati.add(rs.getInt(11));
            listaGolSubiti.add(rs.getInt(12));
            listaAmmonizioni.add(rs.getInt(13));
            listaEspulsioni.add(rs.getInt(14));
        }
        rs.close();
        callableStatement.close();
        connection.close();
    }


    /**
     * Esegue una ricerca nel database per giocatori che corrispondono ai criteri specificati
     * @param listaCodFisc La lista da popolare con i codici fiscali dei giocatori trovati.
     * @param listaNomi La lista da popolare con i nomi dei giocatori trovati.
     * @param listaCognomi La lista da popolare con i cognomi dei giocatori trovati.
     * @param listaDateNascita La lista da popolare con le date di nascita dei giocatori trovati.
     * @param listaEta La lista da popolare con le età dei giocatori trovati.
     * @param listaPiedi La lista da popolare con il piede dominante dei giocatori trovati.
     * @param listaNomiSquadra La lista da popolare con i nomi delle squadre in cui i giocatori trovati hanno militato.
     * @param listaRuoli La lista da popolare con i ruoli giocati dai giocatori trovati.
     * @param listaCaratteristiche La lista da popolare con le caratteristiche dei giocatori trovati.
     * @param listaPartiteGiocate La lista da popolare con il numero di partite giocate dai giocatori trovati.
     * @param listaGolEffettuati La lista da popolare con il numero di gol effettuati dai giocatori trovati.
     * @param listaGolSubiti La lista da popolare con il numero di gol subiti dai giocatori trovati.
     * @param listaAmmonizioni La lista da popolare con il numero di ammonizioni ricevute dai giocatori trovati.
     * @param listaEspulsioni La lista da popolare con il numero di espulsioni ricevute dai giocatori trovati.
     * @param nome Il nome del giocatore da cercare.
     * @param etaRicerca L'età del giocatore da cercare.
     * @param ruolo Il ruolo del giocatore da cercare.
     * @param squadra Il nome della squadra in cui il giocatore deve aver militato.
     * @param piede Il piede dominante del giocatore da cercare.
     * @param ordineGoalSegnati L'ordine dei risultati basato sul numero di gol segnati
     * @param ordineGoalSubiti L'ordine dei risultati basato sul numero di gol subiti
     * @param ordineEta L'ordine dei risultati basato sull'età
     * @param segnoGoalSegnati Il segno della ricerca basata sul numero di gol segnati
     * @param segnoGoalSubiti Il segno della ricerca basata sul numero di gol subiti
     * @param segnoEta Il segno della ricerca basata sull'età
     */
    public void fetchRicerca(ArrayList<String> listaCodFisc, ArrayList<String> listaNomi, ArrayList<String> listaCognomi,
                             ArrayList<LocalDate> listaDateNascita, ArrayList<Integer> listaEta, ArrayList<String> listaPiedi,
                             ArrayList<String> listaNomiSquadra, ArrayList<String> listaRuoli, ArrayList<String> listaCaratteristiche,
                             ArrayList<Integer> listaPartiteGiocate, ArrayList<Integer> listaGolEffettuati, ArrayList<Integer> listaGolSubiti,
                             ArrayList<Integer> listaAmmonizioni, ArrayList<Integer> listaEspulsioni, String nome, int etaRicerca,
                             String ruolo, String squadra, String piede, String ordineGoalSegnati, String ordineGoalSubiti,
                             String ordineEta, String segnoGoalSegnati, String segnoGoalSubiti, String segnoEta, int goalSubitiRicerca,
                             int goalSegnatiRicerca) {

        try {
            connection = ConnessioneDB.getInstance(login, password).connection;
            CallableStatement callableStatement = connection.prepareCall("{call progetto.ricerca(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            callableStatement.setString(1, nome);
            callableStatement.setString(2, ruolo);
            callableStatement.setString(3, piede);
            callableStatement.setInt(4, goalSegnatiRicerca);
            callableStatement.setString(5, segnoGoalSegnati);
            callableStatement.setString(6, ordineGoalSegnati);
            callableStatement.setInt(7, goalSubitiRicerca);
            callableStatement.setString(8, segnoGoalSubiti);
            callableStatement.setString(9, ordineGoalSubiti);
            callableStatement.setInt(10, etaRicerca);
            callableStatement.setString(11, segnoEta);
            callableStatement.setString(12, ordineEta);
            callableStatement.setString(13, squadra);
            metodoEstratto(listaCodFisc, listaNomi, listaCognomi, listaDateNascita, listaEta, listaPiedi, listaNomiSquadra, listaRuoli, listaCaratteristiche, listaPartiteGiocate, listaGolEffettuati, listaGolSubiti, listaAmmonizioni, listaEspulsioni, callableStatement);
        }catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
        }
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}