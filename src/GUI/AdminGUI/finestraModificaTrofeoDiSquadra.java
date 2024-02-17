package GUI.AdminGUI;

import Controller.ControllerAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Objects;

public class finestraModificaTrofeoDiSquadra {
    private JPanel panel;
    private JComboBox comboBoxSquadra;
    private JTextField textNome;
    private JTextField textAnno;
    private JTextField textMerito;
    private JButton indietroButton;
    private JButton modificaButton;
    private JPanel panel1;
    public JFrame frame, frameChiamante;
    ControllerAdmin controller;

    public finestraModificaTrofeoDiSquadra(JFrame frameChiamante, JFrame framehome, ControllerAdmin controller, String nome, String anno, String merito, String nomesquadra, String nazionalita){
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
            comboBoxSquadra.addItem(s + ","+listanazionalita.get(i));
            i++;
        }
        textAnno.setText(anno);
        textMerito.setText(merito);
        textNome.setText(nome);


        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });

        modificaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomenazionalita = comboBoxSquadra.getSelectedItem().toString();
                int index = nomenazionalita.indexOf(",");
                String nuovonomesquadra = nomenazionalita.substring(0, index);
                String nuovanazionalita = nomenazionalita.substring(index+1);
                String nuovonometrofeo = textNome.getText();
                String nuovoanno = textAnno.getText();
                String nuovomerito = textMerito.getText();

                if(controller.modificaTrofeoDiSquadra(nomesquadra, nazionalita, nome, anno, merito, nuovonomesquadra, nuovanazionalita, nuovonometrofeo, nuovoanno, nuovomerito)){
                    JOptionPane.showMessageDialog(null, "Trofeo modificato correttamente!");
                    VisualizzaTrofeiDiSquadra visualizzaTrofeiDiSquadra= new VisualizzaTrofeiDiSquadra(framehome, controller);
                    visualizzaTrofeiDiSquadra.frame.setVisible(true);
                    frame.setVisible(false);
                    frame.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Errore nella modifica del trofeo!");
                }
            }
        });
    }
}
