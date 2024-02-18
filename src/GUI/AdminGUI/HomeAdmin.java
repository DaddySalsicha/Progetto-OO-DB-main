package GUI.AdminGUI;

import Controller.ControllerAdmin;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeAdmin {
    public static JFrame frame;
    private final JFrame frameChiamante;

    public ControllerAdmin controller;
    private JPanel panel;
    private JPanel panelGiocatori;
    private JButton visualizzaGiocatoriButton;
    private JPanel panelCampionati;
    private JButton visualizzaCampionatiButton;
    private JPanel panelSquadre;
    private JButton visualizzaSquadreButton;
    private JButton indietroButton;
    private JButton visualizzaTrofei;
    private JButton visualizzaMilitanze;
    private JButton visualizzaCaratteristiche;

    public HomeAdmin(JFrame frameChiamante, ControllerAdmin controller) {

        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameChiamante;
        this.controller = controller;

        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
                controller.emptyModel();
            }
        });
        visualizzaGiocatoriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualizzaGiocatoriAdmin finestraVisualizzaGiocatoriAdmin = new VisualizzaGiocatoriAdmin(frame, controller);
                finestraVisualizzaGiocatoriAdmin.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
        visualizzaSquadreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualizzaSquadre finestraVisualizzaSquadreAdmin = new VisualizzaSquadre(frame, controller);
                finestraVisualizzaSquadreAdmin.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
        visualizzaCampionatiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualizzaCampionatiAdmin finestraVisualizzaCampionati = new VisualizzaCampionatiAdmin(frame, controller);
                finestraVisualizzaCampionati.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
        visualizzaTrofei.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualizzaTrofei finestraVisualizzaTrofei = new VisualizzaTrofei(frame, controller);
                finestraVisualizzaTrofei.frame.setVisible(true);
                frame.setVisible(false);
            }
        });

        visualizzaMilitanze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualizzaMilitanze finestraVisualizzamilitanze = new VisualizzaMilitanze(frame, controller);
                finestraVisualizzamilitanze.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
        visualizzaCaratteristiche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualizzaCaratteristiche finestraVisualizzaCaratteristiche = new VisualizzaCaratteristiche(frame, controller);
                finestraVisualizzaCaratteristiche.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
    }
}
