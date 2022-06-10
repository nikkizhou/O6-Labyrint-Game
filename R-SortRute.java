import java.util.ArrayList;

public class SortRute extends Rute {
    public SortRute(int ko, int ra, Labyrint laby) {
        super(ko,ra,laby);
    }

    @Override
    public char tilTegn() {
        return '#';
    }
    

    @Override
    public void gaa(ArrayList<Tuppel> sti) {
        return;
    }

    
}
