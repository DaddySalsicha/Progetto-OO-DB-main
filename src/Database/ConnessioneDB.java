package Database;

import GUI.Login;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnessioneDB {

    private static ConnessioneDB instance;
    public Connection connection = null;
    private String url = "jdbc:postgresql://localhost:5432/postgres";
    private String driver = "org.postgresql.Driver";

    /**
     * Metodo per creare la connessione al database
     * @param login Login con cui collegarsi
     * @param password Password con cui collegarsi
     * @throws SQLException Eccezione di errore per SQL
     * @throws LoginFailedException Eccezione se il login non va a buon fine
     */

    private ConnessioneDB(String login, String password) throws SQLException, LoginFailedException {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, login, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
            ex.printStackTrace();
        } catch (PSQLException psqlException) {
            throw new LoginFailedException("Errore durante la connessione al database");
        }
    }

    /**
     * Metodo per ottenere la connessione al database
     * @param login Login con cui collegarsi
     * @param password Password con cui collegarsi
     * @return true se il login ha successo, false altrimenti
     * @throws SQLException Eccezione di errore per SQL
     * @throws LoginFailedException Eccezione se il login non va a buon fine
     */
    public static ConnessioneDB getInstance(String login, String password) throws SQLException, LoginFailedException {
        try{
            System.out.println("In connessionedb: "+login + password);
            if (instance == null || instance.connection.isClosed()) {
                instance = new ConnessioneDB(login, password);
            }
            return instance;
        } catch(LoginFailedException loginFailedException){
            throw new LoginFailedException("Errore durante la connessione al database");
        }

    }
    public Connection getConnection() {
        return connection;
    }
}

