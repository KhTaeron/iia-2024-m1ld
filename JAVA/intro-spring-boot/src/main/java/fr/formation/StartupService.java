package fr.formation;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import fr.formation.musicien.Guitariste;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StartupService implements CommandLineRunner {
    // @Autowired
    private final Guitariste guitariste;

    // public StartupService(Guitariste guitariste) {
    //     this.guitariste = guitariste;
    // }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Hello world!");

        this.guitariste.jouer();
    }
}
