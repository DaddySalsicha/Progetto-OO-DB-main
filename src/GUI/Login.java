package GUI;

import Controller.ControllerAdmin;
import Controller.ControllerUtente;
import GUI.AdminGUI.HomeAdmin;
import GUI.UserGUI.Home;
import GUI.UserGUI.Registrazione;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login {
    public static JFrame frame;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JPanel panel;
    private JPanel panelLogin;
    private JPanel panelPassword;
    private JLabel passwordLabel;
    private JLabel loginLabel;
    private JButton button;
    private JLabel caricamento;
    private ImageIcon loading = new ImageIcon("loading.gif");
    private JButton buttonRegistrazione;
    private JPanel panelReg;
    private JPanel panelCaricamento;
    private JLabel labelCaricamento;
    private JPanel panelPermessi;
    private JComboBox boxPermessi;
    private JLabel labelPermessi;
    private String login;
    private String password;
    private String permessi;
    public ControllerAdmin controllerAdmin = new ControllerAdmin();
    public ControllerUtente controllerUtente = new ControllerUtente();
    private HomeAdmin finestraHomeAdmin;


    public Login(){
        panelCaricamento.setVisible(false);
        labelCaricamento.setSize(200,200);
        boxPermessi.addItem("Admin");
        boxPermessi.addItem("Utente");
        boxPermessi.setSelectedItem("Utente");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiLogin();
            }
        });
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    guiLogin();
                }
            }
        });
        loginField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    guiLogin();
                }
            }
        });
        buttonRegistrazione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registrazione finestraRegistrazione = new Registrazione(frame, controllerUtente);
                finestraRegistrazione.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
    }

    public boolean loginAdmin(ControllerAdmin controller, String login, String password){
        panelLogin.setVisible(false);
        panelPassword.setVisible(false);
        panelReg.setVisible(false);
        panelCaricamento.setVisible(true);
        return controller.login(login, password);
    }

    public boolean loginUtente(ControllerUtente controller, String login, String password){
        panelLogin.setVisible(false);
        panelPassword.setVisible(false);
        panelReg.setVisible(false);
        panelCaricamento.setVisible(true);
        return controller.login(login, password);
    }

    public void guiLogin() {
        login = new String(loginField.getText());
        password = new String(passwordField.getPassword());
        permessi = new String(boxPermessi.getSelectedItem().toString());

        if ((password.isEmpty() || password.isBlank()) && (login.isBlank() || login.isEmpty())) {
            JOptionPane.showMessageDialog(null, "I campi login e password non possono essere vuoti.");
        } else if (login.isBlank() || login.isEmpty()) {
            JOptionPane.showMessageDialog(null ,"Il campo login non puo' essere vuoto.");
        } else if (password.isEmpty() || password.isBlank()) {
            JOptionPane.showMessageDialog(null, "Il campo password non puo' essere vuoto.");
        } else if (permessi.isEmpty() || permessi.isBlank()) {
            JOptionPane.showMessageDialog(null, "Il campo permessi non puo' essere vuoto.");
        } else if (permessi.equals("Admin")) {
            panelCaricamento.setVisible(false);
            panelLogin.setVisible(true);
            panelPassword.setVisible(true);
            panelReg.setVisible(true);
            if (loginAdmin(controllerAdmin, login, password)) {
                panelCaricamento.setVisible(false);
                panelLogin.setVisible(true);
                panelPassword.setVisible(true);
                panelReg.setVisible(true);
                HomeAdmin finestraHomeAdmin = new HomeAdmin(frame, controllerAdmin);
                finestraHomeAdmin.frame.setVisible(true);
                frame.setVisible(false);
            } else {
                loginError();
            }
        } else if(permessi.equals("Utente")){
            if(loginUtente(controllerUtente, login, password)){
                panelCaricamento.setVisible(false);
                panelLogin.setVisible(true);
                panelPassword.setVisible(true);
                panelReg.setVisible(true);
                Home finestraHome = new Home(frame, controllerUtente);
                finestraHome.frame.setVisible(true);
                frame.setVisible(false);
            }
            else
            {
                panelCaricamento.setVisible(false);
                panelLogin.setVisible(true);
                panelPassword.setVisible(true);
                panelReg.setVisible(true);
                JOptionPane.showMessageDialog(null, "Errore con le credenziali utente");
            }
        }
    }

    public void loginError(){
        panelCaricamento.setVisible(false);
        panelLogin.setVisible(true);
        panelPassword.setVisible(true);
        panelReg.setVisible(true);
        JOptionPane.showMessageDialog(null, "Errore durante il login.\nAssicurati di aver inserito correttamente login, password e permessi.");
    }

    public static void main(String[] args){
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(new Login().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,600);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        frame.setVisible(true);
    }
}
