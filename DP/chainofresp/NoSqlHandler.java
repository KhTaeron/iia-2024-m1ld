package chainofresp;

public class NoSqlHandler extends AbstractHandler {
    @Override
    public void handle(int id) {
        if (id >= 0 && id <= 10) {
            System.out.println("NoSQL !");
        }

        // else {
            super.handle(id);
        // }
    }
}
