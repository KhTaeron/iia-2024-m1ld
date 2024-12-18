package observer;

public class RepositoryFactory {
    private RepositoryFactory() { }

    public static ProduitRepository creaProduitRepository() {
        ProduitRepository repository = null;

        if (Config.NOSQL == true) {
            ProduitRepositoryNoSql nosql = new ProduitRepositoryNoSql();

            nosql.subscribe(new JournalSubscriber());
            nosql.subscribe(new MailSubscriber());

            repository = nosql;
        }

        else {
            repository = new ProduitRepositorySql();
        }

        return repository;
    }
}
