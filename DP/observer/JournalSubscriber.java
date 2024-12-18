package observer;

public class JournalSubscriber implements Subscriber {
    @Override
    public void update(Publisher pub) {
        System.out.println("Journalisation ...");
    }
}
