package mvc.Model;

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
import javax.swing.JList;
import mvc.Control.Controlador;
import mvc.View.Inicio;

/**
 *
 * @author Vespertino
 */

public class ListaContactos extends AbstractListModel<Contacto>{
    private String ruta;
    private Contacto c;
    private List<Contacto>listaOriginal;  //listaRaiz
    private File f;
    
    public ListaContactos(String r){
        listaOriginal = new ArrayList<>();
        this.ruta=r;
        readLista();
    }
    public List<Contacto> getListaO() {
        return listaOriginal;
    }
    
    public void readLista(){
        f = new File(this.ruta);
        try {
            if(f.exists()){
                BufferedReader fich = new BufferedReader(new FileReader(f));
                String linea=fich.readLine();
                while(linea!= null){
                    String[] lineaPalabras = linea.split(":");
                    getListaO().add(c = new Contacto(lineaPalabras[0].trim(),Integer.parseInt(lineaPalabras[1].trim())));
                    linea=fich.readLine();
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void addContacto(Contacto c){
        if(!listaOriginal.contains(c)){
            getListaO().add(c);
            rewriteFile(getListaO());
        }
    }
    
    public void deleleteContacto(int i){
        getListaO().remove(i);
    }
    public void editContacto(String nom,int num){
        c.setName(nom);
        c.setNumber(num);
    }
    
    private void rewriteFile(List<Contacto> list){
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(new File("F:\\DAM\\DAM_2\\ACCESO_DATOS\\Contactos2.txt")));
            output.writeObject(list);
            output.close();
        } catch (IOException e) {
            System.out.println("ERROR I salida " + e.getMessage());;
        }
    }
    
    //Metodos getSize y getElementAt -> AbstractListModel<Contacto>
    public int getSize() {
        return getListaO().size();
    }
    public Contacto getElementAt(int index) {
        Contacto c = getListaO().get(index);
        return c;
    }
}
