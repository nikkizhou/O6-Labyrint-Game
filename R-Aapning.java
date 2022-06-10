import java.util.ArrayList;

public class Aapning extends HvitRute {
    public Aapning(int ko, int ra, Labyrint laby) {
        super(ko, ra, laby);
    }
    
    @Override
    public char tilTegn() {
        return '.';
    }

    @Override
    public void gaa(ArrayList<Tuppel> sti) {
       
        //lagre tuppel av aapningen til arraylisten sti
        Tuppel AapningTuppel = new Tuppel(kolonne, rad);
        AapningTuppel.settSomAapning();
        sti.add(AapningTuppel);

        //legge arraylisten sti til arraylisten veieneUt
        labyrinten.veieneUt.add(new ArrayList<>(sti));
    }
}
