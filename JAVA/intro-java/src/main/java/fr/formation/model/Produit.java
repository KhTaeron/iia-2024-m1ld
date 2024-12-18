package fr.formation.model;

import fr.formation.iface.InterfaceA;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Produit implements InterfaceA {
    private String id;
    private String name;
    @Override
    public void addition(int a, int b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addition'");
    }
}
