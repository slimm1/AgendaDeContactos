import java.io.*;
import java.util.*;
import java.io.FileOutputStream;

public class Modelo {
    private File fichero;
    private Set<Contacto> contactos;

    public Modelo(String ruta) {
        contactos = new TreeSet<>();
        fichero = new File(ruta);

        if (readObject(fichero.getAbsolutePath()) != null) {
            contactos.addAll(readObject(fichero.getAbsolutePath()));
        }
    }

    public File getFichero() {
        return fichero;
    }

    public boolean cargar(String nombreContacto, int numeroTelefono) {
        if (String.valueOf(numeroTelefono).length() == 9 && (String.valueOf(numeroTelefono).matches("[0-9]+"))) {
            contactos.add(new Contacto(nombreContacto, numeroTelefono));
            return true;
        } else {
            return false;
        }
    }

    public boolean actualizar(String nombre, int opc, String nuevo) {
        if (opc == 1) {
            for (Contacto contacto : contactos) {
                if (contacto.getNombre().equals(nombre)) {
                    contacto.setNombre(nuevo);
                    System.out.println("Contacto encontrado. actualizado exictamente.");
                    return true;
                } else System.out.println("Contacto no encontrado..");
                return false;
            }
        }
        if (opc == 2) {
            for (Contacto contacto : contactos) {
                if (contacto.getNombre().equals(nombre)) {
                    contacto.setTelefono(Integer.parseInt(nuevo));
                    System.out.println("Contacto encontrado. actualizado exictamente.");
                    return true;
                } else System.out.println("Contacto no encontrado..");
                return false;
            }
        }
        System.out.println("Los datos son incorrectos");
        return false;
    }

    public boolean guardar(File fichero) {

        try {
            if (fichero.exists()) {
                fichero.delete();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero));

            oos.writeObject(contactos);
            oos.close();
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
            return false;
        }
        return true;
    }

    public TreeSet<Contacto> readObject(String ruta) {
        TreeSet<Contacto> lista = new TreeSet<>();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta));
            lista = (TreeSet<Contacto>) ois.readObject();
            ois.close();
            return lista;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    public boolean borrar(String nombre) {
        Iterator<Contacto> iterator = contactos.iterator();
        while (iterator.hasNext()) {
            Contacto contacto = iterator.next();
            if (nombre.equalsIgnoreCase(contacto.getNombre()) && !(contacto.getNombre().equals(null))) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public StringBuilder listar() {
        StringBuilder sb = new StringBuilder();
        System.out.println("Lista de contactos:");
        for (Contacto contacto : contactos) {
            sb.append(contacto);
            sb.append("\n");
        }
        return sb;
    }


}

