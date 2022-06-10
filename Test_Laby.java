import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        Labyrint laby = new Labyrint(args[0]);
        System.out.println(laby.toString() + "\n");
        //l.testNaboer();
        //laby.finnUtveiFra(0, 0);

        System.out.println("Skriv inn koordinater: kolonne rad ('a' for aa avslutte)");
        String svar = sc.nextLine();
        String[] koordinater=null;
        
        while (!svar.equals("a")) {
            koordinater = svar.split(" ");

            try {
                int kolonne = Integer.parseInt(koordinater[0]);
                int rad = Integer.parseInt(koordinater[1]);

                if (rad < laby.antRad && kolonne < laby.antKo) {
                    laby.finnUtveiFra(kolonne, rad);

                } else {
                    System.out.println("rad maa vaere mindre enn " + laby.antRad + ", og kolonne maa vaere mindre enn "
                            + laby.antKo);
                }
                
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException "+e.getMessage()+". Skriv koordinater: kolonne rad");
            }
            
            System.out.println("\nSkriv inn nye koordinater ('a' for aa avslutte)");
            svar = sc.nextLine();
        }
    
    }  
}
