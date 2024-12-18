package chainofresp;

public abstract class AbstractHandler implements Handler {
    private Handler hdlr;

    @Override
    public Handler chain(Handler next) {
        this.hdlr = next;

        return next;    
    }

    @Override
    public void handle(int id) {
        if (this.hdlr != null) {
            this.hdlr.handle(id);
        }
    }
}
