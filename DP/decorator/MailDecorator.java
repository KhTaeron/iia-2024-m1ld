package decorator;

import java.util.List;
import java.util.Optional;

public class MailDecorator implements ProduitRepository {
    private final ProduitRepository repository;

    public MailDecorator(ProduitRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<String> findAll() {
        System.out.println("Le mail va s'envoyer ...");
        return this.repository.findAll();
    }
    
    @Override
    public Optional<String> findById(int id) {
        return this.repository.findById(id);
    }
    
    @Override
    public String save(String produit) {
        return this.repository.save(produit);
    }
}
