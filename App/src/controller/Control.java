package controller;

import model.Contact;

import java.io.*;
import java.lang.reflect.Array;
import java.util.HashSet;

public class Control {
    public void alta(){

    }
    public HashSet<Contact> readFile(File file){
        try{
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
            HashSet <Contact> contactList = new HashSet<>();
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
    public void rewriteFile(File file, HashSet<Contact> contactList){
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
