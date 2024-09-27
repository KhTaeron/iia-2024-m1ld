package fr.formation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import fr.formation.musicien.Guitariste;

@Component
public class StartupService implements CommandLineRunner {
    @Autowired
    private Guitariste guitariste;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello world!");

        this.guitariste.jouer();
    }
}
