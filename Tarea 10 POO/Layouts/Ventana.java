package Layouts;


import java.awt.*;

public class Ventana extends Frame {
    Button boton1;
    TextField texto;
    FlowLayout Layout;

    public Ventana() {
    }

    public Ventana(String titulo, int ancho, int alto) {
        super(titulo);
        setSize(ancho, alto);
        boton1 = new Button("Botón 1");
        texto = new TextField();
        Layout = new FlowLayout();
        this.setLayout(Layout);

        add(boton1);
        add(texto);


    }
}
