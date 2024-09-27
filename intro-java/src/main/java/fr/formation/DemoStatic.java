package fr.formation;

public class DemoStatic {

    public int count = 0;
    public int id = count++;
    
    public void demo() {
        System.out.println("Démo");
    }

    public static void demoStatic() {
        System.out.println("Démo statique");
    }

}
