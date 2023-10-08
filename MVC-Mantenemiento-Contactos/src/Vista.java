import javax.swing.*;
import java.awt.*;
public class Vista extends JFrame {
    private JPanel panel;
    private JButton btnAñadir;
    private JButton btnActualizar;
    private JButton btnListar;
    private JButton btnBorrar;
    private JButton btnGuardar;
    private JButton btnSalir;

    public Vista() {
        setSize(500, 300);
        setTitle("Mantenemiento de contactos");
        setLocationRelativeTo(null);
        colocarBotones();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void colocarBotones() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 500, 200);
        panel.setBackground(Color.lightGray);
        this.getContentPane().add(panel);
        btnAñadir = new JButton("Añadir contacto");
        btnActualizar = new JButton("Actualizar contacto");
        btnListar = new JButton("Listar contactos");
        btnBorrar = new JButton("Borrar contacto");
        btnGuardar = new JButton("Guardar contacto");
        btnSalir = new JButton("Salir");
        btnAñadir.setBounds(20, 20, 150, 30);
        panel.add(btnAñadir);
        btnActualizar.setBounds(20, 60, 150, 30);
        panel.add(btnActualizar);
        btnListar.setBounds(20, 100, 150, 30);
        panel.add(btnListar);
        btnBorrar.setBounds(20, 140, 150, 30);
        panel.add(btnBorrar);
        btnGuardar.setBounds(20, 180, 150, 30);
        panel.add(btnGuardar);
        btnSalir.setBounds(20, 220, 150, 30);
        panel.add(btnSalir);
    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton getBtnAñadir() {
        return btnAñadir;
    }

    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    public JButton getBtnListar() {
        return btnListar;
    }

    public JButton getBtnBorrar() {
        return btnBorrar;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }
}