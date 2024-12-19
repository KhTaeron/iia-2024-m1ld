package visitor;

public class DocumentHtml implements Document {
    @Override
    public void filtrer(Visiteur visiteur) {
        visiteur.visiter(this);
    }
}
