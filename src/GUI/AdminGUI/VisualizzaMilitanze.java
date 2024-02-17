package GUI.AdminGUI;

import Controller.ControllerAdmin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class VisualizzaMilitanze {
    private JPanel panel;
    private JTable tableMilitanze;
    private JScrollPane scrollPaneMilitanze;
    private JButton indietroButton;
    private JButton aggiungiMilitanzaButton;
    private JButton modificaMilitanzaButton;
    private JButton rimuoviMilitanzaButton;
    public JFrame frame;
    private final JFrame frameChiamante;
    public ControllerAdmin controller;


    public VisualizzaMilitanze(JFrame frameChiamante, ControllerAdmin controller){
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameChiamante;
        this.controller = controller;

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });

        tableMilitanze.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Codice Fiscale", "Nome Squadra", "Nazionalita Squadra", "Data Inizio", "Data Fine", "Ruolo", "Partite Giocatre", "Goal Effettuati", "Goal Subiti", "Ammonizioni", "Espulsioni"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableMilitanze.setModel(tableModel);



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

       controller.visualizzaMilitanze(listaCodFisc, listaNomiSquadra, listaNazionalitaSquadra, listaDateInizio, listaDateFine, listaRuoli,
                listaPartitaGiocate, listaGolEffettuati, listaGolSubiti, listaAmmonizioni, listaEspulsioni);

        for(int i = 0; i < listaCodFisc.size(); i++){
            tableModel.addRow(new Object[]{listaCodFisc.get(i), listaNomiSquadra.get(i), listaNazionalitaSquadra.get(i), listaDateInizio.get(i), listaDateInizio.get(i), listaDateFine.get(i), listaRuoli.get(i), listaPartitaGiocate.get(i), listaGolEffettuati.get(i), listaGolSubiti.get(i), listaAmmonizioni.get(i), listaEspulsioni.get(i)});
        }


        aggiungiMilitanzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finestraInserisciMilitanza finestraInserisciMilitanza = new finestraInserisciMilitanza(frameChiamante, frame, controller);
                finestraInserisciMilitanza.frame.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
        rimuoviMilitanzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tableMilitanze.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Selezionare una militanza.");
                }else{
                    String codFisc = tableModel.getValueAt(tableMilitanze.getSelectedRow(), 0).toString();
                    String nomeSquadra = tableModel.getValueAt(tableMilitanze.getSelectedRow(), 1).toString();
                    String nazionalitaSquadra = tableModel.getValueAt(tableMilitanze.getSelectedRow(), 2).toString();
                    LocalDate dataInizio = (LocalDate) tableModel.getValueAt(tableMilitanze.getSelectedRow(), 3);

                    int scelta = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare la militanza " + codFisc + " " + nomeSquadra + " " + nazionalitaSquadra + " iniziata il " + dataInizio + "?");

                    if(scelta == JOptionPane.YES_NO_OPTION) {
                        if (controller.rimuoviMilitanza(codFisc, nomeSquadra, nazionalitaSquadra, dataInizio)) {
                            JOptionPane.showMessageDialog(null, "Militanza rimossa correttamente!");
                            VisualizzaMilitanze visualizzaMilitanze = new VisualizzaMilitanze(frameChiamante, controller);
                            visualizzaMilitanze.frame.setVisible(true);
                            frame.setVisible(false);
                            frame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Errore nella rimozione della militanza.");
                        }
                    }
                }
            }
        });
        modificaMilitanzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(tableMilitanze.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Selezionare una militanza.");
                }else {
                    String codFisc = tableModel.getValueAt(tableMilitanze.getSelectedRow(), 0).toString();
                    String nomeSquadra = tableModel.getValueAt(tableMilitanze.getSelectedRow(), 1).toString();
                    String nazionalitaSquadra = tableModel.getValueAt(tableMilitanze.getSelectedRow(), 2).toString();
                    String[] data = tableModel.getValueAt(tableMilitanze.getSelectedRow(), 3).toString().split("-");
                    LocalDate dataInizio = LocalDate.of(Integer.parseInt(data[0].trim()), Integer.parseInt(data[1].trim()), Integer.parseInt(data[2].trim()));
                    finestraModificaMilitanza finestraModificaMilitanza = new finestraModificaMilitanza(frameChiamante, frame, controller, codFisc, nomeSquadra, nazionalitaSquadra, dataInizio);
                    finestraModificaMilitanza.frame.setVisible(true);
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });
    }
}
