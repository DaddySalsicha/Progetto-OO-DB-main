package GUI.AdminGUI;

import Controller.ControllerAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class finestraModificaCampionato {
    private JTextField nomeText;
    private JTextField annoText;
    private JTextField idText;
    private JButton indietroButton;
    private JButton modificaButton;
    private JPanel panel;
    private JFrame frameChiamante;
    public JFrame frame;
    public ControllerAdmin controller;

    public finestraModificaCampionato(JFrame frameHome, JFrame frameChiamante, ControllerAdmin controller, int id, String nome, String anno){
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameChiamante;
        this.controller = controller;

        nomeText.setText(nome);
        annoText.setText(anno);
        idText.setText(String.valueOf(id));
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
                String nome = nomeText.getText();
                String anno = annoText.getText();

                if(controller.modificaCampionato(id, nome, anno)){
                    JOptionPane.showMessageDialog(null, "Campionato modificato correttamente!");
                    VisualizzaCampionatiAdmin visualizzaCampionatiAdmin = new VisualizzaCampionatiAdmin(frameHome, controller);
                    visualizzaCampionatiAdmin.frame.setVisible(true);
                    frame.setVisible(false);
                    frame.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Errore: non è stato possibile aggiornare il campionato!");
                }
            }
        });
    }
}
