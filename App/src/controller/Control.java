package controller;

import java.io.*;

public class Control {
    public void alta(){
        
    }
    public String readFile(File file){
        try{
            BufferedReader input = new BufferedReader(new FileReader(file));
            StringBuilder txt = new StringBuilder();
            String line = input.readLine();
            while(line!=null){
                txt.append(line);
                txt.append("\n");
                line = input.readLine();
            }
            input.close();
            return txt.toString();
        }
        catch(FileNotFoundException fEx){
            System.out.println("ERROR  F " + fEx.getMessage());
            return null;
        }
        catch(IOException ioEx){
            System.out.println("ERROR  I " + ioEx.getMessage());
            return null;
        }
    }
    public void rewriteFile(File file, String newTxt){
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(newTxt);
            fw.close();
        } catch (IOException e) {
            System.out.println("ERROR I " + e.getMessage());;
        }
    }
}
