package state;

public class Document {
    private EtatDocument etat = new EtatDocumentFerme(this);

    public void setEtat(EtatDocument etat) {
        this.etat = etat;
    }

    public void ouvrir() {
        this.etat.ouvrir();
    }
    
    public void enregistrer() {
        this.etat.enregistrer();
    }
    
    public void fermer() {
        this.etat.fermer();
    }
}
