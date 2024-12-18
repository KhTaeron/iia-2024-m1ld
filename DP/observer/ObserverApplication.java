package observer;

public class ObserverApplication {
    public static void main(String[] args) {
        ProduitRepository repository = RepositoryFactory.creaProduitRepository();

        repository.findAll();
    }

    public static void demo() {
        ProduitRepository repository = RepositoryFactory.creaProduitRepository();

        repository.findAll();
    }
}
