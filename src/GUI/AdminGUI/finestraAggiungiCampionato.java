package GUI.AdminGUI;

import Controller.ControllerAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class finestraAggiungiCampionato {
    private JTextField textNome;
    private JTextField textAnno;
    private JButton indietroButton;
    private JButton aggiungiButton;
    private JTextField textId;
    private JPanel panel;
    private JPanel panel1;
    public JFrame frame;
    private JFrame frameChiamante;
    public ControllerAdmin controller;

    public finestraAggiungiCampionato(JFrame frameHome, JFrame frameChiamante, ControllerAdmin controller, int id){
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameHome;
        this.controller = controller;

        textId.setText(String.valueOf(id));
        aggiungiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = textNome.getText();
                String anno = textAnno.getText();

                if(controller.inserisciCampionato(id, nome, anno)){
                    JOptionPane.showMessageDialog(null, "Campionato aggiunto correttamente!");
                    VisualizzaCampionatiAdmin visualizzaCampionatiAdmin = new VisualizzaCampionatiAdmin(frameHome, controller);
                    visualizzaCampionatiAdmin.frame.setVisible(true);
                    frame.setVisible(false);
                    frame.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Errore: non è stato possibile inserire il campionato!");
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
    }
}
