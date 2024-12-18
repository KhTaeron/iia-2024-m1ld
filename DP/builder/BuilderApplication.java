package builder;

public class BuilderApplication {
    public static void main(String[] args) {
        // Produit p1 = new Produit("Lenom", "Laref", "lePrixAchat", "leprix", "lestock");

        Produit p1 = Produit.builder()
            .nom("LeNom")
            .reference("Laref")
            .stock("Lestock")
            .prix("Leprix")
            .build()
        ;
    }
}
