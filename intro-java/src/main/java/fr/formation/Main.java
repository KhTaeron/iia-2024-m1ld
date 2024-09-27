package fr.formation;

import fr.formation.iface.InterfaceA;
import fr.formation.model.Produit;
import fr.formation.model.ProduitElectronique;
import fr.formation.model.Telephone;

public class Main {
    public static void main(String[] args) {
        // int a = 0;
        // Integer b = null;

        // float c = 5.0;
        // Float d = 5.0F;

        // double / Double
        // long / Long
        // short / Short
        // int / Integer
        // char / Character

        // DemoStatic.demoStatic();

        System.out.println("Bonjour le monde !");

        ProduitElectronique produit = new Telephone();

        produit.setId("l'id en question");

        System.out.println(produit.getId());

        produit.demarrer();

        System.out.println(produit);

        InterfaceA ia = new Telephone();


        InterfaceA iaFonctionnelle = (a, b) -> {
            System.out.println("Le comportement de la méthode ...");
        };

        iaFonctionnelle.addition(5, 6);

        demoFonctionnelle((a, b) -> {
            System.out.println("OK OK");
        });
    }

    public static void demoFonctionnelle(InterfaceA ia) {
        ia.addition(6, 8);
    }
}
