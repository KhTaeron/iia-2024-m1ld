package state;

public class EtatDocumentFerme implements EtatDocument {
    private Document document;

    public EtatDocumentFerme(Document document) {
        this.document = document;
    }

    @Override
    public void ouvrir() {
        System.out.println("Ouverture du document ...");
        this.document.setEtat(new EtatDocumentOuvert(this.document));
    }

    @Override
    public void enregistrer() {
        System.out.println("Impossible d'enregistrer, le document est fermé !");
    }

    @Override
    public void fermer() {
        System.out.println("Document déjà fermé !");
    }
}
