package state;

public class EtatDocumentOuvert implements EtatDocument {
    private Document document;

    public EtatDocumentOuvert(Document document) {
        this.document = document;
    }

    @Override
    public void ouvrir() {
        System.out.println("Document déjà ouvert !");
    }
    
    @Override
    public void enregistrer() {
        System.out.println("Enregistrement en cours ...");
        this.document.setEtat(new EtatDocumentEnregistre(this.document));

        Thread simulateSaving = new Thread(() -> {
            try {
                Thread.sleep(1000);
            }
            
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            System.out.println("Enregistrement terminé !");
            this.document.setEtat(new EtatDocumentOuvert(this.document));
        });

        simulateSaving.start();
    }
    
    @Override
    public void fermer() {
        System.out.println("Fermeture du document ...");
        this.document.setEtat(new EtatDocumentFerme(this.document));
    }
}
