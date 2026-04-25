package Layouts;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Ventana3 extends Frame {
    Button boton1= new Button("Presioname Cofa");
    Button boton2= new Button("En segundo lugar Ete");
    Button boton3= new Button("Despues a este buen hombre");
    Button boton4= new Button("Por Ultimo a Este");

    public Ventana3() {
        super("Ventana Con Eventos");
        setSize(600, 500);
        setResizable(false);
        setLayout(new GridLayout(3,2));

        add(boton1,0);
        add(boton2,1);
        add(boton3,2);
        add(boton4,3);

        addWindowListener(
                new  WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
    }
}
