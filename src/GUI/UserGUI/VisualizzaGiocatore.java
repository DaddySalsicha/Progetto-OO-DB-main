package GUI.UserGUI;

import Controller.ControllerUtente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class VisualizzaGiocatore {
    private JPanel panel;
    private JPanel panelBottoni;
    private JPanel panelInformazioniGiocatore;
    private JTable tableGiocatore;
    private JTable tabellaTrofei;
    private JTable tabellaMilitanze;
    private JButton buttonVisualizzaTrofei;
    private JButton buttonVisualizzaMilitanze;
    private JButton indietroButton;
    public JFrame frame;
    public final JFrame frameChiamante;
    public ControllerUtente controller;

    public VisualizzaGiocatore(JFrame frameChiamante, ControllerUtente controller, String codFisc){
        frame = new JFrame("Campionado - The assist to your goal");
        frame.setContentPane(this.panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1800,800);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(0x6FC276));
        this.frameChiamante = frameChiamante;
        this.controller = controller;

        tableGiocatore.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableModel tableModel = new DefaultTableModel(new Object[][]{}, new String[]{"", "Informazioni Giocatore"}) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableGiocatore.setModel(tableModel);

        ArrayList<String> listaAttributi = new ArrayList<>();
        String caratteristiche = controller.getListaCaratteristicheGiocatore(codFisc);
        LocalDate dataNascita = controller.getDoBGiocatore(codFisc);
        LocalDate dataRitiro = controller.getDoRGiocatore(codFisc);
        controller.visualizzaGiocatore(codFisc, listaAttributi);


        tableModel.addRow(new Object[]{"Codice Fiscale", listaAttributi.get(0)});
        tableModel.addRow(new Object[]{"Nome", listaAttributi.get(1)});
        tableModel.addRow(new Object[]{"Cognome", listaAttributi.get(2)});
        tableModel.addRow(new Object[]{"Piede", listaAttributi.get(3)});
        tableModel.addRow(new Object[]{"Caratteristiche", caratteristiche});
        tableModel.addRow(new Object[]{"Data di Nascita", dataNascita});
        tableModel.addRow(new Object[]{"Data di Ritiro", dataRitiro});


        buttonVisualizzaTrofei.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> listaNomiTrofeo = new ArrayList<>();
                ArrayList<String> listaAnniTrofeo = new ArrayList<>();
                ArrayList<String> listaMeritiTrofeo = new ArrayList<>();

                controller.visualizzaTrofeiGiocatore(codFisc, listaNomiTrofeo, listaAnniTrofeo, listaMeritiTrofeo);

                tabellaTrofei = new JTable();
                tabellaTrofei.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                DefaultTableModel tableModelTrofei = new DefaultTableModel(new Object[][]{}, new String[]{"Nome Trofeo", "Anno di vittoria", "Merito di vittoria"}) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tabellaTrofei.setModel(tableModelTrofei);

                for(int i = 0; i < listaNomiTrofeo.size(); i++){
                    tableModelTrofei.addRow(new Object[]{listaNomiTrofeo.get(i), listaAnniTrofeo.get(i), listaMeritiTrofeo.get(i)});
                }

                JPanel panelTabellaTrofei = new JPanel();
                panelTabellaTrofei.add(new JScrollPane(tabellaTrofei));

                JDialog dialogTrofeiVinti = new JDialog();
                dialogTrofeiVinti.setTitle("Trofei vinti da: " + listaAttributi.get(1) + " " + listaAttributi.get(2));
                dialogTrofeiVinti.setLayout(new GridLayout());
                dialogTrofeiVinti.add(tabellaTrofei);
                dialogTrofeiVinti.setSize(400, 300);
                dialogTrofeiVinti.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dialogTrofeiVinti.setLocationRelativeTo(null);
                dialogTrofeiVinti.setResizable(true);
                dialogTrofeiVinti.setVisible(true);

            }
        });
        buttonVisualizzaMilitanze.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> listaNomiSquadra = new ArrayList<>();
                ArrayList<String> listaNazionalitaSquadra = new ArrayList<>();
                ArrayList<String> listaRuoli = new ArrayList<>();
                ArrayList<LocalDate> listaDateInizio = new ArrayList<>();
                ArrayList<LocalDate> listaDateFine = new ArrayList<>();
                ArrayList<Integer> listaGoalSegnati = new ArrayList<>();
                ArrayList<Integer> listaGoalSubiti = new ArrayList<>();
                ArrayList<Integer> listaPartiteGiocate = new ArrayList<>();
                ArrayList<Integer> listaAmmonizioni = new ArrayList<>();
                ArrayList<Integer> listaEspulsioni = new ArrayList<>();

                controller.visualizzaMilitanzeGiocatore(codFisc, listaNomiSquadra, listaNazionalitaSquadra, listaRuoli, listaDateInizio,
                        listaDateFine, listaGoalSegnati, listaGoalSubiti, listaPartiteGiocate, listaAmmonizioni, listaEspulsioni);

                tabellaMilitanze = new JTable();
                tabellaMilitanze.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
                DefaultTableModel tableModelMilitanze = new DefaultTableModel(new Object[][]{}, new String[]{"Nome Squadra", "Nazionalita Squadra", "Ruolo", "Data Inizio", "Data Fine", "Goal Segnati", "Goal Subiti", "Partite Giocate", "Ammonizioni", "Espulsioni"}) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                tabellaMilitanze.setModel(tableModelMilitanze);

                for(int i = 0; i < listaNomiSquadra.size(); i++){
                    tableModelMilitanze.addRow(new Object[]{listaNomiSquadra.get(i), listaNazionalitaSquadra.get(i), listaRuoli.get(i), listaDateInizio.get(i), listaDateFine.get(i), listaGoalSegnati.get(i), listaGoalSubiti.get(i), listaPartiteGiocate.get(i), listaAmmonizioni.get(i), listaEspulsioni.get(i)});
                }

                JPanel panelTabellaMilitanze = new JPanel();
                panelTabellaMilitanze.add(new JScrollPane(tabellaMilitanze));

                JDialog dialogMilitanze = new JDialog();
                dialogMilitanze.setTitle("Militanze di: " + listaAttributi.get(1) + " " + listaAttributi.get(2));
                dialogMilitanze.setLayout(new GridLayout());
                dialogMilitanze.add(tabellaMilitanze);
                dialogMilitanze.setSize(800, 600);
                dialogMilitanze.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dialogMilitanze.setLocationRelativeTo(null);
                dialogMilitanze.setResizable(true);
                dialogMilitanze.setVisible(true);

            }
        });
        indietroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                frameChiamante.setVisible(true);
                frame.dispose();
            }
        });
    }
}
