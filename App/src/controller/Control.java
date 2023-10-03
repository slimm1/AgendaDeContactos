package controller;

import model.Contact;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;
public class Control {
    private TreeSet<Contact> bufferList, original;
    private final File inputFile;
    public Control(String path){
        inputFile = checkRouting(new File(path));
        if(readFile()==null){
            System.out.println("new set initialized");
            bufferList = new TreeSet<>();
            original = new TreeSet<>();
        }
        else{
            bufferList = readFile();
            original = readFile();
        }
    }
    public void addContact(String name, int number){
        if(getContactByName(name) == null){
            bufferList.add(new Contact(name, number));
            System.out.println("Contacto " + name + " creado con éxito!");
        }
        else{
            System.out.println("Ya hay un contacto registrado con el nombre --> " + name);
        }
    }
    public void removeContact(String name){
        Contact c = getContactByName(name);
        if(c != null){
            bufferList.remove(c);
            System.out.println("Contacto " + name + " borrado con éxito!");
        }
        else{
            System.out.println("El contacto " + name + " no existe en el sistema!");
        }
    }
    public boolean modifyName(String name, String newName){
        Contact c = getContactByName(name);
        if(c != null){
            if(c.getName().equalsIgnoreCase(newName)){
                System.out.println("El nombre introducido coincide con el registrado");
                return false;
            }
            else{
                c.setName(newName);
                System.out.println("El nombre ha sido modificado con éxito");
                return true;
            }
        }
        return false;
    }
    public boolean modifyNumber(String name, int newNumber){
        Contact c = getContactByName(name);
        if(c != null){
            if(c.getNumber()==newNumber){
                System.out.println("El teléfono introducido coincide con el registrado");
                return false;
            }
            else{
                c.setNumber(newNumber);
                System.out.println("El teléfono ha sido modificado con éxito");
                return true;
            }
        }
        return false;
    }
    public String listContacts(){
        StringBuilder out = new StringBuilder();
        for(Contact c: bufferList){
            out.append(c.toString());
            out.append(System.getProperty("line.separator"));
        }
        return out.toString();
    }
    public void saveChanges(){
        if(bufferList.equals(original)){
            System.out.println("No hay cambios que guardar, ADIOS");
        }
        else{
            System.out.println(new File("../files/versions").mkdir()?"created new versions dir":"versions dir already exists");
            rewriteFile(new File("../files/versions/"+getDateTime()), original);
            rewriteFile(new File(inputFile.getPath()), bufferList);
            System.out.println("Los cambios se han guardado, ADIOS");
        }
    }
    private Contact getContactByName(String name){
        for(Contact c:bufferList){
            if(c.getName().equalsIgnoreCase(name)){
                return c;
            }
        }
        return null;
    }
    private String getDateTime(){
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
    private File checkRouting(File routing){
        if(!routing.exists()){
            routing = new File("../files/contacts.txt");
            try {
                System.out.println("Ruta de entrada de datos extraña. Intentando crear fichero en --> " + routing.getPath());
                System.out.println(routing.createNewFile()?"Un fichero vacío se ha creado con éxito": "Ya existe un fichero contacts.txt!!\nDatos cargados");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return routing;
        }
        return routing;
    }
}
