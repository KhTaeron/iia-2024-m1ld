package chainofresp;

public class ChainOfRespApplication {
    public static void main(String[] args) {
        Handler firstHdlr = new NoSqlHandler();

        firstHdlr
            .chain(new SqlHandler())
            // .chain(...)
            // .chain(...)
            // .chain(...)
            // .chain(...)
            // .chain(...)
        ;

        firstHdlr.handle(1);
        firstHdlr.handle(14);
        firstHdlr.handle(12);
        firstHdlr.handle(9);
    }
}
