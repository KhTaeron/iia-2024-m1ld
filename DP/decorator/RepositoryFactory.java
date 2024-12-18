package decorator;

public class RepositoryFactory {
    private RepositoryFactory() { }

    public static ProduitRepository creaProduitRepository() {
        ProduitRepository repository = null;

        if (Config.NOSQL == true) {
            repository = new ProduitRepositoryNoSql();
        }

        else {
            repository = new ProduitRepositorySql();
        }

        repository = new MailDecorator(repository);
        repository = new JournalDecorator(repository);

        return repository;
    }
}
