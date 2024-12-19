package visitor;

public class FauteVisiteur implements Visiteur {
    @Override
    public void visiter(Document document) {
        if (document instanceof DocumentHtml html) {
            this.visiter(html);
        }

        else if (document instanceof DocumentPdf pdf) {
            this.visiter(pdf);
        }
    }

    private void visiter(DocumentHtml documentHtml) {
        System.out.println("Correction des fautes dans le document HTML ...");
    }
    
    private void visiter(DocumentPdf documentPdf) {
        System.out.println("Correction des fautes dans le document PDF ...");
    }
}
