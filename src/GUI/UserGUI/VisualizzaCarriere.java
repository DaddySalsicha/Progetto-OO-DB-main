package GUI.UserGUI;

import Controller.ControllerUtente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class VisualizzaCarriere {

    public JFrame frame;
    public final JFrame frameChiamante;
    private JPanel panel;
    public ControllerUtente controller;
    private JPanel panelTabella;
    private JTable tableGiocatori;
    private JButton buttonIndietro;
    private JScrollPane scrollPaneTabella;
    private JButton visualizzaInformazioniCompleteGiocatoreButton;
    private JTextField ricercanome;
    private JComboBox Piede;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JTextField ricercaeta;
    private JRadioButton max3;
    private JRadioButton min3;
    private JRadioButton ascgolsegnati;
    private JRadioButton descgolsegnati;
    private JRadioButton ascgolsubiti;
    private JRadioButton descgolsubiti;
    private JComboBox ricercasquadra;
    private JComboBox comboBox1;
    private JRadioButton min1;
    private JRadioButton max1;
    private JRadioButton max2;
    private JRadioButton min2;
    private JRadioButton asceta;
    private JRadioButton desceta;
    private JButton cercabtn;
    private JButton puliscibtn;
    private JScrollPane scrollPane;


    public VisualizzaCarriere(JFrame frameChiamante, ControllerUtente controller){
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400,800);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameChiamante;
        this.controller = controller;

        Piede.addItem(" ");
        Piede.addItem("Dx");
        Piede.addItem("Sx");
        Piede.addItem("Am");

        comboBox2.addItem(" ");
        comboBox2.addItem("0");
        comboBox2.addItem("1");
        comboBox2.addItem("2");
        comboBox2.addItem("3");
        comboBox2.addItem("4");
        comboBox2.addItem("5");
        comboBox2.addItem("6");
        comboBox2.addItem("7");
        comboBox2.addItem("8");
        comboBox2.addItem("9");
        comboBox2.addItem("10");

        comboBox3.addItem(" ");
        comboBox3.addItem("0");
        comboBox3.addItem("1");
        comboBox3.addItem("2");
        comboBox3.addItem("3");
        comboBox3.addItem("4");
        comboBox3.addItem("5");
        comboBox3.addItem("6");
        comboBox3.addItem("7");
        comboBox3.addItem("8");
        comboBox3.addItem("9");
        comboBox3.addItem("10");

        comboBox1.addItem(" ");
        comboBox1.addItem("Portiere");
        comboBox1.addItem("Difensore");
        comboBox1.addItem("Centrocampista");
        comboBox1.addItem("Attaccante");

        ArrayList<String> listaNomiComboBox = new ArrayList<>();
        ArrayList<String> listaNazionalita = new ArrayList<>();

        controller.visualizzaSquadre(listaNomiComboBox, listaNazionalita);

        ricercasquadra.addItem(" ");
        for(int i = 0; i < listaNomiComboBox.size(); i++){
            ricercasquadra.addItem(listaNomiComboBox.get(i));
        }

        tableGiocatori.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Codice Fiscale", "Nome", "Cognome",
                "Data Di Nascita", "Eta", "Piede", "Squadra Attuale", "Ruolo Principale", "Caratteristiche Giocatore", "Partite Giocate",
                "Goal Effettuati", "Goal Subiti", "Ammonizioni", "Espulsioni"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tableGiocatori.setModel(tableModel);

        ArrayList<String> listaCodFisc = new ArrayList<>();
        ArrayList<String> listaNomi = new ArrayList<>();
        ArrayList<String> listaCognomi = new ArrayList<>();
        ArrayList<LocalDate> listaDateNascita = new ArrayList<>();
        ArrayList<Integer> listaEta = new ArrayList<>();
        ArrayList<String> listaPiedi = new ArrayList<>();
        ArrayList<String> listaNomiSquadra = new ArrayList<>();
        ArrayList<String> listaRuoli = new ArrayList<>();
        ArrayList<String> listaCaratteristiche = new ArrayList<>();
        ArrayList<Integer> listaPartiteGiocate = new ArrayList<>();
        ArrayList<Integer> listaGolEffettuati = new ArrayList<>();
        ArrayList<Integer> listaGolSubiti = new ArrayList<>();
        ArrayList<Integer> listaAmmonizioni = new ArrayList<>();
        ArrayList<Integer> listaEspulsioni = new ArrayList<>();

        controller.visualizzaCarriere(listaCodFisc, listaNomi, listaCognomi, listaDateNascita, listaEta, listaPiedi, listaNomiSquadra, listaRuoli,
                listaCaratteristiche, listaPartiteGiocate, listaGolEffettuati, listaGolSubiti, listaAmmonizioni, listaEspulsioni);

        for(int i = 0; i < listaCodFisc.size(); i++){
            tableModel.addRow(new Object[]{listaCodFisc.get(i), listaNomi.get(i), listaCognomi.get(i), listaDateNascita.get(i), listaEta.get(i),
                    listaPiedi.get(i), listaNomiSquadra.get(i), listaRuoli.get(i), listaCaratteristiche.get(i), listaPartiteGiocate.get(i),
                    listaGolEffettuati.get(i), listaGolSubiti.get(i), listaAmmonizioni.get(i),  listaEspulsioni.get(i)});
        }

        buttonIndietro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
        visualizzaInformazioniCompleteGiocatoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codFisc = tableModel.getValueAt(tableGiocatori.getSelectedRow(), 0).toString();
                VisualizzaGiocatore finestraVisualizzaGiocatore = new VisualizzaGiocatore(frame, controller, codFisc);
                finestraVisualizzaGiocatore.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
        min1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(max1.isSelected())
                {
                    max1.setSelected(false);
                }
            }
        });

        max1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(min1.isSelected())
                {
                    min1.setSelected(false);
                }
            }
        });

        ascgolsegnati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(descgolsegnati.isSelected())
                {
                    descgolsegnati.setSelected(false);
                }
            }
        });

        descgolsegnati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ascgolsegnati.isSelected())
                {
                    ascgolsegnati.setSelected(false);
                }
            }
        });

        min2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(max2.isSelected())
                {
                    max2.setSelected(false);
                }
            }
        });

        max2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(min2.isSelected())
                {
                    min2.setSelected(false);
                }
            }
        });

        descgolsubiti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ascgolsubiti.isSelected())
                {
                    ascgolsubiti.setSelected(false);
                }
            }
        });

        ascgolsubiti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(descgolsubiti.isSelected())
                {
                    descgolsubiti.setSelected(false);
                }
            }
        });

        min3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(max3.isSelected())
                {
                    max3.setSelected(false);
                }
            }
        });

        max3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(min3.isSelected())
                {
                    min3.setSelected(false);
                }
            }
        });

        desceta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(asceta.isSelected())
                {
                    asceta.setSelected(false);
                }
            }
        });

        asceta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(desceta.isSelected())
                {
                    desceta.setSelected(false);
                }
            }
        });
        puliscibtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ricercanome.setText("");
                ricercaeta.setText("");
                ricercasquadra.setSelectedItem(" ");
                comboBox1.setSelectedItem(" ");
                comboBox2.setSelectedItem(" ");
                comboBox3.setSelectedItem(" ");
                Piede.setSelectedItem(" ");
                ascgolsubiti.setSelected(false);
                descgolsegnati.setSelected(false);
                ascgolsegnati.setSelected(false);
                descgolsubiti.setSelected(false);
                asceta.setSelected(false);
                desceta.setSelected(false);
                min1.setSelected(false);
                max1.setSelected(false);
                min2.setSelected(false);
                max2.setSelected(false);
                min3.setSelected(false);
                max3.setSelected(false);
            }
        });
        cercabtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome, eta, ruolo, squadra, piede, ordgolsegnati, ordgolsubiti, ordeta, segnogolsegnati, segnogolsubiti, segnoeta;
                int etaricerca, golsubitiricerca, golsegnatiricerca;
                String golsubiti, golsegnati;

                nome = ricercanome.getText();
                if(Objects.equals(nome, "") || Objects.equals(nome, " "))
                {
                    nome = String.valueOf(' ');
                }

                eta = ricercaeta.getText();
                if(Objects.equals(eta, "") || Objects.equals(eta, " "))
                {
                    etaricerca = -1;
                }
                else
                {
                    etaricerca = Integer.parseInt(eta);
                }

                squadra = ricercasquadra.getSelectedItem().toString();

                piede = Piede.getSelectedItem().toString();

                golsubiti = comboBox3.getSelectedItem().toString();
                if(Objects.equals(golsubiti, " ")){golsubitiricerca = -1;}else{golsubitiricerca = Integer.parseInt(golsubiti);}

                golsegnati = comboBox2.getSelectedItem().toString();
                if(Objects.equals(golsegnati," ")){golsegnatiricerca = -1;}else{golsegnatiricerca = Integer.parseInt(golsegnati);}

                ruolo = comboBox1.getSelectedItem().toString();

                if(max2.isSelected())
                {
                    segnogolsegnati = String.valueOf('>');
                }
                else if(min2.isSelected())
                {
                    segnogolsegnati = String.valueOf('<');;
                }
                else
                {
                    segnogolsegnati = String.valueOf(' ');;
                }

                if(max1.isSelected())
                {
                    segnogolsubiti = String.valueOf('>');;
                }
                else if(min1.isSelected())
                {
                    segnogolsubiti = String.valueOf('<');;
                }
                else
                {
                    segnogolsubiti = String.valueOf(' ');;
                }

                if(max3.isSelected())
                {
                    segnoeta = String.valueOf('>');;
                }
                else if(min3.isSelected())
                {
                    segnoeta = String.valueOf('<');;
                }
                else
                {
                    segnoeta = String.valueOf(' ');;
                }

                if(ascgolsubiti.isSelected())
                {
                    ordgolsubiti = "ASC";
                }
                else if(descgolsubiti.isSelected())
                {
                    ordgolsubiti = "DESC";
                }
                else
                {
                    ordgolsubiti = String.valueOf(' ');
                }

                if(ascgolsegnati.isSelected())
                {
                    ordgolsegnati = "ASC";
                }
                else if(descgolsegnati.isSelected())
                {
                    ordgolsegnati = "DESC";
                }
                else
                {
                    ordgolsegnati = String.valueOf(' ');
                }

                if(asceta.isSelected())
                {
                    ordeta = "ASC";
                }
                else if(desceta.isSelected())
                {
                    ordeta = "DESC";
                }
                else
                {
                    ordeta = String.valueOf(' ');
                }

                System.out.println(nome + ruolo + piede + golsegnatiricerca + segnogolsegnati + ordgolsegnati + golsubitiricerca + segnogolsubiti + ordgolsubiti + etaricerca + segnoeta + ordeta + squadra);
                ArrayList<String> listaCodFisc = new ArrayList<>();
                ArrayList<String> listaNomi = new ArrayList<>();
                ArrayList<String> listaCognomi = new ArrayList<>();
                ArrayList<LocalDate> listaDateNascita = new ArrayList<>();
                ArrayList<Integer> listaEta = new ArrayList<>();
                ArrayList<String> listaPiedi = new ArrayList<>();
                ArrayList<String> listaNomiSquadra = new ArrayList<>();
                ArrayList<String> listaRuoli = new ArrayList<>();
                ArrayList<String> listaCaratteristiche = new ArrayList<>();
                ArrayList<Integer> listaPartiteGiocate = new ArrayList<>();
                ArrayList<Integer> listaGolEffettuati = new ArrayList<>();
                ArrayList<Integer> listaGolSubiti = new ArrayList<>();
                ArrayList<Integer> listaAmmonizioni = new ArrayList<>();
                ArrayList<Integer> listaEspulsioni = new ArrayList<>();

                controller.ricerca(listaCodFisc, listaNomi, listaCognomi, listaDateNascita, listaEta, listaPiedi, listaNomiSquadra, listaRuoli,
                        listaCaratteristiche, listaPartiteGiocate, listaGolEffettuati, listaGolSubiti, listaAmmonizioni, listaEspulsioni,
                        nome, etaricerca, ruolo, squadra, piede, ordgolsegnati, ordgolsubiti, ordeta, segnogolsegnati, segnogolsubiti, segnoeta,
                        golsubitiricerca, golsegnatiricerca);

                tableGiocatori.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"Codice Fiscale", "Nome", "Cognome",
                        "Data Di Nascita", "Eta", "Piede", "Squadra Attuale", "Ruolo Principale", "Caratteristiche Giocatore", "Partite Giocate",
                        "Goal Effettuati", "Goal Subiti", "Ammonizioni", "Espulsioni"}) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

                tableGiocatori.setModel(tableModel);

                for(int i = 0; i < listaCodFisc.size(); i++){
                    tableModel.addRow(new Object[]{listaCodFisc.get(i), listaNomi.get(i), listaCognomi.get(i), listaDateNascita.get(i), listaEta.get(i),
                            listaPiedi.get(i), listaNomiSquadra.get(i), listaRuoli.get(i), listaCaratteristiche.get(i), listaPartiteGiocate.get(i),
                            listaGolEffettuati.get(i), listaGolSubiti.get(i), listaAmmonizioni.get(i),  listaEspulsioni.get(i)});
                }
            }
        });
    }
}
