package fr.formation.model;

public class Telephone extends ProduitElectronique {
    @Override
    public void demarrer() {
        super.demarrer();
        System.out.println("Le téléphone démarre ...");
    }
}
