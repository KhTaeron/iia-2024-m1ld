package builder;

public class Produit {
    private String nom;
    private String reference;
    private String prixAchat;
    private String prix;
    private String stock;

    public Produit() {
        this.nom = nom;
        this.reference = reference;
        this.prix = prix;
        this.prixAchat = prixAchat;
        this.stock = stock;
    }

    public Produit(String nom, String reference, String prix, String prixAchat, String stock) {
        this.nom = nom;
        this.reference = reference;
        this.prix = prix;
        this.prixAchat = prixAchat;
        this.stock = stock;
    }
    
    public static ProduitBuilder builder() {
        return new ProduitBuilder();
    }
    
    public static ProduitBuilderV2 builderV2() {
        return new ProduitBuilderV2();
    }

    public static class ProduitBuilder {
        private Produit produit;

        public ProduitBuilder() {
            this.produit = new Produit();
        }

        public Produit build() {
            return this.produit;
        }

        public ProduitBuilder nom(String nom) {
            this.produit.nom = nom;

            return this;
        }

        public ProduitBuilder reference(String reference) {
            this.produit.reference = reference;

            return this;
        }
        
        public ProduitBuilder prix(String prix) {
            this.produit.prix = prix;

            return this;
        }
        
        public ProduitBuilder prixAchat(String prixAchat) {
            this.produit.prixAchat = prixAchat;

            return this;
        }

        public ProduitBuilder stock(String stock) {
            this.produit.stock = stock;

            return this;
        }
    }

    public static class ProduitBuilderV2 {
        private String nom;
        private String reference;
        private String prixAchat;
        private String prix;
        private String stock;

        public Produit build() {
            return new Produit(nom, reference, prix, prixAchat, stock);
        }

        public ProduitBuilderV2 nom(String nom) {
            this.nom = nom;

            return this;
        }

        public ProduitBuilderV2 reference(String reference) {
            this.reference = reference;

            return this;
        }
        
        public ProduitBuilderV2 prix(String prix) {
            this.prix = prix;

            return this;
        }
        
        public ProduitBuilderV2 prixAchat(String prixAchat) {
            this.prixAchat = prixAchat;

            return this;
        }

        public ProduitBuilderV2 stock(String stock) {
            this.stock = stock;

            return this;
        }
    }
}
