package GUI.AdminGUI;

import Controller.ControllerAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Objects;

public class finestraModificaSquadra {
    private JFrame frameChiamante;
    public JFrame frame;
    private JTextArea textnome;
    private JTextArea textnazionalita;
    private JButton indietroButton;
    private JButton invioButton;
    private JPanel panel;
    public ControllerAdmin controller;


    public finestraModificaSquadra(JFrame frameHome, JFrame frameChiamante, ControllerAdmin controller, String nomehome, String nazionalitahome) {
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameChiamante;
        this.controller = controller;

        textnome.setText(nomehome);
        textnazionalita.setText(nazionalitahome);

        invioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = textnome.getText();
                String nazionalita = textnazionalita.getText();

                if(controller.updateSquadra(nomehome, nazionalitahome, nome, nazionalita)){
                    JOptionPane.showMessageDialog(null, "Squadra modificata correttamente");
                    VisualizzaSquadre visualizzaSquadre = new VisualizzaSquadre(frameHome, controller);
                    visualizzaSquadre.frame.setVisible(true);
                    frame.setVisible(false);
                    frame.dispose();
                    }
                else{
                    JOptionPane.showMessageDialog(null, "Errore in fase di update");
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
