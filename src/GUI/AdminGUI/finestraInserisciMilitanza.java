package GUI.AdminGUI;

import Controller.ControllerAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class finestraInserisciMilitanza {
    private JPanel panel;
    private JButton indietroButton;
    private JButton invioButton;
    private JComboBox comboBoxGiocatori;
    private JComboBox comboBoxSquadra;
    private JComboBox comboBoxRuoli;
    private JComboBox boxGiornoDataInizio;
    private JComboBox boxAnnoDataInizio;
    private JComboBox boxMeseDataInizio;
    private JTextField textFieldPartite;
    private JTextField textFieldGoalSegnati;
    private JTextField textFieldGoalSubiti;
    private JTextField textFieldAmmonizioni;
    private JTextField textFieldEspulsioni;
    private JComboBox boxGiornoDataFine;
    private JComboBox boxMeseDataFine;
    private JComboBox boxAnnoDataFine;
    public JFrame frame;
    private final JFrame frameChiamante;
    public ControllerAdmin controller;

    public finestraInserisciMilitanza(JFrame frameHome, JFrame frameChiamante, ControllerAdmin controller){
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameChiamante;
        this.controller = controller;


        ArrayList<String> listaCodFisc = new ArrayList<>();
        ArrayList<String> listaNomiSquadra = new ArrayList<>();
        ArrayList<String> listaNazionalitaSquadra = new ArrayList<>();


        controller.visualizzaCodFiscGiocatori(listaCodFisc);
        controller.visualizzaSquadre(listaNomiSquadra, listaNazionalitaSquadra);

        for(int i = 0; i < listaNomiSquadra.size(); i++){
            comboBoxSquadra.addItem(listaNomiSquadra.get(i) + " / " + listaNazionalitaSquadra.get(i));
        }
        for(String s : listaCodFisc){
            comboBoxGiocatori.addItem(s);
        }
        comboBoxRuoli.addItem("Attaccante");
        comboBoxRuoli.addItem("Difensore");
        comboBoxRuoli.addItem("Centrocampista");
        comboBoxRuoli.addItem("Portiere");

        for(int i = 1; i < 32; i++){
            boxGiornoDataInizio.addItem(i);
            boxGiornoDataFine.addItem(i);
        }
        for(int i = 1900; i < LocalDate.now().getYear(); i++){
            boxAnnoDataInizio.addItem(i);
            boxAnnoDataFine.addItem(i);
        }
        for(int i = 1; i < 13; i++){
            boxMeseDataInizio.addItem(i);
            boxMeseDataFine.addItem(i);
        }
        textFieldPartite.setText("0");
        textFieldGoalSegnati.setText("0");
        textFieldGoalSubiti.setText("0");
        textFieldAmmonizioni.setText("0");
        textFieldEspulsioni.setText("0");

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
        invioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codFisc = comboBoxGiocatori.getSelectedItem().toString();
                String[] squadraSelezionata = comboBoxSquadra.getSelectedItem().toString().split("/");
                String nomeSquadraSelezionata = squadraSelezionata[0].trim();
                String nazionalitaSquadraSelezionata = squadraSelezionata[1].trim();
                String ruolo = comboBoxRuoli.getSelectedItem().toString();
                LocalDate dataInizio = LocalDate.of(Integer.parseInt(boxAnnoDataInizio.getSelectedItem().toString()), Integer.parseInt(boxMeseDataInizio.getSelectedItem().toString()), Integer.parseInt(boxGiornoDataInizio.getSelectedItem().toString()));
                LocalDate dataFine = LocalDate.of(Integer.parseInt(boxAnnoDataFine.getSelectedItem().toString()), Integer.parseInt(boxMeseDataFine.getSelectedItem().toString()), Integer.parseInt(boxGiornoDataFine.getSelectedItem().toString()));
                try{
                    int partite = Integer.parseInt(textFieldPartite.getText());
                    int goalSegnati = Integer.parseInt(textFieldGoalSegnati.getText());
                    int goalSubiti = Integer.parseInt(textFieldGoalSubiti.getText());
                    int ammonizioni = Integer.parseInt(textFieldAmmonizioni.getText());
                    int espulsioni = Integer.parseInt(textFieldEspulsioni.getText());
                    if(controller.inserisciMilitanza(codFisc, nomeSquadraSelezionata, nazionalitaSquadraSelezionata, dataInizio, dataFine, ruolo, partite, goalSegnati, goalSubiti, ammonizioni, espulsioni)){
                        JOptionPane.showMessageDialog(null, "Militanza aggiunta correttamente!");
                        VisualizzaMilitanze visualizzaMilitanze= new VisualizzaMilitanze(frameHome, controller);
                        visualizzaMilitanze.frame.setVisible(true);
                        frame.setVisible(false);
                        frame.dispose();
                    }else{
                        JOptionPane.showMessageDialog(null, "Errore: non è stato possibile inserire la militanza!");
                    }
                } catch (NumberFormatException numberFormatException){
                    JOptionPane.showMessageDialog(null, "Errore: assicurati che i campi contengano solo valori numerici.");
                }


            }
        });
        comboBoxRuoli.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(!Objects.equals(comboBoxRuoli.getSelectedItem().toString(), "Portiere")){
                    textFieldGoalSubiti.setText("0");
                    textFieldGoalSubiti.setEditable(false);
                }else{
                    textFieldGoalSubiti.setEditable(true);
                }
            }
        });
    }
}
