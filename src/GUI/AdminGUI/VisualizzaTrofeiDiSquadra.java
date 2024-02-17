package GUI.AdminGUI;

import Controller.ControllerAdmin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VisualizzaTrofeiDiSquadra {
    private JTable table1;
    private JPanel panel1;
    private JButton indietroButton;
    private JButton inserisciButton;
    private JButton rimuoviButton;
    private JButton modificaButton;
    public JFrame frame, frameChiamante;
    ControllerAdmin controller;

    public VisualizzaTrofeiDiSquadra(JFrame frameChiamante, ControllerAdmin controller) {
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameChiamante;
        this.controller = controller;

        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Nome squadra","Nazionalità", "Nome trofeo", "Anno", "Merito"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setModel(tableModel);

        ArrayList<String> nomi = new ArrayList<>();
        ArrayList<String> anni = new ArrayList<>();
        ArrayList<String> meriti = new ArrayList<>();
        ArrayList<String> squadre = new ArrayList<>();
        ArrayList<String> nazionalita = new ArrayList<>();


        controller.visualizzaTrofeiSquadra(nomi, anni, meriti, squadre, nazionalita);

        for (int i = 0; i < nomi.size(); i++) {
            tableModel.addRow(new Object[]{squadre.get(i), nazionalita.get(i),nomi.get(i), anni.get(i), meriti.get(i)});
        }
        rimuoviButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table1.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Selezionare un trofeo.");
                } else {
                    String nomeSelezionato = tableModel.getValueAt(table1.getSelectedRow(), 1).toString();
                    String annoSelezionato = tableModel.getValueAt(table1.getSelectedRow(), 2).toString();
                    String merito = tableModel.getValueAt(table1.getSelectedRow(), 3).toString();

                    int scelta = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare il trofeo: " + nomeSelezionato + ", Anno: " + annoSelezionato + "?");

                    if (scelta == JOptionPane.YES_NO_OPTION) {
                        if (controller.rimuoviTrofeoDiSquadra(nomeSelezionato, annoSelezionato)) {
                            JOptionPane.showMessageDialog(null, "Trofeo rimosso correttamente!");
                            VisualizzaTrofeiIndividuali visualizzaTrofeiIndividuali = new VisualizzaTrofeiIndividuali(frameChiamante, controller);
                            visualizzaTrofeiIndividuali.frame.setVisible(true);
                            frame.setVisible(false);
                            frame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Errore nella rimozione del trofeo.");
                        }
                    }
                }
            }
        });
        modificaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Selezionare un trofeo.");
                }
                else {
                    String nomesquadra = tableModel.getValueAt(table1.getSelectedRow(), 0).toString();
                    String nazionalita = tableModel.getValueAt(table1.getSelectedRow(), 1).toString();
                    String nomeSelezionato = tableModel.getValueAt(table1.getSelectedRow(), 2).toString();
                    String annoSelezionato = tableModel.getValueAt(table1.getSelectedRow(), 3).toString();
                    String merito = tableModel.getValueAt(table1.getSelectedRow(), 4).toString();

                    finestraModificaTrofeoDiSquadra finestraModificaTrofeoDiSquadra = new finestraModificaTrofeoDiSquadra(frameChiamante, frame, controller, nomeSelezionato, annoSelezionato, merito, nomesquadra, nazionalita);
                    finestraModificaTrofeoDiSquadra.frame.setVisible(true);
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });
        inserisciButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finestraAggiungiTrofeoDiSquadra finestraAggiungiTrofeoDiSquadra = new finestraAggiungiTrofeoDiSquadra(frameChiamante, frame, controller);
                finestraAggiungiTrofeoDiSquadra.frame.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
    }
}

