package observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProduitRepositoryNoSql implements ProduitRepository, Publisher {
    private List<Subscriber> subs = new ArrayList<>();

    @Override
    public List<String> findAll() {
        System.out.println("Find all NoSQL ...");

        this.refresh(); // Notifie tous les observateurs !

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

    @Override
    public void subscribe(Subscriber sub) {
        this.subs.add(sub);
    }
    
    @Override
    public void unsubscribe(Subscriber sub) {
        this.subs.remove(sub);
    }

    @Override
    public void refresh() {
        for (Subscriber sub : this.subs) {
            sub.update(this);
        }
    }
}
