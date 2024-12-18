package proxy;

public class ProxyApplication {
    public static void main(String[] args) {
        ProduitRepository repository = RepositoryFactory.creaProduitRepository();

        repository.findAll();
        repository.findAll();
        repository.findAll();
    }

    public static void demo() {
        ProduitRepository repository = RepositoryFactory.creaProduitRepository();

        repository.findAll();
    }
}
