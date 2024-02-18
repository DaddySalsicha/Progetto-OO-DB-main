package GUI.AdminGUI;

import Controller.ControllerAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class finestraAggiungiGiocatore {
    public JFrame frame;
    private final JFrame frameChiamante;
    public ControllerAdmin controller;
    private JPanel panel;
    private JTextField textFieldNome;
    private JLabel labelNome;
    private JTextField textFieldCognome;
    private JLabel labelCognome;
    private JTextField textFieldCodiceFiscale;
    private JLabel labelCodiceFiscale;
    private JComboBox comboBoxPiede;
    private JLabel labelPiede;
    private JButton indietroButton;
    private JButton invioButton;
    private JComboBox boxGiornoDoB;
    private JComboBox boxMeseDoB;
    private JComboBox boxAnnoDoB;

    public finestraAggiungiGiocatore(JFrame frameHome, JFrame frameChiamante, ControllerAdmin controller){
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameHome;
        this.controller = controller;

        comboBoxPiede.addItem("Dx");
        comboBoxPiede.addItem("Sx");
        comboBoxPiede.addItem("Am");
        for(int i = 1; i < 32; i++){
            boxGiornoDoB.addItem(i);
        }
        for(int i = 1900; i < LocalDate.now().getYear(); i++){
            boxAnnoDoB.addItem(i);
        }
        for(int i = 1; i < 13; i++){
            boxMeseDoB.addItem(i);
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
                String codFisc = textFieldCodiceFiscale.getText();
                String nome = textFieldNome.getText();
                String cognome = textFieldCognome.getText();
                String piede = comboBoxPiede.getSelectedItem().toString();
                LocalDate dataDiNascita = LocalDate.of(Integer.parseInt(boxAnnoDoB.getSelectedItem().toString()), Integer.parseInt(boxMeseDoB.getSelectedItem().toString()), Integer.parseInt(boxGiornoDoB.getSelectedItem().toString()));

                if(codFisc.length() != 16){
                    JOptionPane.showMessageDialog(null, "Il codice fiscale deve contenere esattamente 16 caratteri.");
                } else if (!contieneSoloLettere(nome) || !contieneSoloLettere(cognome)) {
                    JOptionPane.showMessageDialog(null, "Nome e Cognome devono contenere solo lettere dell'alfabeto");
                } else if(controller.inserisciGiocatore(nome, cognome, codFisc, piede, dataDiNascita)) {
                        JOptionPane.showMessageDialog(null, "Giocatore aggiunto correttamente!");
                        VisualizzaGiocatoriAdmin visualizzaGiocatoriAdmin = new VisualizzaGiocatoriAdmin(frameHome, controller);
                        visualizzaGiocatoriAdmin.frame.setVisible(true);
                        frame.setVisible(false);
                        frame.dispose();
                }else{
                        JOptionPane.showMessageDialog(null, "Errore: non è stato possibile inserire il giocatore!");
                }
            }
        });
    }


    private static boolean contieneSoloLettere(String input) {
        for (char carattere : input.toCharArray()) {
            if (!Character.isLetter(carattere)) {
                return false;
            }
        }
        return true;
    }
}

