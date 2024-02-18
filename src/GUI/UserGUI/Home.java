package GUI.UserGUI;

import Controller.ControllerUtente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home {

    public static JFrame frame;
    private final JFrame frameChiamante;
    public ControllerUtente controller;
    private JPanel panel;
    private JPanel panelGiocatori;
    private JPanel panelSquadre;
    private JTextField textField1;
    private JButton visualizzaGiocatoriButton;
    private JButton buttonIndietro;
    private JButton visualizzaSquadreButton;
    private JButton visualizzaCampionatiButton;
    private JPanel panelCampionati;


    public Home(JFrame frameChiamante, ControllerUtente controller) {

        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameChiamante;
        this.controller = controller;

        visualizzaGiocatoriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualizzaCarriere finestraVisualizzaCarriere = new VisualizzaCarriere(frame, controller);
                finestraVisualizzaCarriere.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
        buttonIndietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
                controller.emptyModel();
            }
        });
        visualizzaSquadreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualizzaSquadre finestraVisualizzaSquadre = new VisualizzaSquadre(frame, controller);
                finestraVisualizzaSquadre.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
        visualizzaCampionatiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualizzaCampionati finestraVisualizzaCampionati = new VisualizzaCampionati(frame, controller);
                finestraVisualizzaCampionati.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
    }
}
