package model;

public class Contact {
    public String name;
    public int number;
    public String email;
    public String img;
    public Contact(String name, int number, String email, String img){
        this.name = name;
        this.number = number;
        this.email = email;
        this.img = img;
    }

    public int getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImg(String img) {
        this.img = img;
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
        return c.getName().equalsIgnoreCase(this.name);
    }
}
