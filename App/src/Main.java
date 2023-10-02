import controller.Control;
import view.Vista;

public class Main {
    public static void main(String[] args) {
        Vista view = new Vista(new Control("H:\\DAM2023\\ACCESODATOS\\3.Contactos\\files\\contacts.txt"));
        view.ejecutar();
    }
}