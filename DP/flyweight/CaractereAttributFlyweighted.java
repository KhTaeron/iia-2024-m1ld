package flyweight;

import java.util.ArrayList;
import java.util.List;

public class CaractereAttributFlyweighted {
    private List<CaractereAttribut> sharedAttributes = new ArrayList<>();

    public CaractereAttribut getCaractereAttribut(int couleur, int taille, int police) {
        CaractereAttribut sharedAttribute = null;

        for (CaractereAttribut sa : this.sharedAttributes) {
            if (sa.getCouleur() == couleur && sa.getTaille() == taille && sa.getPolice() == police) {
                sharedAttribute = sa;
                break;
            }
        }

        if (sharedAttribute == null) {
            sharedAttribute = new CaractereAttribut(couleur, taille, police);
            this.sharedAttributes.add(sharedAttribute);
        }

        return sharedAttribute;
    }
    
    // Pour comparer ...
    public CaractereAttribut getCaractereAttributNoFlyweight(int couleur, int taille, int police) {
        return new CaractereAttribut(couleur, taille, police);
    }
}
