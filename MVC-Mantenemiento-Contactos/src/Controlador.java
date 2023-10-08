import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Controlador implements ActionListener {
    private Modelo modelo;
    private Vista vista;


    public Controlador(Modelo modelo, Vista Vista) {
        this.modelo = modelo;
        this.vista = vista;
        vista = new Vista();
        this.vista.getBtnAñadir().addActionListener(this);
        this.vista.getBtnActualizar().addActionListener(this);
        this.vista.getBtnListar().addActionListener(this);
        this.vista.getBtnGuardar().addActionListener(this);
        this.vista.getBtnBorrar().addActionListener(this);
        this.vista.getBtnSalir().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        String nuevo = "";
        if (obj == this.vista.getBtnAñadir()) {
            String nombre = JOptionPane.showInputDialog("Introduce el nombre");
            int telefono = Integer.parseInt(JOptionPane.showInputDialog("Introduce el telefono"));
            Boolean boleano = modelo.cargar(nombre, telefono);
            if (boleano == true) {
                JOptionPane.showMessageDialog(null, "Los datos estan cargando con exito");
            } else
                JOptionPane.showMessageDialog(null, "Los datos son incorrectos entrar de nuevo");
        } else if (obj == this.vista.getBtnActualizar()) {
            String nombre = JOptionPane.showInputDialog("Dami el nombre del contacto para actualizar");
            int opc = Integer.parseInt(JOptionPane.showInputDialog("Pulsa 1 para actualizar nombre/n pulsa 2 para actualizar telefono"));
            if (opc == 1) {
                nuevo = JOptionPane.showInputDialog("Introduce el nuevo nombre");
            } else if (opc == 2) {
                nuevo = JOptionPane.showInputDialog("Introduce el nuevo telefono");
            }
            Boolean bol = modelo.actualizar(nombre, opc, nuevo);
            if (bol == true) {
                JOptionPane.showMessageDialog(null, "la actualizacion se ha hecho con éxito");
            } else
                JOptionPane.showMessageDialog(null, "Error entrar de nuevo");
        } else if (obj == this.vista.getBtnListar()) {

            JOptionPane.showMessageDialog(null, modelo.listar().toString());
        } else if (obj == this.vista.getBtnBorrar()) {
            String nombre = JOptionPane.showInputDialog("Introduce el nombre que quieres a borrar");
            if (modelo.borrar(nombre) == true) {
                JOptionPane.showMessageDialog(null, "El contacto esta elimenado con exito");
            } else {
                JOptionPane.showMessageDialog(null, "El contacto no encontrado");
            }
        } else if (obj == this.vista.getBtnGuardar()) {
            if (modelo.guardar(modelo.getFichero()) == true) {
                JOptionPane.showMessageDialog(null, "Los contactos estan guardado con exito");
            } else
                JOptionPane.showMessageDialog(null, "Error");

        } else if (obj == this.vista.getBtnSalir()) {

            JOptionPane.showMessageDialog(null,"adiós");
            System.exit(0);

        }
    }

    public static void main(String[] args) {
        Controlador controlador = new Controlador(new Modelo("C:\\Users\\salah\\IdeaProjects\\MVC-Mantenemiento-Contactos\\src\\fich"), new Vista());

    }
}
