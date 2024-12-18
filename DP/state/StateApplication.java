package state;

public class StateApplication {
    public static void main(String[] args) {
        Document document = new Document();

        document.fermer();
        document.ouvrir();
        document.ouvrir();
        document.fermer();
        document.enregistrer();
        document.ouvrir();
        document.enregistrer();
        document.fermer();
    }
}
