package mvc;
import mvc.View.*;
import mvc.Control.*;
import mvc.Model.*;
/**
 *
 * @author Vespertino
 */

public class Main {
    public static void main(String[] args) {
        String ruta = "F:\\DAM\\DAM_2\\ACCESO_DATOS\\Contactos.txt"; // F,H
        ListaContactos m = new ListaContactos(ruta);
        Controlador c = new Controlador(m);
        Inicio v = new Inicio(c); 
        v.setVisible(true);
    }
}
