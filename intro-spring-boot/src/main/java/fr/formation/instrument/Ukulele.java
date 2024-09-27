package fr.formation.instrument;

import org.springframework.stereotype.Component;

@Component
public class Ukulele implements Instrument {
    @Override
    public String toString() {
        return "YUK YUK YUK";
    }
}
