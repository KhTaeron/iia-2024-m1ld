package observer;

public class MailSubscriber implements Subscriber {
    @Override
    public void update(Publisher pub) {
        System.out.println("Le mail va s'envoyer ...");
    }
}
