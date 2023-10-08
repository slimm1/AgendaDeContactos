import java.io.Serializable;

public class Contacto implements Serializable,Comparable<Contacto> {
    private String nombre;
    private int telefono;

    public Contacto(String nombre, int telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) {
            return false; // No son de la misma clase
        }

        Contacto otroContacto = (Contacto) o;

        if (otroContacto.getNombre().equals(nombre)) {


            return true; // Los objetos son iguales

        } else return false;
    }

    @Override

    public String toString() {
        return "Nombre :" + nombre + ", Telefono :" + telefono;
    }


    public int compareTo(Contacto otroContacto) {
        return this.nombre.compareTo(otroContacto.nombre);
    }


}
