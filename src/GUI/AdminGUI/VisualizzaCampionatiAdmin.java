package GUI.AdminGUI;

import Controller.ControllerAdmin;
import Controller.ControllerUtente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VisualizzaCampionatiAdmin {
    private JPanel panel1;
    private JTable tableCampionati;
    private JTable tabellaSquadre;
    private JButton buttonIndietro;
    private JButton visualizzaSquadreDelCampionatoButton;
    public JFrame frame;
    public JFrame frameChiamante;

    public ControllerAdmin controller;
    private JButton indietroButton;
    private JButton aggiungiButton;
    private JButton modificaButton;
    private JButton rimuoviButton;

    public VisualizzaCampionatiAdmin(JFrame frameChiamante, ControllerAdmin controller){
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1800,800);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameChiamante;
        this.controller = controller;


        tableCampionati.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Id Campionato", "Nome Campionato", "Anno Campionato"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableCampionati.setModel(tableModel);

        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaAnni = new ArrayList<>();
        ArrayList<Integer> listaId = new ArrayList<>();

        controller.getCampionati(listaNomi, listaId, listaAnni);

        for(int i = 0; i < listaId.size(); i++){
            tableModel.addRow(new Object[]{listaId.get(i), listaNomi.get(i), listaAnni.get(i)});
        }

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });

        rimuoviButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tableCampionati.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Selezionare un campionato.");}
                else{
                    String nomeSelezionato = tableModel.getValueAt(tableCampionati.getSelectedRow(), 1).toString();
                    String annoselezionato = tableModel.getValueAt(tableCampionati.getSelectedRow(), 2).toString();
                    String ids = tableModel.getValueAt(tableCampionati.getSelectedRow(), 0).toString();
                    int id = Integer.parseInt(ids);

                    int scelta = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare il campionato: " + nomeSelezionato + ", Anno: " + annoselezionato + "?");

                    if(scelta == JOptionPane.YES_NO_OPTION) {
                        if (controller.rimuoviCampionato(id)) {
                            JOptionPane.showMessageDialog(null, "Campionato rimosso correttamente!");
                            VisualizzaCampionatiAdmin visualizzaCampionatiAdmin = new VisualizzaCampionatiAdmin(frameChiamante, controller);
                            visualizzaCampionatiAdmin.frame.setVisible(true);
                            frame.setVisible(false);
                            frame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Errore nella rimozione del campionato.");
                        }
                    }
                }
            }
        });
        aggiungiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> nomi = new ArrayList<>();
                ArrayList<Integer> id = new ArrayList<>();
                ArrayList<String> anni = new ArrayList<>();
                controller.getCampionati(nomi, id, anni);
                finestraAggiungiCampionato finestraAggiungiCampionato = new finestraAggiungiCampionato(frameChiamante, frame, controller, id.size());
                finestraAggiungiCampionato.frame.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
        modificaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeSelezionato = tableModel.getValueAt(tableCampionati.getSelectedRow(), 1).toString();
                String annoselezionato = tableModel.getValueAt(tableCampionati.getSelectedRow(), 2).toString();
                String ids = tableModel.getValueAt(tableCampionati.getSelectedRow(), 0).toString();
                int id = Integer.parseInt(ids);

                finestraModificaCampionato finestraModificaCampionato = new finestraModificaCampionato(frameChiamante, frame, controller, id, nomeSelezionato, annoselezionato);
                finestraModificaCampionato.frame.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
    }
}
