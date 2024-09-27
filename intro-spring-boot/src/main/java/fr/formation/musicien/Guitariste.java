package fr.formation.musicien;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import fr.formation.instrument.Guitare;
import fr.formation.instrument.Instrument;
import fr.formation.instrument.Ukulele;

@Component
public class Guitariste {
    // private Guitare guitare = new Guitare();
    // private Ukulele ukulele = new Ukulele();
    // private Instrument instrument = new Ukulele();
    @Autowired // On demande à SPRING l'instrument qu'il connait
    @Qualifier("guitare")
    private Instrument instrument;

    public Guitariste() {
        System.out.println("CREATION GUITARISTE");
    }

    public void jouer() {
        System.out.println("Le guitariste joue ... " + this.instrument);
    }
}
