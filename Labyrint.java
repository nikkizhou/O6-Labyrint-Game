import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class Labyrint {

    // arrayen labyrint er et todimensjonalt Rute-array.
    private Rute[][] labyrint; //[[##..#],[...##],[.#.##]...]
    public int antRad;
    public int antKo;
    
    // opprett en variabel for a inneholde alle veier ut
    public ArrayList<ArrayList<Tuppel>> veieneUt = new ArrayList<ArrayList<Tuppel>>();

    public Labyrint(File fil) throws Exception {

        // DEL 1: les data fra fil og lagre data i ArrayListen labyrinten
        lesFraFil(fil);


        //DEL 2: etter DEL 1, skal vi sette naboer for alle objektene.
        settNaboerForAlle();
    }


    //DEL1
    public void lesFraFil(File fil) throws Exception {
        Scanner sc = new Scanner(fil);

        String[] antRadOgKo = sc.nextLine().split(" ");
        antRad = Integer.parseInt(antRadOgKo[0]);
        antKo = Integer.parseInt(antRadOgKo[1]);
        labyrint = new Rute[antRad][antKo];

        while (sc.hasNextLine()) {
            for (int i = 0; i < antRad; i++) {
                String rad = sc.nextLine();

                for (int j = 0; j < antKo; j++) {

                    if (rad.charAt(j) == '.') {
                        // opprett Aapning Objekt
                        if (j == 0 || j == antKo - 1 || i == 0 || i == antRad - 1) {
                            labyrint[i][j] = new Aapning(j, i, this);
                            // opprett HvitRute Objekt
                        } else {
                            labyrint[i][j] = new HvitRute(j, i, this);
                        }

                    } else if (rad.charAt(j) == '#') {
                        labyrint[i][j] = new SortRute(j,i,this);
                    } else {
                        System.out.println("Ugyldig representasjon.");
                        return;
                    }
                }
            }
            
        }

    }
    
    


    // DEL2
    public void settNaboerForAlle() {
        for (int i = 0; i < antRad; i++) {
            for (int j = 0; j < antKo; j++) {

                // hvis denne Ruten er i foerste raden, skal nord naboen vaere null
                Rute nord = (i == 0) ? null : labyrint[i - 1][j];
                // hvis denne Ruten er i siste raden, skal soer naboen vaere null
                Rute soer = (i == antRad - 1) ? null : labyrint[i + 1][j];
                // hvis denne Ruten er i foerste kolonne, skal vest naboen vaere null
                Rute vest = (j == 0) ? null : labyrint[i][j - 1];
                // hvis denne Ruten er i siste kolonne, skal oest naboen vaere null
                Rute oest = (j == antKo - 1) ? null : labyrint[i][j + 1];

                labyrint[i][j].setNaboer(nord, soer, vest, oest);
            }
        }

    }

    // skriv ut labyrinten i riktig format
    public String toString() {
        String tegnForLaby = "";
        for (int i = 0; i < antRad; i++) {
            for (int j = 0; j < antKo; j++) {
                tegnForLaby += labyrint[i][j].tilTegn();
            }
            tegnForLaby += "\n";
        }
        return tegnForLaby;
    }


    //teste funksjonen settNaboerForAlle 
    public void testNaboer() {
        for (int i = 0; i < antRad; i++) {
            for (int j = 0; j < antKo; j++) {
                Rute denne = labyrint[i][j];

                // hvis naboen er lik null, skal strengN="X"
                char strengN = denne.nord == null ? 'X' : denne.nord.tilTegn();
                char strengS = denne.soer == null ? 'X' : denne.soer.tilTegn();
                char strengV = denne.vest == null ? 'X' : denne.vest.tilTegn();
                char strengE = denne.oest == null ? 'X' : denne.oest.tilTegn();

                System.out.println("    " + strengN);
                System.out.println(strengV + " Rute" + j + i + " " + strengE);
                System.out.println("    " + strengS + "\n");

            }
        }
    }
    
    // set en rute i en bestemt koordinate til null
    public void settRuteTilNull(int kol, int rad) {

        //set kobling fra naboen til null
        if (rad >= 1 && labyrint[rad - 1][kol] != null) {
            labyrint[rad - 1][kol].soer = null; 
        }     
        if (rad < antRad - 1 && labyrint[rad + 1][kol] != null) {
            labyrint[rad + 1][kol].nord = null;
        }      
        if (kol >= 1 && labyrint[rad][kol - 1] != null) {
            labyrint[rad][kol - 1].oest = null;
        }              
        if (kol < antKo - 1 && labyrint[rad][kol + 1] != null) {
            labyrint[rad][kol + 1].vest = null;
            
        }    
        // set ruten til null
        labyrint[rad][kol] = null;
    }
    
    public Rute hentRute(int kol, int rad) {
        return labyrint[rad][kol];
    }
    
    public ArrayList<ArrayList<Tuppel>> finnUtveiFra(int kol, int rad) {
        // hvis starte ruten er en sortRute, maa vi gi tilbakemelding.
        if (labyrint[rad][kol] instanceof HvitRute) {
            labyrint[rad][kol].finnUtvei();

        } else {
            System.out.println("Koordinaten (" + kol + "," + rad + ") refererer ikke til en HvitRute. Skriv igjen.");
            return null;
        }

        //sett alle rutene til Ubesoekt for neste runden.
        settUbesoekt();

        // tomme listen veieneUt for a gjoer seg klar for neste runden.
        ArrayList<ArrayList<Tuppel>> veieneUtCopy = veieneUt;
        veieneUt = new ArrayList<>();

        return veieneUtCopy;
    }
    
    // sett alle rutene til Ubesoekt for neste runden.
    public void settUbesoekt() {
        for (int i = 0; i < labyrint.length; i++) {
            for (int j = 0; j < labyrint[i].length; j++) {
                labyrint[i][j].besoekt = false;
            }
        }
    }
}
