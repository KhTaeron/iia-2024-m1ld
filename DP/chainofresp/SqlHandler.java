package chainofresp;

public class SqlHandler extends AbstractHandler {
    @Override
    public void handle(int id) {
        if (id >= 8 && id <= 14) {
            System.out.println("SQL !");
        }

        // else {
            super.handle(id);
        // }
    }
}
