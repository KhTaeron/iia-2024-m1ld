package fr.formation.instrument;

import org.springframework.stereotype.Component;

@Component
public class Guitare implements Instrument {
    public Guitare() {
        System.out.println("CREATION GUITARE");
    }

    @Override
    public String toString() {
        return "GLINK GLINK GLINK";
    }
}
