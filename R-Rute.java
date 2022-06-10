import java.util.ArrayList;

abstract class Rute {
    protected int kolonne;
    protected int rad;

    protected Rute nord = null;
    protected Rute soer = null;
    protected Rute vest = null;
    protected Rute oest = null;

    protected Labyrint labyrinten;
    protected boolean besoekt = false;

    // ko er x, ra er y
    public Rute(int ko, int ra, Labyrint laby) {
        kolonne = ko;
        rad = ra;
        labyrinten = laby;
    }

    public abstract char tilTegn();
    public abstract void gaa(ArrayList<Tuppel> sti);

    public void setNaboer(Rute Nord, Rute Soer, Rute Vest, Rute Oest){
        nord=Nord;
        soer = Soer;
        vest = Vest;
        oest=Oest;
    }
        

    public void finnUtvei() {
        ArrayList<Tuppel> sti = new ArrayList<>();
        gaa(sti);
    }
}
