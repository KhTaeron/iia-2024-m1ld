package proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProduitRepositoryNoSql implements ProduitRepository {
    @Override
    public List<String> findAll() {
        System.out.println("Find all NoSQL ...");
        return new ArrayList<>();
    }

    @Override
    public Optional<String> findById(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public String save(String produit) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
    
}
