package chainofresp;

public interface Handler {
    public Handler chain(Handler next);
    public void handle(int id);
}
