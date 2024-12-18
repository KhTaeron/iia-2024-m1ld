package proxy;

import java.util.List;
import java.util.Optional;

public class ProduitRepositoryProxy implements ProduitRepository {
    private final ProduitRepository repository;
    private List<String> produits;

    public ProduitRepositoryProxy(ProduitRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<String> findAll() {
        if (this.produits == null) {
            this.produits = this.repository.findAll();
        }

        return this.produits;
    }

    @Override
    public Optional<String> findById(int id) {
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public String save(String produit) {
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
}
