package GUI.UserGUI;

import Controller.ControllerUtente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VisualizzaCampionati {
    private JPanel panel;
    private JTable tableCampionati;
    private JTable tabellaSquadre;
    private JScrollPane scrollpaneTabella;
    private JPanel panelTabella;
    private JButton buttonIndietro;
    private JButton visualizzaSquadreDelCampionatoButton;
    public JFrame frame;
    public final JFrame frameChiamante;
    public ControllerUtente controller;

    public VisualizzaCampionati(JFrame frameChiamante, ControllerUtente controller) {
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1800, 800);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameChiamante;
        this.controller = controller;


        tableCampionati.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Nome Campionato", "Anno Campionato", "Id Campionato"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableCampionati.setModel(tableModel);

        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaAnni = new ArrayList<>();
        ArrayList<Integer> listaId = new ArrayList<>();

        controller.visualizzaCampionati(listaNomi, listaAnni, listaId);

        for (int i = 0; i < listaId.size(); i++) {
            tableModel.addRow(new Object[]{listaNomi.get(i), listaAnni.get(i), listaId.get(i)});
        }


        buttonIndietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
    }
}
