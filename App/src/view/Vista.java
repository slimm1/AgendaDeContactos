package view;

import controller.Control;

import java.util.Scanner;
public class Vista {

    private Control controlador;
    private Scanner scanner;
    public Vista(Control controlador){
        this.controlador=controlador;
        scanner = new Scanner(System.in);
    }
    public void menu(){
        System.out.println("1. Agregar contacto");
        System.out.println("2. Actualizar contacto");
        System.out.println("3. Eliminar contacto");
        System.out.println("4. Listar contacto");
        System.out.println("5. Salir y guardar cambios");
        System.out.println("6. Salir sin guardar cambios");
    }
    public void ejecutar(){
        int opcion;
        do{
            menu();
            System.out.println("Elija una opción");
            opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion){
                case 1:
                    System.out.println("Introduce el nombre del nuevo contacto: ");
                    String nombre = scanner.nextLine();
                    System.out.println("Introduce el numero de telefono del nuevo contacto: ");
                    int numero = Integer.parseInt(scanner.nextLine());
                    controlador.addContact(nombre,numero);
                    break;
                case 2:
                    int answer;
                    System.out.println("Introduce el nombre del contacto!!:");
                    String nombreActualizar = scanner.nextLine();
                    System.out.println("¿Qué quieres modificar?:");
                    System.out.println("1.- nombre");
                    System.out.println("2.- telefono");
                    answer = Integer.parseInt(scanner.nextLine());
                    if(answer==1){
                        System.out.println("Introduce el nuevo nombre:");
                        String newName = scanner.nextLine();
                        controlador.modifyName(nombreActualizar,newName);
                    }
                    else if(answer == 2){
                        System.out.println("Introduce el nuevo telefono:");
                        int newNumber = Integer.parseInt(scanner.nextLine());
                        controlador.modifyNumber(nombreActualizar,newNumber);
                    }
                    break;
                case 3:
                    System.out.println("Introduce el nombre del contacto para borrarlo !");
                    String nombreBorrar = scanner.nextLine();
                    controlador.removeContact(nombreBorrar);
                    break;
                case 4:
                    System.out.println(controlador.listContacts());
                    break;
                case 5:
                    System.out.println("Los cambios se han guardado, ADIOS");
                    controlador.saveChanges();
                    break;
                case 6:
                    System.out.println("ADIOS");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }while (opcion<5);
    }
}