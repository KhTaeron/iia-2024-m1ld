package observer;

// Observés
public interface Publisher {
    public void subscribe(Subscriber sub);
    public void unsubscribe(Subscriber sub);
    public void refresh();
}
