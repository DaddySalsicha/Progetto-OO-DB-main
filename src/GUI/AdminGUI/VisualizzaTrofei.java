package GUI.AdminGUI;

import Controller.ControllerAdmin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VisualizzaTrofei {
    private JButton trofeiIndividualiButton;
    private JPanel panel1;
    private JButton trofeiDiSquadraButton;
    private JButton indietroButton;
    public static JFrame frame;
    private final JFrame frameChiamante;
    ControllerAdmin controller;

    public VisualizzaTrofei(JFrame framechiamante, ControllerAdmin controller){
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = framechiamante;
        this.controller = controller;
        trofeiIndividualiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualizzaTrofeiIndividuali finestravisualizzatrofeiindividuali = new VisualizzaTrofeiIndividuali(frame, controller);
                finestravisualizzatrofeiindividuali.frame.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
        trofeiDiSquadraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VisualizzaTrofeiDiSquadra finestravisualizzatrofeidisquadra = new VisualizzaTrofeiDiSquadra(frame, controller);
                finestravisualizzatrofeidisquadra.frame.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
    }
}
