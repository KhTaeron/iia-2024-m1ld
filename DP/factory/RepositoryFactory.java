package factory;

public class RepositoryFactory {
    private RepositoryFactory() { }

    public static ProduitRepository creaProduitRepository() {
        if (Config.NOSQL == true) {
            return new ProduitRepositoryNoSql();
        }

        return new ProduitRepositorySql();
    }
}
