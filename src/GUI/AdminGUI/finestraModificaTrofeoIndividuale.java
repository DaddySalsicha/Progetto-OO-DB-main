package GUI.AdminGUI;

import Controller.ControllerAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class finestraModificaTrofeoIndividuale {
    private JComboBox comboBoxGiocatori;
    private JTextField textNome;
    private JTextField textAnno;
    private JTextField textMerito;
    private JButton indietroButton;
    private JButton modificaButton;
    private JPanel panel;
    public JFrame frame, frameChiamante;
    ControllerAdmin controller;
    public finestraModificaTrofeoIndividuale(JFrame frameChiamante, JFrame framehome, ControllerAdmin controller, String nometrofeo, String anno, String merito, String codf){
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameChiamante;
        this.controller = controller;

        textAnno.setText(anno);
        textMerito.setText(merito);
        textNome.setText(nometrofeo);

        ArrayList<String> listaCodFisc = new ArrayList<>();

        controller.visualizzaCodFiscGiocatori(listaCodFisc);

        for(String s : listaCodFisc){
            comboBoxGiocatori.addItem(s);
        }
        comboBoxGiocatori.setSelectedItem(codf);
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
                String nuovocodf = comboBoxGiocatori.getSelectedItem().toString();
                String nuovonometrofeo = textNome.getText();
                String nuovoanno = textAnno.getText();
                String nuovomerito = textMerito.getText();

                if(controller.modificaTrofeoIndividuale(nuovocodf, nuovonometrofeo, nuovoanno, nuovomerito, codf, nometrofeo, anno, merito)){
                    JOptionPane.showMessageDialog(null, "Trofeo modificato correttamente!");
                    VisualizzaTrofeiIndividuali visualizzaTrofeiIndividuali= new VisualizzaTrofeiIndividuali(framehome, controller);
                    visualizzaTrofeiIndividuali.frame.setVisible(true);
                    frame.setVisible(false);
                    frame.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Errore nella modifica del trofeo!");
                }
            }
        });
    }
}
