public class Tuppel {
    int kolonne;
    int rad;
    boolean erAapning;

    public Tuppel(int ko, int ra) {
        kolonne = ko;
        rad = ra;    
    }


    @Override
    public String toString() {

        return "(" + kolonne + "," + rad + ")";
 
    }
    
    // metodene settSomAapning og erAapning er baade for a merke at en Rute er en Aapning naar det skrives ut.
    public void settSomAapning() {
        erAapning = true;
    }

    public boolean erAapning(){
        return erAapning;
    }
    
}
