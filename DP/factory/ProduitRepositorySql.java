package factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProduitRepositorySql implements ProduitRepository {
    @Override
    public List<String> findAll() {
        System.out.println("Find all SQL ...");
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
