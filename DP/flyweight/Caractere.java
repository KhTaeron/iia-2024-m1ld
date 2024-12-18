package flyweight;

public class Caractere {
    private Character codeClavier;
    private CaractereAttribut attribut;
    
    public Character getCodeClavier() {
        return codeClavier;
    }

    public void setCodeClavier(Character codeClavier) {
        this.codeClavier = codeClavier;
    }

    public CaractereAttribut getAttribut() {
        return attribut;
    }

    public void setAttribut(CaractereAttribut attribut) {
        this.attribut = attribut;
    }

    public Caractere(Character codeClavier, CaractereAttribut attribut) {
        this.codeClavier = codeClavier;
        this.attribut = attribut;
    }
}
