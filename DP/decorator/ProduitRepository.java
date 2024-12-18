package decorator;

import java.util.List;
import java.util.Optional;

public interface ProduitRepository {
    public List<String> findAll();
    public Optional<String> findById(int id);
    public String save(String produit);

    // etc.
}
