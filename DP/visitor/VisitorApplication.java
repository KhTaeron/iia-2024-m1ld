package visitor;

import java.util.ArrayList;
import java.util.List;

public class VisitorApplication {
    public static void main(String[] args) {
        List<Document> documents = new ArrayList<>();
        List<Visiteur> visiteurs = new ArrayList<>();

        documents.add(new DocumentHtml());
        documents.add(new DocumentPdf());
        documents.add(new DocumentPdf());
        documents.add(new DocumentPdf());

        visiteurs.add(new FauteVisiteur());
        visiteurs.add(new LienVisiteur());
        visiteurs.add(new LienVisiteur());

        for (Visiteur visiteur : visiteurs) {
            for (Document doc : documents) {
                // visiteur.visiter(doc);
                doc.filtrer(visiteur);
            }
        }
    }
}
