package GUI.AdminGUI;

import Controller.ControllerAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Objects;

public class finestraModificaGiocatore {
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
    private JLabel labelPiede;
    private JComboBox comboBoxPiede;
    private JComboBox boxGiornoDoB;
    private JComboBox boxAnnoDoB;
    private JComboBox boxMeseDoB;
    private JButton indietroButton;
    private JButton invioButton;
    private JComboBox boxGiornoDoR;
    private JComboBox boxAnnoDoR;
    private JComboBox boxMeseDoR;

    public finestraModificaGiocatore(JFrame frameHome, JFrame frameChiamante, ControllerAdmin controller, String codFisc) {
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameHome;
        this.controller = controller;

        String nome = controller.getNomeGiocatore(codFisc);
        String cognome = controller.getCognomeGiocatore(codFisc);
        LocalDate dataNascita = controller.getDataNascitaGiocatore(codFisc);
        String piede = controller.getPiedeGiocatore(codFisc);
        LocalDate dataRitiro = controller.getDataRitiroGiocatore(codFisc);

        boxGiornoDoB.addItem(dataNascita.getDayOfMonth());
        boxMeseDoB.addItem(dataNascita.getMonthValue());
        boxAnnoDoB.addItem(dataNascita.getYear());
        comboBoxPiede.addItem("Dx");
        comboBoxPiede.addItem("Sx");
        comboBoxPiede.addItem("Am");
        comboBoxPiede.setSelectedItem(piede);
        textFieldCodiceFiscale.setText(codFisc);
        textFieldCognome.setText(cognome);
        textFieldNome.setText(nome);

        boxGiornoDoR.addItem("");
        for(int i = 1; i < 32; i++){
            boxGiornoDoB.addItem(i);
            boxGiornoDoR.addItem(i);
        }
        boxGiornoDoR.setSelectedItem("");
        boxGiornoDoB.setSelectedItem(dataNascita.getDayOfMonth());

        boxAnnoDoR.addItem("");
        for(int i = 1900; i < LocalDate.now().getYear(); i++){
            boxAnnoDoB.addItem(i);
            boxAnnoDoR.addItem(i);
        }
        boxGiornoDoB.setSelectedItem(dataNascita.getYear());
        boxAnnoDoR.setSelectedItem("");

        boxMeseDoR.addItem("");
        for(int i = 1; i < 13; i++){
            boxMeseDoB.addItem(i);
            boxMeseDoR.addItem(i);
        }
        boxMeseDoB.setSelectedItem(dataNascita.getMonthValue());
        boxMeseDoR.setSelectedItem("");

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
                String nome = textFieldNome.getText();
                String cognome = textFieldCognome.getText();
                String piede = comboBoxPiede.getSelectedItem().toString();
                String nuovocodfisc = textFieldCodiceFiscale.getText();
                LocalDate datanascitanuova = LocalDate.of(Integer.parseInt(boxAnnoDoB.getSelectedItem().toString()), Integer.parseInt(boxMeseDoB.getSelectedItem().toString()), Integer.parseInt(boxGiornoDoB.getSelectedItem().toString()));
                if(!Objects.equals(boxGiornoDoR.getSelectedItem().toString(), "") && !Objects.equals(boxMeseDoR.getSelectedItem().toString(), "") && !Objects.equals(boxAnnoDoR.getSelectedItem().toString(), ""))
                {
                    LocalDate datadiritironuova = LocalDate.of(Integer.parseInt(boxAnnoDoR.getSelectedItem().toString()), Integer.parseInt(boxMeseDoR.getSelectedItem().toString()), Integer.parseInt(boxGiornoDoR.getSelectedItem().toString()));
                    if(controller.updateGiocatore(codFisc, nuovocodfisc, nome, cognome, piede, datanascitanuova, datadiritironuova)){
                        JOptionPane.showMessageDialog(null, "Giocatore modificato correttamente");
                        VisualizzaGiocatoriAdmin visualizzaGiocatoriAdmin = new VisualizzaGiocatoriAdmin(frameHome, controller);
                        visualizzaGiocatoriAdmin.frame.setVisible(true);
                        frame.setVisible(false);
                        frame.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Errore in fase di update");
                    }
                }
                else if(Objects.equals(boxGiornoDoR.getSelectedItem().toString(), "") && Objects.equals(boxMeseDoR.getSelectedItem().toString(), "") && Objects.equals(boxAnnoDoR.getSelectedItem().toString(), ""))
                {
                    if(controller.updateGiocatore(codFisc, nuovocodfisc, nome, cognome, piede, datanascitanuova, LocalDate.of(0, 1, 1))){
                        JOptionPane.showMessageDialog(null, "Giocatore modificato correttamente");
                        VisualizzaGiocatoriAdmin visualizzaGiocatoriAdmin = new VisualizzaGiocatoriAdmin(frameHome, controller);
                        visualizzaGiocatoriAdmin.frame.setVisible(true);
                        frame.setVisible(false);
                        frame.dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Errore in fase di update");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Inserire i dati correttamente");
                }
            }
        });
    }

}
