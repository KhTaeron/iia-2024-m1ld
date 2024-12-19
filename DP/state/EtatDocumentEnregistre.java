package state;

public class EtatDocumentEnregistre implements EtatDocument {
    private Document document;

    public EtatDocumentEnregistre(Document document) {
        this.document = document;
    }

    @Override
    public void ouvrir() {
        System.out.println("Document en cours d'enregistrement !");
    }

    @Override
    public void enregistrer() {
        System.out.println("Document en cours d'enregistrement !");
    }

    @Override
    public void fermer() {
        System.out.println("Document en cours d'enregistrement !");
    }
}
