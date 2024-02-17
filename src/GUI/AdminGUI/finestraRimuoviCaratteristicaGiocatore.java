package GUI.AdminGUI;

import Controller.ControllerAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class finestraRimuoviCaratteristicaGiocatore {
    private JPanel panel;
    private JTextField textField;
    private JComboBox comboBox;
    private JButton indietroButton;
    private JButton invioButton;

    public JFrame frame;
    public ControllerAdmin controller;

    public finestraRimuoviCaratteristicaGiocatore(JFrame frameHome, ControllerAdmin controller, String codFisc){
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.controller = controller;

        textField.setText(codFisc);

        ArrayList<String> caratteristicheGiocatore = controller.getCaratteristicheGiocatore(codFisc);

        for(String s : caratteristicheGiocatore){
            comboBox.addItem(s);
        }

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualizzaGiocatoriAdmin visualizzaGiocatoriAdmin= new VisualizzaGiocatoriAdmin(frameHome, controller);
                visualizzaGiocatoriAdmin.frame.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
        invioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String caratteristica = comboBox.getSelectedItem().toString();
                if(controller.rimuoviCaratteristicaGiocatore(codFisc, caratteristica)){
                    JOptionPane.showMessageDialog(null, "Caratteristica rimossa correttamente!");
                    VisualizzaGiocatoriAdmin visualizzaGiocatoriAdmin= new VisualizzaGiocatoriAdmin(frameHome, controller);
                    visualizzaGiocatoriAdmin.frame.setVisible(true);
                    frame.setVisible(false);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Errore nella rimozione della carattersitica!");
                }
            }
        });
    }
}
