package GUI.AdminGUI;

import Controller.ControllerAdmin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VisualizzaTrofeiIndividuali {
    private JTable table1;
    private JPanel panel1;
    private JButton indietroButton;
    private JButton inserisciButton;
    private JButton rimuoviButton;
    private JButton modificaButton;
    public JFrame frame, frameChiamante;
    ControllerAdmin controller;

    public VisualizzaTrofeiIndividuali(JFrame frameChiamante, ControllerAdmin controller) {
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameChiamante;
        this.controller = controller;

        table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Nome giocatore", "Nome trofeo", "Anno", "Merito"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table1.setModel(tableModel);

        ArrayList<String> nomi = new ArrayList<>();
        ArrayList<String> anni = new ArrayList<>();
        ArrayList<String> meriti = new ArrayList<>();
        ArrayList<String> codf = new ArrayList<>();


        controller.visualizzaTrofeiIndividuali(nomi, anni, meriti, codf);

        for (int i = 0; i < nomi.size(); i++) {
            tableModel.addRow(new Object[]{codf.get(i), nomi.get(i), anni.get(i), meriti.get(i)});
        }
        rimuoviButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (table1.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Selezionare un trofeo.");
                } else {
                    String nomeSelezionato = tableModel.getValueAt(table1.getSelectedRow(), 1).toString();
                    String annoSelezionato = tableModel.getValueAt(table1.getSelectedRow(), 2).toString();

                    int scelta = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare il trofeo: " + nomeSelezionato + ", Anno: " + annoSelezionato + "?");

                    if (scelta == JOptionPane.YES_NO_OPTION) {
                        if (controller.rimuoviTrofeoIndividuale(nomeSelezionato, annoSelezionato)) {
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
                    String codfselezionato = tableModel.getValueAt(table1.getSelectedRow(), 0).toString();
                    String nomeSelezionato = tableModel.getValueAt(table1.getSelectedRow(), 1).toString();
                    String annoSelezionato = tableModel.getValueAt(table1.getSelectedRow(), 2).toString();
                    String merito = tableModel.getValueAt(table1.getSelectedRow(), 3).toString();

                    finestraModificaTrofeoIndividuale finestraModificaTrofeoIndividuale = new finestraModificaTrofeoIndividuale(frameChiamante, frame, controller, nomeSelezionato, annoSelezionato, merito, codfselezionato);
                    finestraModificaTrofeoIndividuale.frame.setVisible(true);
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });
        inserisciButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finestraAggiungiTrofeoIndividuale finestraAggiungiTrofeoIndividuale = new finestraAggiungiTrofeoIndividuale(frameChiamante, frame, controller);
                finestraAggiungiTrofeoIndividuale.frame.setVisible(true);
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

