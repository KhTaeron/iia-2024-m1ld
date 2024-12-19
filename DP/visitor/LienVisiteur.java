package visitor;

public class LienVisiteur implements Visiteur {
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
        System.out.println("Application des liens dans le document HTML ...");
    }
    
    private void visiter(DocumentPdf documentPdf) {
        System.out.println("Application des liens dans le document PDF ...");
    }
}
