package PostgresDAO;

import Database.ConnessioneDB;
import Database.LoginFailedException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class RegistrazioneImplementazioneDAO {
    private static Connection connection;

    /**
     * Registra un utente nel database
     * @param login Il login con cui registrarsi
     * @param password La password con cui regisgrarsi
     * @return true se la registrazione ha avuto successo, false altrimenti
     */
    public static boolean signUp(String login, String password){
        try {
            connection = ConnessioneDB.getInstance("ist_registrazione", "registrazione").connection;
            CallableStatement callableStatement = connection.prepareCall("call progetto.createuser(?, ?)");
            callableStatement.setString(1, login);
            callableStatement.setString(2, password);
            callableStatement.execute();
            connection.close();
        } catch (SQLException | LoginFailedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
