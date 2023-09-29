package model;

import java.util.Objects;

public class Contact {
    public String name;
    public int number;
    public Contact(String name, int number){
        this.name = name;
        this.number = number;
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

    public void settNumber(int number) {
        this.number = number;
    }

    public boolean equals(Object obj) {
        if(obj==null){return false;}
        if(!(obj instanceof Contact)){return false;}
        Contact c = (Contact)obj;
        return c.getName().equalsIgnoreCase(this.name) || c.getNumber() == this.number;
    }
    public int hashCode() {
        return Objects.hash(name,number);
    }
}
