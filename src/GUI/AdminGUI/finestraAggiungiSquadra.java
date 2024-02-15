package GUI.AdminGUI;

import Controller.ControllerAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class finestraAggiungiSquadra {
    private JPanel panel;
    private JTextField textNome;
    private JTextField textNazionalita;
    private JButton invioButton;
    private JButton indietroButton;
    private JComboBox comboBoxCampionato;

    public JFrame frame;
    private JFrame frameChiamante;
    public ControllerAdmin controller;

    public finestraAggiungiSquadra(JFrame frameHome, JFrame frameChiamante, ControllerAdmin controller){
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameHome;
        this.controller = controller;

        ArrayList<String> nomi = new ArrayList<>();
        ArrayList<String> anni = new ArrayList<>();
        ArrayList<Integer> id = new ArrayList<>();
        controller.getCampionati(nomi, id, anni);
        int i = 0;
        for(String s: nomi)
        {
            comboBoxCampionato.addItem(s + " " + anni.get(i));
            i++;
        }
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
        invioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nazionalita = textNazionalita.getText();
                String nome = textNome.getText();
                String campionato = comboBoxCampionato.getSelectedItem().toString();
                int id = comboBoxCampionato.getSelectedIndex();
                
                if(controller.inserisciSquadra(nome, nazionalita, id)) {
                    JOptionPane.showMessageDialog(null, "Squadra aggiunta correttamente!");
                    VisualizzaSquadre visualizzaSquadre = new VisualizzaSquadre(frameHome, controller);
                    visualizzaSquadre.frame.setVisible(true);
                    frame.setVisible(false);
                    frame.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Errore: non è stato possibile inserire la squadra!");
                }
            }
        });
    }
}
