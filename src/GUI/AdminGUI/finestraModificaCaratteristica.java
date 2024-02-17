package GUI.AdminGUI;

import Controller.ControllerAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class finestraModificaCaratteristica {
    private JPanel panel;
    private JTextField textField;
    private JButton indietroButton;
    private JButton invioButton;

    public JFrame frame;
    private final JFrame frameChiamante;
    public ControllerAdmin controller;


    public finestraModificaCaratteristica(JFrame frameHome, JFrame frameChiamante, ControllerAdmin controller, String caratteristicaSelezionata) {
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameChiamante;
        this.controller = controller;

        textField.setText(caratteristicaSelezionata);
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
                String caratteristica = textField.getText();
                if(controller.modificaCaratteristica(caratteristicaSelezionata, caratteristica)){
                    JOptionPane.showMessageDialog(null, "Caratteristica modificata correttamente!");
                    VisualizzaCaratteristiche visualizzaCaratteristiche= new VisualizzaCaratteristiche(frameHome, controller);
                    visualizzaCaratteristiche.frame.setVisible(true);
                    frame.setVisible(false);
                    frame.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Errore nella modifica della caratteristica!");
                }
            }
        });


    }
}
