package mvc.Model;

/**
 *
 * @author Vespertino
 */
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Contacto implements Comparable<Contacto>, Serializable {
    public String name;
    public int number;
    public Contacto(String name, int number){
        this.name = name;
        this.number = number;
    }
    public Contacto(String name){
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean equals(Object obj) {
        if(obj==null){return false;}
        if(!(obj instanceof Contacto)){return false;}
        Contacto c = (Contacto)obj;
        return c.getName().equalsIgnoreCase(this.name);
    }
    public int hashCode() {
        return Objects.hash(name);
    }
    public int compareTo(Contacto c) {
        return name.compareTo(c.getName());
    }
    public String toString() {
        return this.name + " --> " + number;
    }
}
