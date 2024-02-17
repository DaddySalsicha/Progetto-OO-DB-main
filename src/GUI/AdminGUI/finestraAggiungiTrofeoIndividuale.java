package GUI.AdminGUI;

import Controller.ControllerAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class finestraAggiungiTrofeoIndividuale {
    private JComboBox comboBoxGiocatori;
    private JTextField textNome;
    private JTextField textAnno;
    private JTextField textMerito;
    private JButton indietroButton;
    private JButton aggiungiButton;
    private JPanel panel1;
    private JPanel panel;
    public JFrame frame, frameChiamante;
    ControllerAdmin controller;
    public finestraAggiungiTrofeoIndividuale(JFrame frameChiamante, JFrame framehome, ControllerAdmin controller){
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameChiamante;
        this.controller = controller;

        ArrayList<String> listaCodFisc = new ArrayList<>();

        controller.visualizzaCodFiscGiocatori(listaCodFisc);

        for(String s : listaCodFisc){
            comboBoxGiocatori.addItem(s);
        }

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });


        aggiungiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codf = comboBoxGiocatori.getSelectedItem().toString();
                String nometrofeo = textNome.getText();
                String anno = textAnno.getText();
                String merito = textMerito.getText();

                if(controller.inserisciTrofeoIndividuale(codf, nometrofeo, anno, merito)){
                    JOptionPane.showMessageDialog(null, "Trofeo aggiunto correttamente!");
                    VisualizzaTrofeiIndividuali visualizzaTrofeiIndividuali= new VisualizzaTrofeiIndividuali(framehome, controller);
                    visualizzaTrofeiIndividuali.frame.setVisible(true);
                    frame.setVisible(false);
                    frame.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Errore nell' aggiunta del trofeo!");
                }
            }
        });
        aggiungiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
