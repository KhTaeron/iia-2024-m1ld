package flyweight;

public class CaractereAttribut {
    private int couleur;
    private int taille;
    private int police;

    public int getCouleur() {
        return couleur;
    }

    public void setCouleur(int couleur) {
        this.couleur = couleur;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getPolice() {
        return police;
    }

    public void setPolice(int police) {
        this.police = police;
    }

    public CaractereAttribut(int couleur, int taille, int police) {
        this.couleur = couleur;
        this.taille = taille;
        this.police = police;
    }
    
}
