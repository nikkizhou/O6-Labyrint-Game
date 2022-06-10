import java.util.ArrayList;

public class HvitRute extends Rute {
    public HvitRute(int ko, int ra, Labyrint laby) {
        super(ko,ra,laby);
    }

    @Override
    public char tilTegn() {
        return '.';
    }

    @Override
    public void gaa(ArrayList<Tuppel> sti) {

        //legge tuppel av denne ruten til arraylisten sti
        sti = new ArrayList<>(sti);
        sti.add(new Tuppel(kolonne, rad));

        // sett naavaerende ruten til besoekt for aa hindre at det gaar tilbake
        besoekt = true;
        if (!nord.besoekt) nord.gaa(sti);
        if (!soer.besoekt) soer.gaa(sti);
        if (!vest.besoekt) vest.gaa(sti);
        if (!oest.besoekt) oest.gaa(sti); 
    }
}
