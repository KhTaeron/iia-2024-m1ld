package visitor;

public class DocumentPdf implements Document {
    @Override
    public void filtrer(Visiteur visiteur) {
        visiteur.visiter(this);
    }
}
