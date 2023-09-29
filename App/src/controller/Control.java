package controller;

import com.sun.security.jgss.GSSUtil;
import model.Contact;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;
public class Control {
    private TreeSet<Contact> bufferList, original;
    private final File inputFile = new File("./../files/contacts.txt");
    public Control(){
        if(readFile()==null){
            System.out.println("new set initializated");
            bufferList = new TreeSet<>();
            original = new TreeSet<>();
        }
        else{
            bufferList = readFile();
            original = readFile();
        }
    }
    public void addContact(String name, int number){
        bufferList.add(new Contact(name, number));
    }
    public void removeContact(String name){
        Contact c = getContactByName(name);
        if(c != null){
            bufferList.remove(c);
        }
        else{
            System.out.println("El contacto introducido no figura en la lista!");
        }
    }
    public void modifyContact(String name, String newName, int newNumber){
        Contact c = getContactByName(name);
        if(c != null){
            c.setName(newName);
            c.setNumber(newNumber);
        }
        else{
            System.out.println("El contacto introducido no figura en la lista!");
        }
    }
    public String listContacts(){
        StringBuilder out = new StringBuilder();
        for(Contact c: bufferList){
            out.append(c.toString());
            out.append(System.getProperty("line.separator"));
        }
        return out.toString();
    }
    //revisar routing de ficheros
    public void saveChanges(){
        System.out.println(new File("../files/versions").mkdir()?"created new versions dir":"versions dir already exists");
        rewriteFile(new File("../files/versions/"+getDateTime()+inputFile.getName()), original);
        rewriteFile(new File(inputFile.getPath()), bufferList);
    }
    private Contact getContactByName(String name){
        for(Contact c:bufferList){
            if(c.getName().equalsIgnoreCase(name)){
                return c;
            }
        }
        return null;
    }
    public String getDateTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddLLLuuuu-HH.mm.ss"));
    }
    private TreeSet<Contact> readFile(){
        TreeSet <Contact> contactList;
        try{
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(inputFile));
            contactList = (TreeSet<Contact>) input.readObject();
            input.close();
            return contactList;
        }
        catch(FileNotFoundException fEx){
            System.out.println("ERROR  F " + fEx.getMessage());
            return null;
        }
        catch(IOException ioEx){
            System.out.println("ERROR  I entrada " + ioEx.getMessage());
            return null;
        }
        catch(ClassNotFoundException cEx){
            System.out.println("ERROR C " + cEx.getMessage());
            return null;
        }
    }
    private void rewriteFile(File file, TreeSet<Contact> contactList){
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
            output.writeObject(contactList);
            output.close();
        } catch (IOException e) {
            System.out.println("ERROR I salida " + e.getMessage());;
        }
    }
}
