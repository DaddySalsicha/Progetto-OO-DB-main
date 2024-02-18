package DAO;

import Database.LoginFailedException;

import java.time.LocalDate;
import java.util.ArrayList;

public interface UtenteDAO {
    public boolean login();
    public void getCarriereFromDB(ArrayList<String> listaCodFisc, ArrayList<String> listaNomi, ArrayList<String> listaCognomi,
    ArrayList<LocalDate> listaDateNascita, ArrayList<Integer> listaEta, ArrayList<String> listaPiedi, ArrayList<String> listaNomiSquadra, ArrayList<String> listaRuoli,
    ArrayList<String> listaCaratteristiche, ArrayList<Integer> listaPartiteGiocate, ArrayList<Integer> listaGolEffettuati, ArrayList<Integer> listaGolSubiti,
    ArrayList<Integer> listaAmmonizioni, ArrayList<Integer> listaEspulsioni);

}
