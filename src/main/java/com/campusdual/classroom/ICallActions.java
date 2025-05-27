package com.campusdual.classroom;

public interface ICallActions {
    void callMyNumber();
    //Este métod. tendrá que indicar con un mensaje escrito por consola
    //que el propio contacto se está llamando a sí mismo, y especificar
    //cuál es su número de teléfono

    void callOtherNumber(String number);
    //Este métod. tendrá que indicar con un mensaje escrito por consola
    //que el propio contacto se está llamando a un número de teléfono
    //que se le facilita por parámetro

    void showContactDetails();
    //Muestra en un mensaje por consola con los detalles del contacto
}