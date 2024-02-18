package GUI.AdminGUI;

import Controller.ControllerAdmin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VisualizzaSquadre {
    private JTable table;
    private JPanel panel;
    private JButton modificaSquadraButton;
    private JButton inserisciSquadraButton;
    private JButton rimuoviSquadraButton;
    private JButton indietroButton;
    public JFrame frame, frameChiamante;

    public ControllerAdmin controller;
    public VisualizzaSquadre(JFrame frameChiamante, ControllerAdmin controller){
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1800,800);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameChiamante;
        this.controller = controller;

        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Nome", "Nazionalita"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setModel(tableModel);

        ArrayList<String> nomi = new ArrayList<>();
        ArrayList<String> nazionalita = new ArrayList<>();

        controller.visualizzaSquadre(nomi, nazionalita);

        for(int i = 0; i < nomi.size(); i++){
            tableModel.addRow(new Object[]{nomi.get(i), nazionalita.get(i)});
        }

        rimuoviSquadraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Selezionare una squadra.");}
                else{
                    String nomeSelezionato = tableModel.getValueAt(table.getSelectedRow(), 0).toString();
                    String nazionalitaSelezionato = tableModel.getValueAt(table.getSelectedRow(), 1).toString();

                    int scelta = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare la squadra: " + nomeSelezionato + ", Nazionalità: " + nazionalitaSelezionato + "?");

                    if(scelta == JOptionPane.YES_NO_OPTION) {
                        if (controller.rimuoviSquadra(nomeSelezionato, nazionalitaSelezionato)) {
                            JOptionPane.showMessageDialog(null, "Squadra rimossa correttamente!");
                            VisualizzaSquadre visualizzaSquadre = new VisualizzaSquadre(frameChiamante, controller);
                            visualizzaSquadre.frame.setVisible(true);
                            frame.setVisible(false);
                            frame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Errore nella rimozione della squadra.");
                        }
                    }
                }
            }
        });
        modificaSquadraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Selezionare una squadra.");
                }
                else{
                    String nomeSelezionato = tableModel.getValueAt(table.getSelectedRow(), 0).toString();
                    String nazionalitaSelezionato = tableModel.getValueAt(table.getSelectedRow(), 1).toString();

                    finestraModificaSquadra finestraModificaSquadra = new finestraModificaSquadra(frameChiamante, frame, controller, nomeSelezionato, nazionalitaSelezionato);
                    finestraModificaSquadra.frame.setVisible(true);
                    frame.setVisible(false);
                    frame.dispose();
                }
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
        inserisciSquadraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finestraAggiungiSquadra finestraAggiungiSquadra = new finestraAggiungiSquadra(frameChiamante, frame, controller);
                finestraAggiungiSquadra.frame.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
    }
}