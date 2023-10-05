package mvc.Control;
import mvc.Model.*;
import mvc.View.*;

import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;

/**
 *
 * @author PC_HOME
 */
public class Controlador{
    
    private Contacto c;
    private ListaContactos mod;
    //private ArrayList<Contacto> listaT; //listaTemporal
    
    public Controlador(ListaContactos l){
        mod = l;
    }
    
    public ListaContactos getMod() {
        return mod;
    }
    
    public void crear(JTextField s,JTextField i){
        String nom = s.getText();
        int num = Integer.parseInt(i.getText());
        if(num>0){
            getMod().addContacto(new Contacto(nom,num));
        }else if(num<=0){
            getMod().addContacto(new Contacto(nom));
        }
        
    }
    
    public void actualizarLista(){
        getMod().getListaO();
    }
    
    public void eliminar(JList l){
        getMod().deleleteContacto(l.getSelectedIndex());
    }
    public int indexOfeliminar(JList l){
        int index = l.getSelectedIndex();
        return index;
    }
    
    
    public void editar(JTextField nom, JTextField num){
        getMod().editContacto(nom.getText(), Integer.parseInt(num.getText()));
    }
    public String nombreByIndex(int i){
        return getMod().getElementAt(i).getName();
    }
    public String numeroByIndex(int i){
        return String.valueOf(getMod().getElementAt(i).getNumber());
    }
    
    
    
    private void rewriteFile(File file, TreeSet<Contacto> contactList){
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
            output.writeObject(contactList);
            output.close();
        } catch (IOException e) {
            System.out.println("ERROR I salida " + e.getMessage());;
        }
    }
}
