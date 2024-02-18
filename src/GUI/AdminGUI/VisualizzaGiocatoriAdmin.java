package GUI.AdminGUI;

import Controller.ControllerAdmin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class VisualizzaGiocatoriAdmin {
    public JFrame frame;
    private final JFrame frameChiamante;
    public ControllerAdmin controller;
    private JPanel panel;
    private JButton indietroButton;
    private JTable tableGiocatori;
    private JPanel panelGiocatori;
    private JScrollPane scrollpaneGiocatori;
    private JPanel panelBottoni;
    private JPanel panelBottoniGiocatore;
    private JButton aggiungiGiocatoreButton;
    private JButton modificaGiocatoreButton;
    private JButton rimuoviGiocatoreButton;
    private JPanel panelCaratteristicheGiocatori;
    private JButton aggiungiCaratteristicaAGiocatoreButton;
    private JButton rimuoviCaratteristicaAGiocatoreButton;

    public VisualizzaGiocatoriAdmin(JFrame frameChiamante, ControllerAdmin controller) {

        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,400);
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

        tableGiocatori.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Codice Fiscale", "Nome", "Cognome", "Piede", "Caratteristiche", "Data Di Nascita", "Data di Ritiro"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableGiocatori.setModel(tableModel);

        ArrayList<String> listaCodFisc = new ArrayList<>();
        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaCognomi = new ArrayList<>();
        ArrayList<String> listaPiedi = new ArrayList<>();
        ArrayList<String> listaCaratteristiche = new ArrayList<>();
        ArrayList<LocalDate> listaDoB = new ArrayList<>();
        ArrayList<LocalDate> listaDoR = new ArrayList<>();
        controller.visualizzaGiocatori(listaCodFisc, listaNomi, listaCognomi, listaPiedi, listaCaratteristiche, listaDoB, listaDoR);

        for(int i = 0; i < listaCodFisc.size(); i++){
            tableModel.addRow(new Object[]{listaCodFisc.get(i), listaNomi.get(i), listaCognomi.get(i), listaPiedi.get(i), listaCaratteristiche.get(i), listaDoB.get(i), listaDoR.get(i)});
        }

        aggiungiGiocatoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finestraAggiungiGiocatore finestraAggiungiGiocatore = new finestraAggiungiGiocatore(frameChiamante, frame, controller);
                finestraAggiungiGiocatore.frame.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
        modificaGiocatoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tableGiocatori.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Selezionare un giocatore.");
                }
                else {
                    String codFiscSelezionato = tableModel.getValueAt(tableGiocatori.getSelectedRow(), 0).toString();
                    finestraModificaGiocatore finestraModificaGiocatore = new finestraModificaGiocatore(frameChiamante, frame, controller, codFiscSelezionato);
                    finestraModificaGiocatore.frame.setVisible(true);
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });
        rimuoviGiocatoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tableGiocatori.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Selezionare un giocatore.");
                }else{
                    String codFiscSelezionato = tableModel.getValueAt(tableGiocatori.getSelectedRow(), 0).toString();
                    String nomeSelezionato = tableModel.getValueAt(tableGiocatori.getSelectedRow(), 1).toString();
                    String cognomeSelezionato = tableModel.getValueAt(tableGiocatori.getSelectedRow(), 2).toString();

                    int scelta = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare il giocatore " + nomeSelezionato + " " + cognomeSelezionato + "?");

                    if(scelta == JOptionPane.YES_NO_OPTION) {
                        if (controller.rimuoviGiocatore(codFiscSelezionato)) {
                            JOptionPane.showMessageDialog(null, "Giocatore rimosso correttamente!");
                            VisualizzaGiocatoriAdmin visualizzaGiocatoriAdmin = new VisualizzaGiocatoriAdmin(frameChiamante, controller);
                            visualizzaGiocatoriAdmin.frame.setVisible(true);
                            frame.setVisible(false);
                            frame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Errore nella rimozione del giocatore.");
                        }
                    }
                }
            }
        });
        aggiungiCaratteristicaAGiocatoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tableGiocatori.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Selezionare un giocatore.");
                }else {
                    String codFisc = tableModel.getValueAt(tableGiocatori.getSelectedRow(), 0).toString();
                    finestraAggiungiCaratteristicaGiocatore finestraAggiungiCaratteristicaGiocatore = new finestraAggiungiCaratteristicaGiocatore(frameChiamante, controller, codFisc);
                    finestraAggiungiCaratteristicaGiocatore.frame.setVisible(true);
                    frame.setVisible(false);
                }
            }
        });
        rimuoviCaratteristicaAGiocatoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String codFisc = tableModel.getValueAt(tableGiocatori.getSelectedRow(), 0).toString();
                finestraRimuoviCaratteristicaGiocatore finestraRimuoviCaratteristicaGiocatore = new finestraRimuoviCaratteristicaGiocatore(frameChiamante, controller, codFisc);
                finestraRimuoviCaratteristicaGiocatore.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
    }
}