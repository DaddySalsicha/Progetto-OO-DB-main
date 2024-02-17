package GUI.AdminGUI;

import Controller.ControllerAdmin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VisualizzaCaratteristiche {
    public JFrame frame;
    private final JFrame frameChiamante;
    public ControllerAdmin controller;
    private JPanel panel;
    private JButton indietroButton;
    private JTable tableCaratteristiche;
    private JButton aggiungiCaratteristicaButton;
    private JButton rimuoviCaratteristicaButton;
    private JButton modificaCaratteristicaButton;

    public VisualizzaCaratteristiche(JFrame frameChiamante, ControllerAdmin controller) {
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameChiamante;
        this.controller = controller;

        tableCaratteristiche.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Tipo Caratteristica"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableCaratteristiche.setModel(tableModel);

        ArrayList<String> listaCaratteristiche = controller.getListaCaratteristiche();

        for (String s : listaCaratteristiche) {
            tableModel.addRow(new Object[]{s});
        }

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });

        aggiungiCaratteristicaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finestraAggiungiCaratteristica finestraAggiungiCaratteristica = new finestraAggiungiCaratteristica(frameChiamante, frame, controller);
                finestraAggiungiCaratteristica.frame.setVisible(true);
                frame.setVisible(false);
                frame.dispose();

            }
        });
        modificaCaratteristicaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableCaratteristiche.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Selezionare una caratteristica.");
                } else {
                    String caratteristica = tableModel.getValueAt(tableCaratteristiche.getSelectedRow(), 0).toString();
                    finestraModificaCaratteristica finestraModificaCaratteristica = new finestraModificaCaratteristica(frameChiamante, frame, controller, caratteristica);
                    finestraModificaCaratteristica.frame.setVisible(true);
                    frame.setVisible(false);
                    frame.dispose();
                }
            }
        });
        rimuoviCaratteristicaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableCaratteristiche.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Selezionare una caratteristica.");
                } else {
                    String caratteristica = tableModel.getValueAt(tableCaratteristiche.getSelectedRow(), 0).toString();
                    int scelta = JOptionPane.showConfirmDialog(null, "Sei sicuro di voler eliminare la caratteristica " + caratteristica + "?");

                    if (scelta == JOptionPane.YES_NO_OPTION) {
                        if (controller.rimuoviCaratteristica(caratteristica)) {
                            JOptionPane.showMessageDialog(null, "Caratteristica rimossa correttamente!");
                            VisualizzaCaratteristiche visualizzaCaratteristiche = new VisualizzaCaratteristiche(frameChiamante, controller);
                            visualizzaCaratteristiche.frame.setVisible(true);
                            frame.setVisible(false);
                            frame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Errore nella rimozione della militanza.");
                        }
                    }
                }
            }
        });
    }
}
