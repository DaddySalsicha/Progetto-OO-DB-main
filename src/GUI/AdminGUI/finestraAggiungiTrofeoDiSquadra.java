package GUI.AdminGUI;

import Controller.ControllerAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class finestraAggiungiTrofeoDiSquadra {
    private JPanel panel1;
    private JPanel panel;
    private JComboBox comboBoxSquadre;
    private JTextField textNome;
    private JTextField textAnno;
    private JTextField textMerito;
    private JButton indietroButton;
    private JButton aggiungiButton;
    public JFrame frame, frameChiamante;
    ControllerAdmin controller;

    public finestraAggiungiTrofeoDiSquadra(JFrame frameChiamante, JFrame frameHome, ControllerAdmin controller) {
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameChiamante;
        this.controller = controller;

        ArrayList<String> listanomi = new ArrayList<>();
        ArrayList<String> listanazionalita = new ArrayList<>();

        controller.visualizzaSquadre(listanomi, listanazionalita);

        int i = 0;
        for(String s : listanomi){
            comboBoxSquadre.addItem(s + ","+listanazionalita.get(i));
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
        aggiungiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeNazionalita = comboBoxSquadre.getSelectedItem().toString();
                int index = nomeNazionalita.indexOf(",");
                String nuovoNomeSquadra = nomeNazionalita.substring(0, index);
                String nuovaNazionalita = nomeNazionalita.substring(index+1);
                String nomeTrofeo = textNome.getText();
                String anno = textAnno.getText();
                String merito = textMerito.getText();

                if(controller.inserisciTrofeoDiSquadra(nuovoNomeSquadra, nuovaNazionalita, nomeTrofeo, anno, merito)){
                    JOptionPane.showMessageDialog(null, "Trofeo aggiunto correttamente!");
                    VisualizzaTrofeiDiSquadra visualizzaTrofeiDiSquadra= new VisualizzaTrofeiDiSquadra(frameHome, controller);
                    visualizzaTrofeiDiSquadra.frame.setVisible(true);
                    frame.setVisible(false);
                    frame.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Errore nell' aggiunta del trofeo!");
                }
            }
        });
    }
}
