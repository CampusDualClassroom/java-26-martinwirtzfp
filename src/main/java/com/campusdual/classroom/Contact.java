package com.campusdual.classroom;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class Contact implements ICallActions{
    private String name;
    private String surname1;
    private String phone;
    private String code;

    public Contact(String name, String surname1, String phone){
        this.name = name;
        this.surname1 = surname1;
        this.phone = phone;
        this.code = contactCodeGenerator();
    }

    private String contactCodeGenerator(){
        //Primero tratamos el nombre
        char nombre = this.name.toLowerCase().charAt(0);
        //Le pasamos el apellido a la funcion charCount para que nos pase el numero de espacios
        int spaces = charCount(this.surname1, ' ');
        String apellido;
        //Actuamos en función del número de espacios
        if(spaces == 0){
            apellido = normalize(this.surname1);
        } else {
            int ruptura = 0;
            ruptura = this.surname1.indexOf(' ');
            apellido = this.surname1.substring(0,1);
            apellido += this.surname1.substring(ruptura+1);
            //Lo normalizamos
            apellido = normalize(apellido);
            //Eliminamos sus espacios
            apellido = removeSpaces(apellido);
        }
        return nombre+apellido;
    }

    private static int charCount(String string, char character) {
        int contador = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == character) {
                contador++;
            }
        }
        return contador;
    }

    private static String normalize(String texto) {
        // Normaliza el texto (por ejemplo: "á" → "á")
        String textoNormalizado = Normalizer.normalize(texto, Normalizer.Form.NFD);
        // Elimina los caracteres que no son ASCII (los diacríticos)
        Pattern patron = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return (patron.matcher(textoNormalizado).replaceAll("")).toLowerCase();
    }

    private static String removeSpaces (String string){
        StringBuilder sb = new StringBuilder(string);

        for (int i = 0; i < sb.length(); i++) {
            if (Character.isWhitespace(sb.charAt(i))) {
                sb.deleteCharAt(i);
                i--; // Retrocede una posición porque eliminaste un carácter
            }
        }
        return sb.toString();
    }


    @Override
    public void callMyNumber() {
        System.out.println("El contacto " + this.name + " " + this.surname1 +
                " con numero " + this.phone + " se está llamando a si mismo");
    }

    @Override
    public void callOtherNumber(String number) {
        System.out.println("El contacto " + this.name + " " + this.surname1 + " está llamando a " + number);
    }

    @Override
    public void showContactDetails() {
        System.out.println("Name: " + this.name + " Surname: " + this.surname1 + " Phone: " + this.phone
        + " Code: " + this.code);
    }

    public String getName() {
        return name;
    }

    public String getSurnames() {
        return surname1;
    }

    public String getPhone() {
        return phone;
    }

    public String getCode() {
        return code;
    }
}
