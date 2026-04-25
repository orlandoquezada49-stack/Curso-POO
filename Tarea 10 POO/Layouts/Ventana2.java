package Layouts;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Ventana2 extends Frame {
    Button boton1= new Button("Norte");
    Button boton2= new Button("Sur");
    Button boton3= new Button("Este");
    Button boton4= new Button("Oste");

    public Ventana2() {
        super("Ventana Con Eventos");
        setSize(600, 500);
        setResizable(false);
        setLayout(new BorderLayout());

       add(boton1,BorderLayout.NORTH);
       add(boton2,BorderLayout.SOUTH);
       add(boton3,BorderLayout.EAST);
       add(boton4,BorderLayout.WEST);

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
