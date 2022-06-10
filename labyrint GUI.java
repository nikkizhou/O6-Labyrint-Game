import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;
import java.io.*;
import static javax.swing.JOptionPane.showMessageDialog;

// p1: antVeierLable viser ikke i vinduet
// p2: ruteGUI.setText("X");  linje 146,   viser ikke X men ...
// p3: hvordan gjoer at loesningene viser en etter en naar brukeren trykker paa en knapp

class LabyrintGUI {
    static Labyrint labyrinten = null;
    static JPanel brett = new JPanel();
    static JLabel antVeierLable = new JLabel();
    static JPanel panel = new JPanel();
    

    public static void main(String[] arg) throws Exception {
        JFrame vindu = new JFrame("Labyrint");
        panel = new JPanel();
        
        vindu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vindu.add(panel);

        //Labyrinten er ikke null lenger etter lesFil(); 
        Scanner leser = lesFil();

        // brett er fylt med buttons etter dette.
        fillBrett(leser);
        panel.add(brett);

        //viser ikke ?????????????
        //JLabel test = new JLabel("Hei");
        //panel.add(test);
        panel.add(antVeierLable);

        vindu.pack();
        vindu.setVisible(true);
    }

    static Scanner lesFil() throws Exception{
        JFileChooser velger = new JFileChooser();
        int resultat = velger.showOpenDialog(null);

        if (resultat != JFileChooser.APPROVE_OPTION) {
            System.exit(1);
        }

        File f = velger.getSelectedFile();
        Scanner leser = null;
        try {
            leser = new Scanner(f);
            labyrinten = new Labyrint(f);
        } catch (FileNotFoundException e) {
            System.exit(1);
        }
        return leser;
    }


    static void fillBrett(Scanner leser) {
        String[] linje = leser.nextLine().trim().split(" ");
        int antRad = Integer.parseInt(linje[0]);
        int antKol = Integer.parseInt(linje[1]);

        brett.setLayout(new GridLayout(antRad, antKol));

        for (int i = 0; i < antRad; i++) {
            String rad = leser.nextLine();

            for (int j = 0; j < antKol; j++) {
                RuteGUI rute = null;

                if (rad.charAt(j) == '.') {
                    rute = new HvitRuteGUI();
                } else if (rad.charAt(j) == '#') {
                    rute = new SortRuteGUI();
                } else {
                    System.out.println("Ugyldig representasjon.");
                }
                rute.setPosition(i, j);
                rute.initGUI();
                brett.add(rute);
            }
        }
    }

}

abstract class RuteGUI extends JButton {
    int rad, kol;

    public void setPosition(int rad, int kol) {
        this.rad = rad;
        this.kol = kol;
    }
    
    void initGUI() {
        setPreferredSize(new Dimension(20, 20));
        setOpaque(true);
    }

    public int[] getPosition() {
        int[] positionArr = new int[2];
        positionArr[0] = kol;
        positionArr[1] = rad;
        return positionArr;
    }

    public String getPosStr() {
        return "(" + kol + "," + rad + ")";
    }
}

class HvitRuteGUI extends RuteGUI {
    @Override
    void initGUI() {
        super.initGUI();
        setBackground(Color.WHITE);
        addActionListener(new KlikkHvit());
    }

    class KlikkHvit extends LabyrintGUI implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            // blanker alle rutene foer en ny løsning genereres
            for (Component rute : brett.getComponents()) {
                RuteGUI ruteGUI = (RuteGUI) rute;
                ruteGUI.setText("");
            }

            //naar brukeren klikk paa en hvitrute, skal systemet finner vei ut.
            int kol = getPosition()[0];
            int rad = getPosition()[1];

            ArrayList<ArrayList<Tuppel>> utveiene = labyrinten.finnUtveiFra(kol, rad);
            int antVeier = utveiene.size();
            antVeierLable.setText("Det er " + antVeier + " veier ut.");
            
            if (!utveiene.isEmpty()) {
                ArrayList<Tuppel> TuppleList0 = utveiene.get(0);
                for (Tuppel tuppel : TuppleList0) {
                    for (Component rute : brett.getComponents()) {
                        RuteGUI ruteGUI = (RuteGUI) rute;
                        if (ruteGUI.getPosStr().equals(tuppel.toString())) {
                            ruteGUI.setText("X");
                            break;
                        }
                    }
                }
            } else {
                
                showMessageDialog(null, "Ingen utvei herfra. \nKlikk en annen rute!:)");
            }
        }    
    }
}

class SortRuteGUI extends RuteGUI {
    @Override
    void initGUI() {
        super.initGUI();
        setBackground(Color.BLACK);
        addActionListener(new KlikkSort());
    }

    class KlikkSort implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //naar brukeren klikk paa en scart, skal systemet alert
        }
    }
}
