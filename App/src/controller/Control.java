package controller;

import model.Contact;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;
public class Control {
    private final TreeSet<Contact> bufferList, original;
    private final File inputFile;
    public Control(String routing){
        inputFile = new File(routing);
        bufferList = readFile();
        original = readFile();
    }
    private void addContact(String name, int number){
        bufferList.add(new Contact(name, number));
    }
    private void removeContact(String name){
        Contact c = getContactByName(name);
        if(c != null){
            bufferList.remove(c);
        }
        else{
            System.out.println("El contacto introducido no figura en la lista!");
        }
    }
    private void modifyContact(String name, String newName, int newNumber){
        Contact c = getContactByName(name);
        if(c != null){
            c.setName(newName);
            c.setNumber(newNumber);
        }
        else{
            System.out.println("El contacto introducido no figura en la lista!");
        }
    }
    private String listContacts(){
        StringBuilder out = new StringBuilder();
        for(Contact c: bufferList){
            out.append(c.toString());
            out.append(System.getProperty("line.separator"));
        }
        return out.toString();
    }
    private void saveChanges(){
        System.out.println(new File("../versions").mkdir()?"created new versions dir":"versions dir already exists");
        rewriteFile(new File("../versions/"+getDateTime()+inputFile.getName()), original);
        rewriteFile(new File(inputFile.getPath()), bufferList);
    }
    private Contact getContactByName(String name){
        for(Contact c: bufferList){
            if(c.getName().equalsIgnoreCase(name)){
                return c;
            }
        }
        return null;
    }
    public String getDateTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddLLLuuuu-HHmmss"));
    }
    private TreeSet<Contact> readFile(){
        try{
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(inputFile));
            TreeSet <Contact> contactList = new TreeSet<>();
            Contact newContact = (Contact) input.readObject();
            while(newContact!=null){
                contactList.add(newContact);
                newContact = (Contact) input.readObject();
            }
            input.close();
            return contactList;
        }
        catch(FileNotFoundException fEx){
            System.out.println("ERROR  F " + fEx.getMessage());
            return null;
        }
        catch(IOException ioEx){
            System.out.println("ERROR  I " + ioEx.getMessage());
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
            for(Contact c:contactList){
                output.writeObject(c);
            }
            output.close();
        } catch (IOException e) {
            System.out.println("ERROR I " + e.getMessage());;
        }
    }
}
