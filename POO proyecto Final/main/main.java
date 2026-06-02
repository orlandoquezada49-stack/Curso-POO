package org.example.main;

import org.example.controlador.controlador;
import org.example.vista.VentanaLibros;

public class main {

    public static void main(String[] args) {

        VentanaLibros vista = new VentanaLibros();
        controlador controlador = new controlador(vista);

    }
}