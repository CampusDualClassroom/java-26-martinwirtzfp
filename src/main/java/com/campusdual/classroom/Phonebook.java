package com.campusdual.classroom;

import com.campusdual.util.Utils;
import jdk.jshell.execution.Util;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Phonebook {
    private Map<String, Contact> agenda;

    public Phonebook() {
        this.agenda = new TreeMap<>();
    }

    public Map<String, Contact> getData() {
        return agenda;
    }

    //p.showPhonebook()
    public void addContact(Contact c){
        //Clave del contacto
        String clave = c.getCode();
        //Añadimos el contacto
        this.agenda.put(clave, c);
    };

    public void showPhonebook(){
        for (Map.Entry<String, Contact> entry: agenda.entrySet()){
            entry.getValue().showContactDetails();
        }
    }

    public boolean deleteContact(String c){ //Recibe como parámetro el código de un contacto
        //Primero comprobamos si el contacto existe
        boolean match = false;
        for(Map.Entry<String, Contact> entry: this.agenda.entrySet()){
            if (Objects.equals(entry.getKey(), c)){
                match = true;
                break;
            }
        }

        //Si existe lo borramos
        if (match){
            this.agenda.remove(c);
        }
        return match;
    }

    public Contact buscarContacto(String c){
        Contact contact = null;
        for(Map.Entry<String, Contact> entry: this.agenda.entrySet()){
            if (Objects.equals(entry.getKey(), c)){
                contact = entry.getValue();
                break;
            }
        }
        return contact;
    }

    public static void ejecutable(Phonebook p){
        int option = 0;
        do{
            System.out.println("Selecciona una opcion: ");
            System.out.println("1. Añadir contacto");
            System.out.println("2. Mostrar contactos");
            System.out.println("3. Opciones de contacto");
            System.out.println("4. Eliminar un contacto");
            System.out.println("5.  Salir");
            option = Utils.integer();
            switch (option){
                case 1:{
                    String nombre;
                    String apellido;
                    String numero;
                    System.out.println();
                    nombre = Utils.string("Introduce el nombre");
                    apellido = Utils.string("Introduce el apllido o apellidos");
                    numero = Utils.string("Introduce el numero de telefono");
                    Contact contact = new Contact(nombre, apellido, numero);

                    p.addContact(contact);

                    System.out.println("Contacto añadido correctamente");
                    break;
                }
                case 2:{
                    System.out.println("Lista de contactos");
                    p.showPhonebook();
                    break;
                }
                case 3:{
                    String codigo;
                    codigo = Utils.string("Introduce un codigo de contacto");
                    Contact contact = p.buscarContacto(codigo);
                    int contactOption = 0;
                    System.out.println("Selecciona una opcion");
                    System.out.println("1. Call my number");
                    System.out.println("2. Call other number");
                    System.out.println("3. Show contact details");
                    contactOption = Utils.integer();
                    switch (contactOption){
                        case 1:{
                            contact.callMyNumber();
                        }
                        case 2:{
                            String codtocall;
                            codtocall = Utils.string("Introduce el numero del contacto para llamarlo");
                            contact.callOtherNumber(codtocall);
                        }
                        case 3:{
                            contact.showContactDetails();
                        }
                    }
                    break;

                }
                case 4:{
                    String cod;
                    cod = Utils.string("Introduce el codigo del contacto a borrar: ");
                    if (p.deleteContact(cod)){
                        System.out.println("Contatco eliminado con exito");
                    } else {
                        System.out.println("El contacto introducido no existe");
                    }
                }
                case 5:{
                    System.out.println("Finalizando el programa...");
                }
            }
        } while (option != 5);
    }



}
