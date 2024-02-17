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

public class finestraModificaMilitanza {
    private JPanel panel;
    private JComboBox comboBoxGiocatori;
    private JComboBox comboBoxRuoli;
    private JComboBox comboBoxSquadra;
    private JComboBox boxGiornoDataInizio;
    private JComboBox boxAnnoDataInizio;
    private JComboBox boxMeseDataInizio;
    private JComboBox boxGiornoDataFine;
    private JComboBox boxAnnoDataFine;
    private JComboBox boxMeseDataFine;
    private JTextField textFieldEspulsioni;
    private JTextField textFieldPartite;
    private JTextField textFieldAmmonizioni;
    private JTextField textFieldGoalSegnati;
    private JTextField textFieldGoalSubiti;
    private JButton indietroButton;
    private JButton invioButton;
    public JFrame frame;
    private final JFrame frameChiamante;
    public ControllerAdmin controller;
    public finestraModificaMilitanza(JFrame frameHome, JFrame frameChiamante, ControllerAdmin controller, String codFisc, String nomeSquadra, String nazionalitaSquadra, LocalDate dataInizio) {
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
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

        comboBoxGiocatori.setSelectedItem(codFisc);
        comboBoxSquadra.setSelectedItem(nomeSquadra + " / " + nazionalitaSquadra);
        comboBoxRuoli.setSelectedItem(controller.getRuoloMilitanza(codFisc, nomeSquadra, nazionalitaSquadra, dataInizio));
        boxGiornoDataInizio.setSelectedItem(dataInizio.getDayOfMonth());
        boxMeseDataInizio.setSelectedItem(dataInizio.getMonthValue());
        boxAnnoDataInizio.setSelectedItem(dataInizio.getYear());
        boxGiornoDataFine.setSelectedItem(controller.getDataFineMilitanza(codFisc, nomeSquadra, nazionalitaSquadra, dataInizio).getDayOfMonth());
        boxMeseDataFine.setSelectedItem(controller.getDataFineMilitanza(codFisc, nomeSquadra, nazionalitaSquadra, dataInizio).getMonthValue());
        boxAnnoDataFine.setSelectedItem(controller.getDataFineMilitanza(codFisc, nomeSquadra, nazionalitaSquadra, dataInizio).getYear());
        textFieldPartite.setText(""+controller.getPartiteGiocateMilitanza(codFisc, nomeSquadra, nazionalitaSquadra, dataInizio));
        textFieldGoalSegnati.setText(""+controller.getGoalSegnatiMilitanza(codFisc, nomeSquadra, nazionalitaSquadra, dataInizio));
        textFieldGoalSubiti.setText(""+controller.getGoalSubitiMilitanza(codFisc, nomeSquadra, nazionalitaSquadra, dataInizio));
        textFieldAmmonizioni.setText(""+controller.getAmmonizioniMilitanza(codFisc, nomeSquadra, nazionalitaSquadra, dataInizio));
        textFieldEspulsioni.setText(""+controller.getEspulsioniMilitanza(codFisc, nomeSquadra, nazionalitaSquadra, dataInizio));

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
                String nuovoCodFisc = comboBoxGiocatori.getSelectedItem().toString();
                String[] squadraSelezionata = comboBoxSquadra.getSelectedItem().toString().split("/");
                String nuovoNomeSquadra = squadraSelezionata[0].trim();
                String nuovaNazionalitaSquadra = squadraSelezionata[1].trim();
                String nuovoRuolo = comboBoxRuoli.getSelectedItem().toString();
                LocalDate nuovaDataInizio = LocalDate.of(Integer.parseInt(boxAnnoDataInizio.getSelectedItem().toString()), Integer.parseInt(boxMeseDataInizio.getSelectedItem().toString()), Integer.parseInt(boxGiornoDataInizio.getSelectedItem().toString()));
                LocalDate nuovaDataFine = LocalDate.of(Integer.parseInt(boxAnnoDataFine.getSelectedItem().toString()), Integer.parseInt(boxMeseDataFine.getSelectedItem().toString()), Integer.parseInt(boxGiornoDataFine.getSelectedItem().toString()));
                try{
                    int partite = Integer.parseInt(textFieldPartite.getText());
                    int goalSegnati = Integer.parseInt(textFieldGoalSegnati.getText());
                    int goalSubiti = Integer.parseInt(textFieldGoalSubiti.getText());
                    int ammonizioni = Integer.parseInt(textFieldAmmonizioni.getText());
                    int espulsioni = Integer.parseInt(textFieldEspulsioni.getText());
                    if(controller.modificaMilitanza(codFisc, nomeSquadra, nazionalitaSquadra, dataInizio, nuovoCodFisc, nuovoNomeSquadra, nuovaNazionalitaSquadra, nuovaDataInizio, nuovaDataFine, nuovoRuolo, partite, goalSegnati, goalSubiti, ammonizioni, espulsioni)){
                        JOptionPane.showMessageDialog(null, "Militanza modificata correttamente!");
                        VisualizzaMilitanze visualizzaMilitanze= new VisualizzaMilitanze(frameHome, controller);
                        visualizzaMilitanze.frame.setVisible(true);
                        frame.setVisible(false);
                        frame.dispose();
                    }else{
                        JOptionPane.showMessageDialog(null, "Errore: non è stato possibile modificare la militanza!");
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
