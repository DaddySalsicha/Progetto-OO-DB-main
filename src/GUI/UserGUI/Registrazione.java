package GUI.UserGUI;

import Controller.ControllerUtente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Registrazione {
    public JFrame frame;
    private JLabel labelRegistrazione;
    private JPasswordField passwordField;
    private JTextField loginField;
    private JLabel labelLoginReg;
    private JLabel labelPasswReg;
    private JPanel panel;
    private JButton goBackButton;
    private JButton buttonRegistrati;
    private JPanel panelRegistrazione;
    private JPanel panelLogin;
    private JPanel panelPassword;
    private JPanel panelGoBack;
    private JPanel panelCaricamento;
    private JLabel labelCaricamento;
    public ControllerUtente controller;
    private final JFrame frameChiamante;
    private String login;
    private String password;


    public Registrazione(JFrame frameChiamante, ControllerUtente controller) {

        panelCaricamento.setVisible(false);
        labelCaricamento.setSize(200,200);
        this.controller = controller;
        this.frameChiamante = frameChiamante;

        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameChiamante.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
        buttonRegistrati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiRegistrazione();
            }
        });
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    guiRegistrazione();
                }
            }
        });
        loginField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    guiRegistrazione();
                }
            }
        });
    }

    public void guiRegistrazione(){
        login = new String(loginField.getText());
        password = new String(passwordField.getPassword());
        if ((password.isEmpty() || password.isBlank()) && (login.isBlank() || login.isEmpty())) {
            JOptionPane.showMessageDialog(null, "I campi login e password non possono essere vuoti.");
        } else if (login.isBlank() || login.isEmpty()) {
            JOptionPane.showMessageDialog(null ,"Il campo login non puo' essere vuoto.");
        } else if (password.isEmpty() || password.isBlank()) {
            JOptionPane.showMessageDialog(null, "Il campo password non puo' essere vuoto.");
        } else{
            if(signUp(login, password)){
                panelCaricamento.setVisible(false);
                panelRegistrazione.setVisible(true);
                panelLogin.setVisible(true);
                panelPassword.setVisible(true);
                JOptionPane.showMessageDialog(null, "Registrazione completata, per accedere torna alla pagina di login.");
            }else{
                panelCaricamento.setVisible(false);
                panelRegistrazione.setVisible(true);
                panelLogin.setVisible(true);
                panelPassword.setVisible(true);
                JOptionPane.showMessageDialog(null, "Errore: la registrazione non e' andata a buon termine.");
            }
        }
    }

    public boolean signUp(String login, String password){
        panelRegistrazione.setVisible(false);
        panelLogin.setVisible(false);
        panelCaricamento.setVisible(true);
        return controller.signUp(login, password);
    }
}