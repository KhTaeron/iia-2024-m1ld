package flyweight;

public class FlyweightApplication {
    public static void main(String[] args) {
        long debut = System.currentTimeMillis();
        Texte texte = new Texte();
        boolean useFlyweight = false;

        texte.ajouter('H', 0, 12, 0, useFlyweight);
        texte.ajouter('e', 0, 12, 0, useFlyweight);
        texte.ajouter('l', 0, 12, 0, useFlyweight);
        texte.ajouter('l', 0, 12, 0, useFlyweight);
        texte.ajouter('o', 0, 12, 0, useFlyweight);
        texte.ajouter(' ', 0, 12, 0, useFlyweight);
        texte.ajouter('W', 0, 24, 0, useFlyweight);
        texte.ajouter('o', 0, 24, 0, useFlyweight);
        texte.ajouter('r', 0, 24, 0, useFlyweight);
        texte.ajouter('l', 0, 24, 0, useFlyweight);
        texte.ajouter('d', 0, 24, 0, useFlyweight);

        texte.imprimer();
        
        for (int i = 0; i < 10_000_000; i++) {
            texte.ajouter('d', 0, 24, 0, useFlyweight);
        }
        
        long fin = System.currentTimeMillis();

        System.out.println("Mémoire utilisée : "+ (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
        System.out.println("Temps passé " + (fin - debut) + " ms");
    }
}
